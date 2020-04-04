package com.equinity.tennis.utils;

import com.equinity.tennis.model.TennisPlayer;
import com.equinity.tennis.points.Points;

import static com.equinity.tennis.constants.TennisMatchConstants.LAST_SET_MAX_GAMES;
import static com.equinity.tennis.constants.TennisMatchConstants.NON_LAST_SET_MAX_GAMES;
import static com.equinity.tennis.constants.TennisMatchConstants.WINNING_SETS;

public class ScoreCalculator {
  private static TennisPlayer player1;
  private static TennisPlayer player2;

  private ScoreCalculator() {
    //no public constructor for util classes
  }


  public static String calculateCurrentScore(TennisPlayer firstPlayer, TennisPlayer secondPlayer) {
    player1 = firstPlayer;
    player2 = secondPlayer;
    if (player1.getSetsWon() == WINNING_SETS - 1 && player2.getSetsWon() == WINNING_SETS - 1) {
      return calculateScoreInTieBreakGames(LAST_SET_MAX_GAMES);
    } else {
      return calculateScoreInTieBreakGames(NON_LAST_SET_MAX_GAMES);
    }

  }

  private static String calculateScoreInTieBreakGames(int tieBreakGames) {
    if (player1.getGamesWon() == tieBreakGames && player2.getGamesWon() == tieBreakGames) {
      return calculateScoreInTieBreak();
    } else {
      return calculateScoreInNonTieBreak();
    }
  }

  private static String calculateScoreInNonTieBreak() {
    String player1PointsRepresentation = Points.getRepresentationByValue(player1.getPointsWon());
    String player2PointsRepresentation = Points.getRepresentationByValue(player2.getPointsWon());
    return player1.getSetsWon() + "/" + player2.getSetsWon() + " " + player1.getGamesWon() + "/" + player2.getGamesWon()
        + " " + player1PointsRepresentation + "/" + player2PointsRepresentation;
  }

  private static String calculateScoreInTieBreak() {
    return player1.getSetsWon() + "/" + player2.getSetsWon() + " " + player1.getGamesWon() + "/" + player2.getGamesWon()
        + " " + player1.getPointsWon() + "/" + player2.getPointsWon();
  }
}
