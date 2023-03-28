package com.example.leaderboard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ListProcessingService {

    public LinkedHashMap<String, Long> initializeLeaderboard() {
        LinkedHashMap<String, Long> leaderboard = Maps.newLinkedHashMap();

        //todo map directly to object
        String jsonAsString = "";
        try {
            jsonAsString = FileUtils.readFileToString(new File("leaderboard_init.json"), StandardCharsets.UTF_8);

            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(jsonAsString, new TypeReference<List<ScoreEntry>>(){}).forEach(entry -> {
                String name = entry.getName();
                if (leaderboard.containsKey(name)) {
                    Long newValue = Long.sum(leaderboard.get(name), entry.getScore());
                    leaderboard.replace(name, newValue);
                } else {
                    leaderboard.put(name, entry.getScore());
                }
            });
        } catch (Exception e) {

        }

        return leaderboard;
    }

}
