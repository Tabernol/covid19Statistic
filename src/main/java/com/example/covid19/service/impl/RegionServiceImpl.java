package com.example.covid19.service.impl;

import com.example.covid19.entity.Region;
import com.example.covid19.exception.RegionException;
import com.example.covid19.repository.RegionRepository;
import com.example.covid19.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region findByIso(String iso) throws RegionException {
        return regionRepository.findByIso(iso)
                .orElseThrow(() -> new RegionException("Can't find region with iso: " + iso));
    }

    @Override
    public Region findByName(String name) throws RegionException {
        return regionRepository.findByName(name)
                .orElseThrow(() -> new RegionException("Can't find region with name: " + name));
    }
}
