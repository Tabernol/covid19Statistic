package com.example.covid19.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regions")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    private String iso;
    private String name;

}
