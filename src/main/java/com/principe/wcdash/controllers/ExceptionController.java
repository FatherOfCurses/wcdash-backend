package com.principe.wcdash.controllers;

import com.principe.wcdash.domain.DateHandler;
import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.domain.SummaryData;
import com.principe.wcdash.service.DatabaseService;
import com.principe.wcdash.service.FilteringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Completion;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exception")
@Api(value="Excepted Transaction Service", description="Operations related to exception transaction objects")

public class ExceptionController {
        @Autowired
        private DatabaseService databaseService;

        @Autowired
        private FilteringService filteringService;

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
                List<MinimalTrans> fullDataset = databaseService.listAllMinimalTrans();
                List<MinimalTrans> transactionDaySummary = filteringService.listDateRangeTransactionDetail(fullDataset, transactionRange, "Exception");
                return filteringService.listDateRangeTransactionSummary(transactionDaySummary, transactionRange);
        }

        /*

        Return list of last five days of transactions

         */
        @ApiOperation(value = "List completed transactions by date range. Date must be passed in date format YYYY-MM-DD.", response = Completion.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Bad query"),
                @ApiResponse(code = 500, message = "Not authorized")
        })

        //TODO: Parameterize request to allow for specification of date range
        @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<MinimalTrans> last5DaysCompletions(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
                DateHandler transactionRange = filteringService.calculateDateRange(startDate, endDate);
                List<MinimalTrans> fullDataset = databaseService.listAllMinimalTrans();
                return filteringService.listDateRangeTransactionDetail(fullDataset, transactionRange, "Exception");
        }
        //TODO: Provide a download service?


}
