package com.principe.wcdash.service;

import com.principe.wcdash.domain.DateHandler;
import com.principe.wcdash.domain.Transaction;
import com.principe.wcdash.domain.SummaryData;
import com.principe.wcdash.domain.TopTen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import static java.lang.Math.toIntExact;

public class FilteringService {

        @Autowired
        private DatabaseService databaseService;

        public FilteringService() {
        }

        /**
         *  Operation:
         *  1. Take an ArrayList of MinimalTrans objects
         *  2. Filter by transaction type
         *  3. Limit by date range,
         *  4. Return an ArrayList of MinimalTrans objects
         *
         * @param transactionArray
         * @param transactionRange
         * @param transactionType
         * @return List<MinimalTrans> returnDetailList
         */


        //TODO: Does it make more sense to allow each controller (or service) to build an ArrayList of a single type of transaction?
        public List<Transaction> listDateRangeTransactionDetail(List<Transaction> transactionArray, DateHandler transactionRange, String transactionType) {

                /**
                 *  In order to handle how lambdas handle a range, have to provide a date range that includes dates on either side of the range.
                 *  Because operators are isAfter and isBefore, associated elements are named afterStartDate and beforeEndDate
                 *
                 *  Example:
                 *  Start Date = 2018-09-21  afterStartDate: 2018-09-20
                 *  End Date = 2018-09-25  beforeEndDate: 2018-09-26
                 *  Filtering transaction will then return dates *after* 2018-09-20 and *before* 2018-09-26
                 *
                 */
                List <Transaction> returnDetailList = transactionArray.stream()
                        .filter(x -> (x.getStatustext().equals(transactionType)))
                        .filter(x -> (x.getTransactionCompleteTime().toLocalDate().isAfter(transactionRange.getAfterStartDate())))
                        .filter(x -> (x.getTransactionCompleteTime().toLocalDate().isBefore(transactionRange.getBeforeEndDate())))
                        .collect(Collectors.toList());
                return returnDetailList;
        }



        /**
         * Operation:
         * 1. Take an ArrayList of MinimalTrans objects,
         * 2. Filter the array by the date range and transaction type,
         * 3. Group transactions by date,
         * 4. Count number of transactions for the date,
         * 5. Get the average transaction time for transactions on the date,
         * 6. Add the single date value, AHT, and number of transactions to a SummaryData object
         * 7. Add the SummaryData object to the ArrayList
         * 8. Return the ArrayList of SummaryData objects
         *
         * @param transactionDetail
         * @param transactionRange
         * @return List<SummaryData> returnSummaryList
         */
        public List<SummaryData> listDateRangeTransactionSummary(List<Transaction> transactionDetail, DateHandler transactionRange) {
                Map<LocalDate, Integer> totalTransForDate = DateRangeTransactionCount(transactionRange);
                int numberOfDays = transactionRange.getNumberOfDays();
                List<SummaryData> returnSummaryList = new ArrayList<>();
                SummaryData[] singleDaySummary = new SummaryData[numberOfDays];
                double averageHandleTime[] = new double[numberOfDays];
                long transactionCount[] = new long[numberOfDays];
                for(int i=0; i < numberOfDays; i++) {
                        singleDaySummary[i] = new SummaryData();
                        long longDate = new Long(i);
                        LocalDate currentDateinDataset = transactionRange.getStartDateRange().plusDays(longDate);
                        singleDaySummary[i].setSummaryDate(currentDateinDataset);
                        try {
                                averageHandleTime[i] = transactionDetail.stream().filter(y -> (y.getTransactionCompleteTime().toLocalDate()).isEqual(currentDateinDataset)).collect(Collectors.averagingInt(Transaction::getWorkTimeInSecs));
                                singleDaySummary[i].setDailyAverageWorkTime((long) averageHandleTime[i]);
                                transactionCount[i] = transactionDetail.stream().filter(y -> (y.getTransactionCompleteTime().toLocalDate()).isEqual(currentDateinDataset)).count();
                                singleDaySummary[i].setNumberOfTransactions(transactionCount[i]);
                                if (transactionCount[i] != 0) {
                                        double transactionTypeCount = transactionCount[i];
                                        double totalTransactionCount = totalTransForDate.get(currentDateinDataset);
                                        BigDecimal percentage = new BigDecimal((transactionTypeCount/totalTransactionCount)*100);
                                        BigDecimal roundPercentage = percentage.setScale(0, BigDecimal.ROUND_HALF_DOWN);
                                        singleDaySummary[i].setPercentOfTotal(roundPercentage);
                                } else {
                                        singleDaySummary[i].setPercentOfTotal(BigDecimal.ZERO);
                                }
                        }
                        catch (NullPointerException n) {
                                singleDaySummary[i].setDailyAverageWorkTime(0);
                                singleDaySummary[i].setNumberOfTransactions(0);
                        }
                        returnSummaryList.add(singleDaySummary[i]);
                }
                return returnSummaryList;
        }

