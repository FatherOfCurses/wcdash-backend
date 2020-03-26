package com.principe.wcdash.controllers;

import com.principe.wcdash.domain.DateHandler;
import com.principe.wcdash.domain.Transaction;
import com.principe.wcdash.domain.TopTen;
import com.principe.wcdash.service.DatabaseService;
import com.principe.wcdash.service.FilteringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topten")
@Api(value="Top Ten Service", description="Operations related to providing top ten lists")

public class TopTenController {
        @Autowired
        private DatabaseService databaseService;

        @Autowired
        private FilteringService filteringService;

        /**
        * Return top ten exception reasons
        */
        @ApiOperation(value = "Return the top ten exception reasons for a date range. Date must be passed in date format YYYY-MM-DD.", response = TopTen.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Bad query"),
                @ApiResponse(code = 500, message = "Not authorized")
        })

        @GetMapping(value = "/exceptionreason", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<TopTen> topTenExceptionReasons(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
                System.out.println("I just got asked for " + startDate + ", " + endDate + " from exceptionreason" );
                DateHandler transactionRange = filteringService.calculateDateRange(startDate, endDate);
                List<Transaction> fullDataset = databaseService.listAllMinimalTrans();
                List<Transaction> dateSelectedList = filteringService.listDateRangeTransactionDetail(fullDataset, transactionRange, "Exception");
                return filteringService.topTenExceptionList(dateSelectedList);
        }

        /**
         * Return top ten customers for exceptions
         */
        @ApiIgnore
        @ApiOperation(value = "Return the top ten customers for exceptions for a date range. Date must be passed in date format YYYY-MM-DD.", response = TopTen.class, responseContainer = "List")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 404, message = "Bad query"),
                @ApiResponse(code = 500, message = "Not authorized")
        })

        @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<TopTen> topTenExceptionCustomers(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
                DateHandler transactionRange = filteringService.calculateDateRange(startDate, endDate);
                List<Transaction> fullDataset = databaseService.listAllMinimalTrans();
                List<Transaction> dateSelectedList = filteringService.listDateRangeTransactionDetail(fullDataset, transactionRange, "Exception");
                return filteringService.topTenExceptionList(dateSelectedList);
        }



}
