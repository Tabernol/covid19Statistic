package com.example.covid19.service;

import com.example.covid19.entity.Report;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ReportService {
    List<Report> getReport(String responseBody);
}
