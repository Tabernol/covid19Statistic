package com.example.covid19.service;

import com.example.covid19.entity.Region;
import com.example.covid19.exception.RegionException;

import java.util.List;

public interface RegionService {

    List<Region> findAll();

    Region findByIso(String iso) throws RegionException;

    Region findByName(String name) throws RegionException;
}
