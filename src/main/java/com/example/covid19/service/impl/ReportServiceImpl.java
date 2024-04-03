package com.example.covid19.service.impl;

import com.example.covid19.entity.Report;
import com.example.covid19.repository.CountryReportRepository;
import com.example.covid19.service.ReportService;
import com.example.covid19.utill.ReportSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportSerializer reportSerializer;


    @Override
    public List<Report> getReport(String responseBody) {
        try {
            return reportSerializer.serialize(responseBody);
        } catch (JsonProcessingException e) {
            //todo change exception
            throw new RuntimeException(e);
        }
    }
}
