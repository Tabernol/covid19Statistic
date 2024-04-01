package com.example.covid19.entity;

import org.springframework.data.annotation.Id;

public record Region(@Id String iso, String name) {
}
