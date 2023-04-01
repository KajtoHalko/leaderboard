package com.example.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Node {
    private ScoreEntry scoreEntry;
    private Node next;
    private Node prev;

    public Node(ScoreEntry scoreEntry) {
        this.scoreEntry = scoreEntry;
    }
}