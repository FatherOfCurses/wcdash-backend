package com.principe.wcdash.controllers;

import com.principe.wcdash.domain.DateHandler;
import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.domain.SummaryData;
import com.principe.wcdash.domain.FullTransDetails;
import com.principe.wcdash.service.DatabaseService;
import com.principe.wcdash.service.DatabaseServiceImpl;
import com.principe.wcdash.service.FilteringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complete")
@Api(value="Transaction Service", description="Operations related to completed transaction objects")

public class CompletionController {

        @Autowired
        private DatabaseServiceImpl databaseService;

        @Autowired
        private FilteringService filteringService;

        /*

        Get all

         */
        // TODO: Error handling on all @Get methods
        @ApiIgnore
        @ApiOperation(value = "List all transactions", response = FullTransDetails.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Database not available"),
                @ApiResponse(code = 408, message = "This is the custom message for 408 error when a database is not available"),
                @ApiResponse(code = 500, message = "This is the custom message for 500 error when a database is not available")
        })

        @GetMapping(value = "/transall", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<FullTransDetails>> listAllTrans() throws Exception{
                return databaseService.listAllTrans() == null ? new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT)
                        : ResponseEntity.ok(databaseService.listAllTrans());
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
                List<MinimalTrans> fullDataset = databaseService.listAllMinimalTrans();
                List<MinimalTrans> transactionDaySummary = filteringService.listDateRangeTransactionDetail(fullDataset, transactionRange, "Completed");
                return filteringService.listDateRangeTransactionSummary(transactionDaySummary, transactionRange);
        }

        /*

        Return list of last five days of transactions

         */
        @ApiOperation(value = "List details of completed transactions by date range.  Date must be passed in date format YYYY-MM-DD.", response = MinimalTrans.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Bad query"),
                @ApiResponse(code = 500, message = "Not authorized")
        })

        //TODO: Parameterize request to allow for specification of date range
        @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<MinimalTrans> dateRangeCompletionDetail(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
                System.out.println("I just got asked for " + startDate + ", " + endDate + " from completionDetail" );
                DateHandler transactionRange = filteringService.calculateDateRange(startDate, endDate);
                List<MinimalTrans> fullDataset = databaseService.listAllMinimalTrans();
                return filteringService.listDateRangeTransactionDetail(fullDataset, transactionRange, "Completed");
        }
        //TODO: Provide a download service?







}