        /**
         * Operation:
         */


        /**
         *
         * Operation:
         * 1. Take an array of MinimalTrans objects
         * 2. Return a count of the occurrence of the ten most frequently occurring exceptions in the format
         *                      Reason1,122
         *                      Reason2,110
         *                      ... etc
         *
         * Further details inside method
         *
         * @param arrayToProcess
         * @return List<TopTen>returnList
         */

        public List<TopTen> topTenExceptionList(List<Transaction> arrayToProcess) {
                /**
                 * 1. Take the provided arrayList
                 * 2. Filter group to only include exceptions.
                 * 3. Sort by the exception reason
                 * 4. Count the occurrences of each exception reason
                 * 5. Write reason and count out to a Map
                 */
                Map<String, Long> topTenList = arrayToProcess.stream()
                        .filter(x -> (x.getStatustext().equals("Exception")))
                        .sorted(Comparator.comparing(Transaction::getExceptionReason2))
                        .collect(Collectors.groupingBy(Transaction::getExceptionReason2, Collectors.counting()));
                /**
                 * Prepare arrays of variables for iterating through Map
                 * and converting to a list.
                 * Number of each variable must be the size of the Map
                 */
                int mapSize = topTenList.size();
                TopTen[] interimList = new TopTen[mapSize];
                List<TopTen> countingList = new ArrayList<>();
                int index = 0;
                /**
                 * 1. The Loop will iterate through all the key-value pairs in the Map
                 * 2. Initialize a new TopTen object inside the loop
                 * 3. Assign the Map key and Map value in the sequence to the according array element
                 * 4. Add the TopTen object to the ArrayList
                 */
                for (Map.Entry<String, Long> mapEntry: topTenList.entrySet()){
                        interimList[index] = new TopTen();
                        interimList[index].setElementName(mapEntry.getKey());
                        interimList[index].setElementCount(mapEntry.getValue().intValue());
                        countingList.add(interimList[index]);
                }
                /**
                 * 1. Put ArrayList of Exceptions and count in descending order
                 * 2. Only stream the first ten
                 * 3. Populate the ArrayList that will be returned
                 */
                List<TopTen> returnList = countingList.stream()
                        .sorted(Comparator.comparing(TopTen::getElementCount).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
                int listIndex = 1;
                for(TopTen oneEntry: returnList) {
                        oneEntry.setIndexNumber(listIndex);
                        listIndex++;
                }
                return returnList;
        }

        public DateHandler calculateDateRange (String startDate, String endDate) {
                DateHandler rangeToReturn = new DateHandler();
                rangeToReturn.setStartDateRange(LocalDate.parse(startDate));
                rangeToReturn.setEndDateRange(LocalDate.parse(endDate));
                rangeToReturn.setAfterStartDate(rangeToReturn.getStartDateRange().minusDays(1));
                rangeToReturn.setBeforeEndDate(rangeToReturn.getEndDateRange().plusDays(1));
                rangeToReturn.setNumberOfDays(Period.between(rangeToReturn.getAfterStartDate(), rangeToReturn.getBeforeEndDate()).getDays() - 1);
                return rangeToReturn;
        }

        Map<LocalDate, Integer> DateRangeTransactionCount(DateHandler transactionRange) {
                List<Transaction> fullCountDataset = databaseService.listAllTrans();
                // TODO: Strip logging once debugged
                for(Transaction oneTrans: fullCountDataset) {
                        System.out.println("One object row");
                        System.out.println(oneTrans.getTransactionCompleteTime());
                        System.out.println(oneTrans.getStatustext());
                        System.out.println(oneTrans.getWorkTimeInSecs());
                        System.out.println(oneTrans.getClaimnumber());
                        System.out.println(oneTrans.getTransIdFromDB());
                        System.out.println(oneTrans.getXmldata());
                        System.out.println(oneTrans.getExceptionReason2());
                }
                List <Transaction> returnDetailList = fullCountDataset.stream()
                        .filter(x -> (x.getTransactionCompleteTime().toLocalDate().isAfter(transactionRange.getAfterStartDate())))
                        .filter(x -> (x.getTransactionCompleteTime().toLocalDate().isBefore(transactionRange.getBeforeEndDate())))
                        .collect(Collectors.toList());
                Map<LocalDate, Integer> currentDateCount = new HashMap<>();
                for(Transaction oneDay : returnDetailList){
                        LocalDate currentDateinDataset = oneDay.getTransactionCompleteTime().toLocalDate();
                        currentDateCount.put(
                                // key
                                currentDateinDataset,
                                // value
                                (toIntExact(
                                        returnDetailList.stream()
                                                .filter(y -> (y.getTransactionCompleteTime().toLocalDate()).isEqual(currentDateinDataset))
                                                .count())));
                }
                return currentDateCount;
        }
}























