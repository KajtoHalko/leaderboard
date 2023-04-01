package com.example.leaderboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class LeaderboardService {

    public BidirectionalLinkedList initializeLeaderboard() {
        LinkedList<ScoreEntry> linkedList = Lists.newLinkedList();

        String jsonAsString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = Resources.getResource("leaderboard_init.json");
            jsonAsString = Resources.toString(url, StandardCharsets.UTF_8);

            //todo this code below is horrible and uses addAll
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

    public LinkedList<ScoreEntry> findPlayerByName(BidirectionalLinkedList scoreEntries, String playerName) {
//        Optional<ScoreEntry> specificEntry = scoreEntries
//                .stream()
//                .filter(entry -> playerName.equals(entry.getName()))
//                .findFirst();
//
//        return specificEntry
//                .map(scoreEntry -> new LinkedList<>(Collections.singletonList(scoreEntry)))
//                .orElseGet(Lists::newLinkedList);
        return Lists.newLinkedList();
    }

    public LinkedList<ScoreEntry> updateLeaderboard(LinkedList<ScoreEntry> leaderboard, List<ScoreEntry> newScores) {
        newScores.forEach(newScore -> {
            //todo handle adding new scores
        });
        return leaderboard;
    }

}
