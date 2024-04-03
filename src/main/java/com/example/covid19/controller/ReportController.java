package com.example.covid19.controller;

import com.example.covid19.entity.Report;
import com.example.covid19.exception.ReportException;
import com.example.covid19.service.CountryReportService;
import com.example.covid19.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    private final CountryReportService countryReportService;

    @GetMapping("/{iso}")
    private CompletableFuture<List<Report>> getResponse(@PathVariable("iso") String iso) throws ReportException {
        if (countryReportService.isExist(iso)) {
            log.info("The data is already stored locally");
            return CompletableFuture.completedFuture(countryReportService.getByIso(iso).getReportList());
        } else {
            log.info("Cal to server to obtain data");
            return callToRemoteServer(iso);
        }
    }


    private CompletableFuture<List<Report>> callToRemoteServer(String iso) {
        log.info("call get report " + iso);
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        String baseUrl = "https://covid-19-statistics.p.rapidapi.com/reports?iso=";
        String currentUrl = baseUrl + iso;

        return client.prepare("GET", currentUrl)
                .setHeader("X-RapidAPI-Key", "4919255501mshe2ac11ca4846c23p1249e1jsn9b6fcd256ce3")
                .setHeader("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenCompose(response -> {
                    if (response.getStatusCode() == 200) {
                        String responseBody = response.getResponseBody();
                        log.info(responseBody);
                        List<Report> reportList = reportService.getReport(responseBody);
                        countryReportService.save(iso, reportList); // save to MongoDB
                        log.info("data was saved to Mongo " + iso);
                        return CompletableFuture.completedFuture(reportList);
                    } else {
                        log.warn("Request failed with status code: " + response.getStatusCode());
                        return CompletableFuture.completedFuture(null); // Or handle error case appropriately
                    }
                })
                .whenComplete((result, exception) -> {
                    try {
                        client.close();
                    } catch (IOException e) {
                        log.warn("EXCEPTION");
                        throw new RuntimeException(e);
                    }
                });
    }
}
