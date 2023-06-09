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

    public Node getNodeByPlayerName(String playerName) {
        Node current = first;
        while (current != null) {
            if (current.getScoreEntry().getName().equals(playerName)) {
                return current;
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
            Node current = first;
            while (current != null && current.getScoreEntry().getScore() >= scoreEntry.getScore()) {
                current = current.getNext();
            }
            if (current == null) {
                newNode.setPrev(last);
                last.setNext(newNode);
                last = newNode;
            } else if (current == first) {
                newNode.setNext(first);
                first.setPrev(newNode);
                first = newNode;
            } else {
                newNode.setNext(current);
                newNode.setPrev(current.getPrev());
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
            }
        }
        size++;
    }

    public void updateNodePosition(Node node) {
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
