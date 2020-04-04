package com.equinity.tennis.utils;

import com.equinity.tennis.model.TennisPlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreCalculatorTest {

  @Test
  public void calculateCurrentScore_noFinalSetNoTieBreak_appropriateString() {
    TennisPlayer player1 = setUpPlayer(4, 4, 1);
    TennisPlayer player2 = setUpPlayer(3, 5, 1);

    String actualScore = ScoreCalculator.calculateCurrentScore(player1, player2);
    String expectedScore = "1/1 4/5 AD/40";

    assertEquals(expectedScore, actualScore);
  }

  @Test
  public void calculateCurrentScore_noFinalSetInTieBreak_appropriateString() {
    TennisPlayer player1 = setUpPlayer(4, 6, 1);
    TennisPlayer player2 = setUpPlayer(3, 6, 1);

    String actualScore = ScoreCalculator.calculateCurrentScore(player1, player2);
    String expectedScore = "1/1 6/6 4/3";

    assertEquals(expectedScore, actualScore);
  }

  @Test
  public void calculateCurrentScore_FinalSetNoTieBreak_appropriateString() {
    TennisPlayer player1 = setUpPlayer(4, 6, 2);
    TennisPlayer player2 = setUpPlayer(3, 6, 2);

    String actualScore = ScoreCalculator.calculateCurrentScore(player1, player2);
    String expectedScore = "2/2 6/6 AD/40";

    assertEquals(expectedScore, actualScore);
  }

  @Test
  public void calculateCurrentScore_FinalSetTieBreak_appropriateString() {
    TennisPlayer player1 = setUpPlayer(4, 12, 2);
    TennisPlayer player2 = setUpPlayer(3, 12, 2);

    String actualScore = ScoreCalculator.calculateCurrentScore(player1, player2);
    String expectedScore = "2/2 12/12 4/3";

    assertEquals(expectedScore, actualScore);
  }


  private TennisPlayer setUpPlayer(int points, int games, int sets) {
    TennisPlayer player = new TennisPlayer();
    player.setPointsWon(points);
    player.setGamesWon(games);
    player.setSetsWon(sets);
    return player;
  }
}