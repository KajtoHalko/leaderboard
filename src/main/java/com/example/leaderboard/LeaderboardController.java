package com.example.leaderboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {

    @GetMapping("/")
    public String welcome() {
        return "leaderboard";
    }

}
