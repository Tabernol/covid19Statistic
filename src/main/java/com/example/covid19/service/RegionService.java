package com.example.covid19.service;

import com.example.covid19.entity.Region;
import com.example.covid19.exception.RegionException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RegionService {

    Region save(Region region);

    List<Region> saveAll(String data) throws JsonProcessingException;

    List<Region> findAll();

    Region findByIso(String iso) throws RegionException;

    Region findByName(String name) throws RegionException;
}
