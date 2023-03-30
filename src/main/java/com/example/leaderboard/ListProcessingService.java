package com.example.leaderboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
            //handle exception
        }

        scoreEntries.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));
        return scoreEntries;
    }

    public List<ScoreEntry> getNewScores() {
        //todo call REST endpoint for new scores
        //dummy insert
        return Collections.singletonList(new ScoreEntry("Dwight", 9999L));
    }

    public LinkedList<ScoreEntry> updateLeaderboard(LinkedList<ScoreEntry> leaderboard, List<ScoreEntry> newScores) {
        newScores.forEach(newScore -> {
            //todo handle adding new scores
        });
        return leaderboard;
    }

}
