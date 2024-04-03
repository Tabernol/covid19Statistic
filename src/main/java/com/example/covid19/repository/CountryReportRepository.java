package com.example.covid19.repository;

import com.example.covid19.entity.CountryReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryReportRepository extends MongoRepository<CountryReport, String> {
}
