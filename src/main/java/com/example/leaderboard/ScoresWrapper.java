package com.example.leaderboard;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ScoresWrapper {
    private List<ScoreEntry> scores;

    public List<ScoreEntry> getScores() {
        if (scores == null) {
            scores = new ArrayList<>();
        }
        return scores;
    }
}
