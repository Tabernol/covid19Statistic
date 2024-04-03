package com.example.covid19.entity;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Getter
@Setter
@Builder
public class CountryReport {
    @Id
    String iso;

    List<Report> reportList;
}
