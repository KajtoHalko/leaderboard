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
public class LeaderboardService {

    public BidirectionalLinkedList initializeLeaderboard() {
        LinkedList<ScoreEntry> linkedList = Lists.newLinkedList();

        String jsonAsString;
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = Resources.getResource("leaderboard_init.json");
            jsonAsString = Resources.toString(url, StandardCharsets.UTF_8);
            List<ScoreEntry> list = Arrays.asList(mapper.readValue(jsonAsString, ScoreEntry[].class));
            linkedList.addAll(list);
        } catch (Exception e) {
            //handle exception
        }

        linkedList.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));

        BidirectionalLinkedList resultList = new BidirectionalLinkedList();
        linkedList.forEach(resultList::addPlayer);

        return resultList;
    }

    public BidirectionalLinkedList insertPlayers(List<ScoreEntry> newEntries, BidirectionalLinkedList leaderboard) {
        for (ScoreEntry newEntry : newEntries) {
            leaderboard.addPlayer(newEntry);
        }
        return leaderboard;
    }

}
