package com.example.covid19.utill;

import com.example.covid19.entity.Region;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionMapper {

    public static Region map(RegionData regionData) {
        return new Region(regionData.getIso(), regionData.getName());
    }

    public static List<Region> mapAll(List<RegionData> regionDataList) {
        return regionDataList.stream().map(data -> new Region(data.getIso(), data.getName())).toList();
    }
}
