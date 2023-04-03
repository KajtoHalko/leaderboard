package com.example.leaderboard;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class BidirectionalLinkedList {
    private Node first;
    private Node last;
    private int size;

    public List<Node> getAllNodes() {
        List<Node> nodeList = Lists.newArrayList();
        Node current = first;
        while (current != null) {
            nodeList.add(current);
            current = current.getNext();
        }
        return nodeList;
    }

    public ScoreEntry getNodeByPlayerName(String playerName) {
        Node current = first;
        while (current != null) {
            if (current.getScoreEntry().getName().equals(playerName)) {
                return current.getScoreEntry();
            }
            current = current.getNext();
        }
        return null;
    }

    public void addPlayer(ScoreEntry scoreEntry) {
        Node newNode = new Node(scoreEntry);
        if (first == null) {
            first = newNode;
            last = newNode;
            first.setPrev(null);
            last.setNext(null);
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
            last.setNext(null);
        }
        size++;
    }

    public void updateScore(String playerName, long newScore) {
        //todo get node for specific player
        Node current = first;
        while (current != null) {
            if (current.getScoreEntry().getName().equals(playerName)) {
                current.getScoreEntry().setScore(newScore);
                updatePosition(current);
                break;
            }
            //todo next or previous dependent on which direction we want to go
            current = current.getNext();
        }
    }

    private void updatePosition(Node node) {
        Node current = node;
        while (current.getPrev() != null && current.getScoreEntry().getScore() > current.getPrev().getScoreEntry().getScore()) {
            swapNodes(current.getPrev(), current);
            current = current.getPrev();
        }
        while (current.getNext() != null && current.getScoreEntry().getScore() < current.getNext().getScoreEntry().getScore()) {
            swapNodes(current, current.getNext());
            current = current.getNext();
        }
    }

    private void swapNodes(Node node1, Node node2) {
        ScoreEntry tempScoreEntry = node1.getScoreEntry();
        node1.setScoreEntry(node2.getScoreEntry());
        node2.setScoreEntry(tempScoreEntry);
    }

    public boolean isEmpty() {
        return size == 0;
    }


}
