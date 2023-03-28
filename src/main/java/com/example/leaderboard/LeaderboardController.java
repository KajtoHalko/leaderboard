package com.example.leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;

@Controller
public class LeaderboardController {

    @Autowired
    ListProcessingService processingService;

    @GetMapping("/")
    public String initialize(Model model) {
        LinkedList<ScoreEntry> scores = processingService.initializeLeaderboard();
        model.addAttribute("scoresList", scores);
        return "leaderboard";
    }

}
