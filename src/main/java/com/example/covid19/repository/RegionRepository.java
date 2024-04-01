package com.example.covid19.repository;

import com.example.covid19.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {

    Optional<Region> findByIso(String iso);

    Optional<Region>  findByName(String name);
}
