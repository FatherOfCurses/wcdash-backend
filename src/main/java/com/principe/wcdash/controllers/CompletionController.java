package com.principe.wcdash.controllers;

import com.principe.wcdash.domain.DateHandler;
import com.principe.wcdash.domain.Transaction;
import com.principe.wcdash.domain.SummaryData;
import com.principe.wcdash.service.DatabaseServiceImpl;
import com.principe.wcdash.service.FilteringService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.List;

@RestController
@RequestMapping("api")
public class CompletionController {

        private final DatabaseServiceImpl databaseService;
        private FilteringService filteringService;

        CompletionController(DatabaseServiceImpl databaseService) {
                this.databaseService = databaseService;
        }

        /*

        Get summary data for five days of completed transactions (Date and AHT)

         */
        @ApiOperation(value = "List summary of completed transactions by date range. Date must be passed in date format YYYY-MM-DD.", response = SummaryData.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Bad query"),
                @ApiResponse(code = 500, message = "Not authorized")
        })

        //TODO: Parameterize request to allow for specification of date range
        @GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<SummaryData> averageDailyTransactionTime(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
                DateHandler transactionRange = filteringService.calculateDateRange(startDate, endDate);
                System.out.println("I just got asked for " + startDate + ", " + endDate + " from completionsummary.  That should be " + transactionRange.getNumberOfDays() + " days.");
                List<Transaction> transactionDaySummary = filteringService.listDateRangeTransactionDetail(databaseService.listAllTrans(), transactionRange, "Completed");
                return filteringService.listDateRangeTransactionSummary(transactionDaySummary, transactionRange);
        }

        /*

        Return list of last five days of transactions

         */
        @ApiOperation(value = "List details of completed transactions by date range.  Date must be passed in date format YYYY-MM-DD.", response = Transaction.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Bad query"),
                @ApiResponse(code = 500, message = "Not authorized")
        })

        //TODO: Parameterize request to allow for specification of date range
        @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Transaction> dateRangeCompletionDetail(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
                System.out.println("I just got asked for " + startDate + ", " + endDate + " from completionDetail" );
                DateHandler transactionRange = filteringService.calculateDateRange(startDate, endDate);
                return filteringService.listDateRangeTransactionDetail(databaseService.listAllTrans(), transactionRange, "Completed");
        }
        //TODO: Provide a download service?

        @GetMapping(value = "/test")
        public List<Transaction> fullTest() {
                System.out.println("calling test");
                return databaseService.listAllTrans();
        }

        // private List<Transaction>







}
