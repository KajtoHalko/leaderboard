package com.example.leaderboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LeaderboardServiceTest {

    @InjectMocks
    private LeaderboardService testee;

    @Test
    void initializeLeaderBoardTest() {
        //when
        BidirectionalLinkedList result = testee.initializeLeaderboard();

        //then
        assertEquals(3, result.getAllNodes().size());
    }

    @Test
    void findPlayerByNameTest() {
        //given
        String searchedName = "A";
        Long expectedScore = 100L;

        BidirectionalLinkedList linkedList = new BidirectionalLinkedList();
        linkedList.addPlayer(new ScoreEntry(searchedName, expectedScore));
        linkedList.addPlayer(new ScoreEntry());

        //when
        ScoreEntry result = testee.findPlayerByName(linkedList, searchedName);

        //then
        assertEquals(searchedName, result.getName());
        assertEquals(expectedScore, result.getScore());
    }

    @Test
    void updatePlayerScoreTest() {
        //given
        String name = "A";
        Long score = 20L;
        ScoreEntry updatedPlayer = new ScoreEntry(name, score);

        BidirectionalLinkedList linkedList = new BidirectionalLinkedList();
        linkedList.addPlayer(new ScoreEntry("C", 30L));
        linkedList.addPlayer(updatedPlayer);
        linkedList.addPlayer(new ScoreEntry("B", 10L));


        //when
        BidirectionalLinkedList result = testee.updatePlayerScore(linkedList, updatedPlayer, 50L);

        //then
        assertEquals(3, result.getAllNodes().size());
        assertEquals(name, result.getAllNodes().get(0).getScoreEntry().getName());
    }

}
