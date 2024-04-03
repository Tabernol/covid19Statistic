package com.example.covid19.service.impl;

import com.example.covid19.entity.CountryReport;
import com.example.covid19.entity.Report;
import com.example.covid19.exception.AppException;
import com.example.covid19.exception.ReportException;
import com.example.covid19.repository.CountryReportRepository;
import com.example.covid19.service.CountryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryReportServiceImpl implements CountryReportService {
    private final CountryReportRepository countryReportRepository;

    @Override
    public CountryReport save(String iso, List<Report> reportList) {
        CountryReport countryReport = CountryReport.builder().iso(iso).reportList(reportList).build();
        return countryReportRepository.save(countryReport);
    }

    @Override
    public boolean isExist(String iso) {
        return countryReportRepository.existsById(iso);
    }

    @Override
    public CountryReport getByIso(String iso) throws ReportException {
        return countryReportRepository.findById(iso)
                .orElseThrow(() -> new ReportException("Cann't find report by iso: " + iso));
    }


}
