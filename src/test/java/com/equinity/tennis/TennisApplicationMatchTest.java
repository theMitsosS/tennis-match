package com.equinity.tennis;

import com.equinity.tennis.player.TennisPlayer;
import com.equinity.tennis.utils.ScoreCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.equinity.tennis.constants.TennisMatchConstants.GAME_ON_CURRENT_SCORE;
import static com.equinity.tennis.constants.TennisMatchConstants.GAME_OVER_CURRENT_SCORE;
import static com.equinity.tennis.constants.TennisMatchConstants.WINNING_SETS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ScoreCalculator.class)
public class TennisApplicationMatchTest {

    TennisMatch match;

    @Mock
    TennisPlayer mockPlayer1;

    @Mock
    TennisPlayer mockPlayer2;


    @Before
    public void setUp() {
        match = new TennisMatch(mockPlayer1, mockPlayer2);
    }


    @Test
    public void isGameOn_noPlayershaveWinningSets_returnTrue() {
        //GIVEN
        when(mockPlayer1.getSetsWon()).thenReturn(WINNING_SETS - 1);
        when(mockPlayer2.getSetsWon()).thenReturn(WINNING_SETS - 1);

        //WHEN
        boolean expectedResult = match.gameIsOn();

        //THEN
        assertTrue(expectedResult);
    }

    @Test
    public void isGameOn_APlayerhaveThreeSets_returnFalse() {
        //GIVEN
        when(mockPlayer1.getSetsWon()).thenReturn(WINNING_SETS);
        when(mockPlayer2.getSetsWon()).thenReturn(WINNING_SETS - 1);

        //WHEN
        boolean expectedResult = match.gameIsOn();

        //THEN
        assertFalse(expectedResult);
    }

    @Test
    public void playerAWonPoint_gameStillOn_appropriateMessage() {
        //GIVEN
        when(mockPlayer1.getSetsWon()).thenReturn(WINNING_SETS);
        when(mockPlayer2.getSetsWon()).thenReturn(WINNING_SETS - 1);
        when(mockPlayer1.getSetsWon()).thenReturn(1);
        when(mockPlayer1.getGamesWon()).thenReturn(2);
        when(mockPlayer1.getPointsWon()).thenReturn(1);
        when(mockPlayer2.getSetsWon()).thenReturn(0);
        when(mockPlayer2.getGamesWon()).thenReturn(4);
        when(mockPlayer2.getPointsWon()).thenReturn(1);

        //WHEN
        String score = match.playerAWonPoint();

        //THEN
        assertEquals(GAME_ON_CURRENT_SCORE + "1/0 2/4 15/15", score);
    }

    @Test
    public void playerAWonPoint_gameHasFinished_appropriateMessage() {
        //GIVEN
        when(mockPlayer1.getSetsWon()).thenReturn(3);
        when(mockPlayer1.getGamesWon()).thenReturn(2);
        when(mockPlayer1.getPointsWon()).thenReturn(1);
        when(mockPlayer2.getSetsWon()).thenReturn(0);
        when(mockPlayer2.getGamesWon()).thenReturn(4);
        when(mockPlayer2.getPointsWon()).thenReturn(1);

        //WHEN
        String score = match.playerAWonPoint();

        //THEN
        assertEquals(GAME_OVER_CURRENT_SCORE + "3/0 2/4 15/15", score);
    }

    @Test
    public void playerBWonPoint_gameStillOn_appropriateMessage() {
        //GIVEN
        when(mockPlayer1.getSetsWon()).thenReturn(WINNING_SETS);
        when(mockPlayer2.getSetsWon()).thenReturn(WINNING_SETS - 1);
        when(mockPlayer1.getSetsWon()).thenReturn(1);
        when(mockPlayer1.getGamesWon()).thenReturn(2);
        when(mockPlayer1.getPointsWon()).thenReturn(1);
        when(mockPlayer2.getSetsWon()).thenReturn(0);
        when(mockPlayer2.getGamesWon()).thenReturn(4);
        when(mockPlayer2.getPointsWon()).thenReturn(1);

        //WHEN
        String score = match.playerBWonPoint();

        //THEN
        assertEquals(GAME_ON_CURRENT_SCORE + "1/0 2/4 15/15", score);

    }

    @Test
    public void playerBWonPoint_gameHasFinished_appropriateMessage() {
        when(mockPlayer1.getSetsWon()).thenReturn(3);
        when(mockPlayer1.getGamesWon()).thenReturn(2);
        when(mockPlayer1.getPointsWon()).thenReturn(1);
        when(mockPlayer2.getSetsWon()).thenReturn(0);
        when(mockPlayer2.getGamesWon()).thenReturn(4);
        when(mockPlayer2.getPointsWon()).thenReturn(1);

        //WHEN
        String score = match.playerBWonPoint();

        //THEN
        assertEquals(GAME_OVER_CURRENT_SCORE + "3/0 2/4 15/15", score);
    }

    @Test
    public void currentScore() {
        String mockScoreCalculatorResult = "mockScoreCalculatorResult";
        PowerMockito.mockStatic(ScoreCalculator.class);
        when(ScoreCalculator.calculateCurrentScore(mockPlayer1, mockPlayer2)).thenReturn(mockScoreCalculatorResult);
        String expectedResult = ScoreCalculator.calculateCurrentScore(mockPlayer1, mockPlayer2);
        assertEquals(expectedResult, mockScoreCalculatorResult);


    }
}