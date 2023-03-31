package com.example.leaderboard;

import com.google.common.collect.Lists;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;

@Controller
@Scope(value= WebApplicationContext.SCOPE_SESSION)
public class LeaderboardController {

    @Autowired
    LeaderboardService processingService;

    private LinkedList<ScoreEntry> scores = Lists.newLinkedList();

    @GetMapping("/leaderboard")
    public String initialize(Model model) {
        if (scores.isEmpty()) {
            scores = processingService.initializeLeaderboard();
        }
        model.addAttribute("scoresList", scores);
        return "leaderboard";
    }

    @PostMapping("/findPlayer")
    public String findPlayer(HttpServletRequest request, Model model) {
        String playerName = request.getParameter("playerNameInput");
        scores = processingService.findPlayerByName(scores, playerName);
        return "redirect:leaderboard";
    }

}
