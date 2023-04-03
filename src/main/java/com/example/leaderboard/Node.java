package com.example.leaderboard;

import lombok.Data;

@Data
public class Node {
    private ScoreEntry scoreEntry;
    private Node next;
    private Node prev;

    public Node(ScoreEntry scoreEntry) {
        this.scoreEntry = scoreEntry;
    }
    @Override
    public String toString() {
        return scoreEntry.toString();
    }
}