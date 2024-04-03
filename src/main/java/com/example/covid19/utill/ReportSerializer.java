package com.example.covid19.utill;

import com.example.covid19.entity.Region;
import com.example.covid19.entity.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ReportSerializer {
    private final ObjectMapper objectMapper;

    public List<Report> serialize(String data) throws JsonProcessingException {
        List<Report> reportList = new ArrayList<>();

        // Read the JSON data and extract the "data" node
        JsonNode rootNode = objectMapper.readTree(data);
        JsonNode dataNode = rootNode.get("data");

        // Check if "data" node exists and is an array
        if (dataNode != null && dataNode.isArray()) {
            ArrayNode dataArray = (ArrayNode) dataNode;

            // Iterate over the elements of the array and deserialize each one into a RegionData object
            for (JsonNode node : dataArray) {
                String date = node.get("date").asText();
                int confirmed = node.get("confirmed").asInt();
                int deaths = node.get("deaths").asInt();
                int active = node.get("active").asInt();
                Report report = Report.builder()
                        .date(date)
                        .active(active)
                        .deaths(deaths)
                        .confirmed(confirmed)
                        .build();
                reportList.add(report);
            }
        }

        return reportList;
    }
}
