package com.example.leaderboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class ListProcessingService {

    public LinkedList<ScoreEntry> initializeLeaderboard() {
        LinkedList<ScoreEntry> scoreEntries = Lists.newLinkedList();

        //todo map directly to object
        String jsonAsString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = Resources.getResource("leaderboard_init.json");
            jsonAsString = Resources.toString(url, StandardCharsets.UTF_8);

            //todo this code below is horrible and uses addAll
            List<ScoreEntry> list = Arrays.asList(mapper.readValue(jsonAsString, ScoreEntry[].class));
            scoreEntries.addAll(list);
        } catch (Exception e) {

        }

        return scoreEntries;
    }

}
