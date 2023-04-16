package com.example.leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LeaderboardController {
    @Autowired
    LeaderboardService service;
    private BidirectionalLinkedList leaderboard = new BidirectionalLinkedList();

    @GetMapping("/leaderboard")
    public ResponseEntity<List<ScoreEntry>> getLeaderboard() {
        if (leaderboard.isEmpty()) {
            leaderboard = service.initializeLeaderboard();
        }
        return ResponseEntity.ok(
                leaderboard.getAllNodes()
                        .stream()
                        .map(Node::getScoreEntry)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertPlayer(@RequestBody List<ScoreEntry> newEntries) {
        leaderboard = service.insertPlayer(newEntries, leaderboard);
        return ResponseEntity.ok("Player import has completed.");
    }
}
