package com.example.leaderboard;

import com.google.common.collect.Lists;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;

@Controller
@Scope(value= WebApplicationContext.SCOPE_SESSION)
public class LeaderboardController {

    @Autowired
    LeaderboardService service;

    private LinkedList<ScoreEntry> scores = Lists.newLinkedList();

    @GetMapping("/leaderboard")
    public String initialize(HttpSession session) {
        if (scores.isEmpty()) {
            scores = service.initializeLeaderboard();
            session.setAttribute("scoresList", scores);
        }
        return "leaderboard";
    }

    @PostMapping("/findPlayer")
    public String findPlayer(HttpServletRequest request, HttpSession session) {
        String playerName = request.getParameter("playerNameInput");
        session.setAttribute("scoresList", service.findPlayerByName(scores, playerName));
        return "redirect:leaderboard";
    }

    @PostMapping("/refresh")
    public String refreshLeaderboard(HttpSession session) {
        session.setAttribute("scoresList", scores);
        return "redirect:leaderboard";
    }

}
