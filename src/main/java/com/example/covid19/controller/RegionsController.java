package com.example.covid19.controller;

import com.example.covid19.entity.Region;
import com.example.covid19.service.RegionService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionsController {

    private final RegionService regionService;

//    @GetMapping()
//    public void getRegions() throws IOException {
//        log.info("go to regions");
//        getRequest();
//    }


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

//    @GetMapping("/{iso}")
//    public void getProvinces(@PathVariable("iso") String iso) throws IOException {
//        AsyncHttpClient client = new DefaultAsyncHttpClient();
//        String baseUrl = "https://covid-19-statistics.p.rapidapi.com/provinces?iso=";
//        String currentUrl = baseUrl + iso;
//        client.prepare("GET", currentUrl)
//                .setHeader("X-RapidAPI-Key", "4919255501mshe2ac11ca4846c23p1249e1jsn9b6fcd256ce3")
//                .setHeader("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
//                .execute()
//                .toCompletableFuture()
//                .thenAccept(System.out::println)
//                .join();
//
//        client.close();
//    }

    @GetMapping()
    public List<Region> getAllCountries() {
        return regionService.findAll();
    }


}
