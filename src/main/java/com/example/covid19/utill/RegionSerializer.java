package com.example.covid19.utill;

import com.example.covid19.entity.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionSerializer {

    private final RegionMapper regionMapper;

    private final ObjectMapper objectMapper;

    public List<Region> serialize(String data) throws JsonProcessingException {
        List<Region> regionDataList = new ArrayList<>();

        // Read the JSON data and extract the "data" node
        JsonNode rootNode = objectMapper.readTree(data);
        JsonNode dataNode = rootNode.get("data");

        // Check if "data" node exists and is an array
        if (dataNode != null && dataNode.isArray()) {
            ArrayNode dataArray = (ArrayNode) dataNode;

            // Iterate over the elements of the array and deserialize each one into a RegionData object
            for (JsonNode node : dataArray) {
                String iso = node.get("iso").asText();
                String name = node.get("name").asText();
                Region region = new Region(iso, name);
                regionDataList.add(region);
            }
        }

        return regionDataList;
    }
}
