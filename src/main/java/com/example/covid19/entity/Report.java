package com.example.covid19.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class Report {

    private String date;
    private Integer confirmed;
    private Integer deaths;
    private Integer active;
}
