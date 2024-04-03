package com.example.covid19.service;

import com.example.covid19.entity.CountryReport;
import com.example.covid19.entity.Report;
import com.example.covid19.exception.ReportException;

import java.util.List;

public interface CountryReportService {

    CountryReport save(String iso, List<Report> reportList);

    boolean isExist(String iso);

    CountryReport getByIso(String iso) throws ReportException;
}
