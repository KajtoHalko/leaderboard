package com.example.leaderboard;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LeaderboardServiceTest {

    @InjectMocks
    private LeaderboardService testee;

    @Test
    void initializeLeaderboardTest() {
        //when
        BidirectionalLinkedList result = testee.initializeLeaderboard();

        //then
        assertEquals(3, result.getAllNodes().size());
    }

    @Test
    void insertPlayersTest() {
        //given
        List<ScoreEntry> newEntries = Lists.newArrayList(
                new ScoreEntry("Player_1", 100L),
                new ScoreEntry("Player_2", 200L)
        );

        //when
        BidirectionalLinkedList result = testee.insertPlayers(newEntries, new BidirectionalLinkedList());

        //then
        assertEquals(2, result.getAllNodes().size());
        assertEquals(200L, result.getAllNodes().get(0).getScoreEntry().getScore());
    }

}
