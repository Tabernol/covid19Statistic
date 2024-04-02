package com.example.covid19.service.impl;

import com.example.covid19.entity.Region;
import com.example.covid19.exception.RegionException;
import com.example.covid19.repository.RegionRepository;
import com.example.covid19.service.RegionService;
import com.example.covid19.utill.RegionSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private final RegionSerializer regionSerializer;

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public List<Region> saveAll(String data) throws JsonProcessingException {
        List<Region> regionList = regionSerializer.serialize(data);
        return regionRepository.saveAll(regionList);
    }

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
