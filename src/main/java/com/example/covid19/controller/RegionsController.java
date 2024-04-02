package com.example.covid19.controller;

import com.example.covid19.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RegionsController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public void getRegions() throws IOException {
        log.info("go to regions");
        getRequest();
    }


    public void getRequest() throws IOException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        client.prepare("GET", "https://covid-19-statistics.p.rapidapi.com/regions")
                .setHeader("X-RapidAPI-Key", "4919255501mshe2ac11ca4846c23p1249e1jsn9b6fcd256ce3")
                .setHeader("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenAccept(response -> {
                    try {
                        if (response.getStatusCode() == 200) {
                            String responseBody = response.getResponseBody();
                            log.info(responseBody);
                            regionService.saveAll(responseBody);
                        } else {
                            log.warn("Request failed with status code: " + response.getStatusCode());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .join();

        client.close();
    }


}
