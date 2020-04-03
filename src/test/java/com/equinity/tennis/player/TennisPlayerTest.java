package com.equinity.tennis.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TennisPlayerTest {

    TennisPlayer player;

    @Spy
    TennisPlayer mockOpponent;

    @Before
    public void setUp() {
        player = new TennisPlayer();
    }

    @Test
    public void addNewPoint_WinAGameStraight_registerThePoint() {
        //GIVEN
        mockSpyAndPlayer(0, 0, 0, 0, 0, 0);

        //WHEN
        for (int i = 0; i < 4; i++) {
            player.addNewPoint(mockOpponent);
        }

        //THEN
        assertPlayersScore(0, 1, 0, 0, 0, 0);
    }

    @Test
    public void addNewPoint_deuce_behaveAppropriately() {
        //GIVEN
        mockSpyAndPlayer(3, 0, 0, 3, 0, 0);

        //WHEN
        player.addNewPoint(mockOpponent);
        mockOpponent.addNewPoint(player);

        //THEN
        assertPlayersScore(3, 0, 0, 3, 0, 0);
    }

    @Test
    public void addNewPoint_gameAfterDeuce_behaveAppropriately() {
        //GIVEN
        mockSpyAndPlayer(3, 0, 0, 3, 0, 0);

        //WHEN
        player.addNewPoint(mockOpponent);
        mockOpponent.addNewPoint(player);
        player.addNewPoint(mockOpponent);
        player.addNewPoint(mockOpponent);

        //THEN
        assertPlayersScore(0, 1, 0, 0, 0, 0);
    }

    @Test
    public void addNewPoint_cleanSet_registerSet() {
        //GIVEN
        mockSpyAndPlayer(2, 3, 0, 3, 5, 0);

        //WHEN
        player.addNewPoint(mockOpponent);

        //THEN
        assertPlayersScore(0, 0, 1, 0, 0, 0);
    }

    @Test
    public void addNewPoint_tieBreakNonFinalSet_tieBreakIsReached() {
        //GIVEN
        mockSpyAndPlayer(0, 6, 1, 0, 5, 1);

        //WHEN
        for (int i = 0; i < 10; i++) {
            player.addNewPoint(mockOpponent);
        }

        //THEN
        assertPlayersScore(6, 6, 1, 0, 6, 1);
    }

    @Test
    public void addNewPoint_finalSetBeforeTieBreak_gamesUntil12() {
        //GIVEN
        mockSpyAndPlayer(0, 6, 2, 0, 5, 2);

        for (int i = 0; i < 10; i++) {
            player.addNewPoint(mockOpponent);
        }
        assertPlayersScore(2, 7, 2, 0, 6, 2);
        player.addNewPoint(mockOpponent);
        player.addNewPoint(mockOpponent);
        assertPlayersScore(0, 0, 3, 0, 0, 2);

    }

    @Test
    public void addNewPoint_finalSetTieBreak_enterTieBreak() {
        //GIVEN
        mockSpyAndPlayer(0, 12, 2, 0, 11, 2);


        for (int i = 0; i < 10; i++) {
            player.addNewPoint(mockOpponent);
        }
        assertPlayersScore(6, 12, 2, 0, 12, 2);
        player.addNewPoint(mockOpponent);
        assertPlayersScore(0, 0, 3, 0, 0, 2);
    }

    //o for opponent and p for player
    private void mockSpyAndPlayer(int oPoints, int oGames, int oSets, int pPoints, int pGames, int pSets) {
        mockOpponent.setPointsWon(oPoints);
        mockOpponent.setGamesWon(oGames);
        mockOpponent.setSetsWon(oSets);
        player.setPointsWon(pPoints);
        player.setGamesWon(pGames);
        player.setSetsWon(pSets);
    }

    //o for opponent annd p for player
    private void assertPlayersScore(int pPoints, int pGames, int pSets, int oPoints, int oGames, int oSets) {
        assertEquals(pPoints, player.getPointsWon());
        assertEquals(pGames, player.getGamesWon());
        assertEquals(pSets, player.getSetsWon());
        assertEquals(oPoints, mockOpponent.getPointsWon());
        assertEquals(oGames, mockOpponent.getGamesWon());
        assertEquals(oSets, mockOpponent.getSetsWon());

    }
}