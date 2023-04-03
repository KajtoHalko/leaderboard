package com.example.leaderboard;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(value= WebApplicationContext.SCOPE_SESSION)
public class LeaderboardController {
    boolean isEditFieldVisible;
    boolean isNoPlayerFoundVisible;

    @Autowired
    LeaderboardService service;

    private BidirectionalLinkedList scores = new BidirectionalLinkedList();
    private ScoreEntry editedEntry;

    @GetMapping("/leaderboard")
    public String initialize(HttpSession session) {
        if (scores.isEmpty()) {
            scores = service.initializeLeaderboard();
            session.setAttribute("scoresList", scores.getAllNodes());
        }
        return "leaderboard";
    }

    @PostMapping("/findPlayer")
    public String findPlayer(HttpServletRequest request, HttpSession session) {
        String playerName = request.getParameter("playerNameInput");
        editedEntry = service.findPlayerByName(scores, playerName);
        if (editedEntry != null) {
            session.setAttribute("editedEntry", editedEntry);
            session.setAttribute("playerScore", editedEntry.getScore());
        }
        return "redirect:leaderboard";
    }

    @PostMapping("/refresh")
    public String refreshLeaderboard(HttpSession session) {
        session.setAttribute("scoresList", scores.getAllNodes());
        return "redirect:leaderboard";
    }

}
