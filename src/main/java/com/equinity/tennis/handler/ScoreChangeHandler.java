package com.equinity.tennis.handler;

import com.equinity.tennis.model.TennisPlayer;

import static com.equinity.tennis.constants.TennisMatchConstants.LAST_SET_MAX_GAMES;
import static com.equinity.tennis.constants.TennisMatchConstants.WINNING_SETS;

public class ScoreChangeHandler {
  private TennisPlayer pointWinner;
  private TennisPlayer pointLoser;


  public void addNewPoint(TennisPlayer pointWinner, TennisPlayer pointLoser) {
    this.pointLoser = pointLoser;
    this.pointWinner = pointWinner;
    if (pointWinner.getSetsWon() == WINNING_SETS - 1 && pointLoser.getSetsWon() == WINNING_SETS - 1) {
      addPointInLastSet();
    } else {
      addPointInNonFinalSet();
    }
  }

  private void addPointInNonFinalSet() {
    int gamesWon = pointWinner.getGamesWon();
    if (gamesWon < 5) {
      addPointInNonWinningSetGame();
    } else if (gamesWon - pointLoser.getGamesWon() >= 1) {
      addPointInWinningSetGame();
    } else if (gamesWon == 5) {
      addPointInNonWinningSetGame();
    } else {
      addPointInTieBreak();
    }
  }

  private void addPointInTieBreak() {
    int pointsWon = pointWinner.getPointsWon();
    if (pointsWon >= 6 && pointsWon - pointLoser.getPointsWon() >= 1) {
      registerWinningSet();
    } else {
      pointWinner.addPoint();
    }
  }

  private void addPointInNonWinningSetGame() {
    if (winnerHasWonThisGame()) {
      registerWinningGame();
    } else {
      addPointInNonWinningGamePoint();
    }
  }

  private void addPointInNonWinningGamePoint() {
    if (pointLoser.getPointsWon() == 4) {
      pointLoser.setPointsWon(3);
    } else {
      pointWinner.addPoint();
    }
  }

  private void addPointInWinningSetGame() {
    if (winnerHasWonThisGame()) {
      registerWinningSet();
    } else {
      addPointInNonWinningGamePoint();
    }
  }

  private void registerWinningGame() {
    pointWinner.addGame();
    pointWinner.resetPoints();
    pointLoser.resetPoints();
  }

  private void registerWinningSet() {
    pointWinner.addSet();
    pointWinner.resetGames();
    pointWinner.resetPoints();
    pointLoser.resetGames();
    pointLoser.resetPoints();
  }

  private boolean winnerHasWonThisGame() {
    return pointWinner.getPointsWon() == 3 && pointLoser.getPointsWon() < 3 || pointWinner.getPointsWon() == 4;
  }

  private void addPointInLastSet() {
    int gamesWon = pointWinner.getGamesWon();
    if (gamesWon < 5) {
      addPointInNonWinningSetGame();
    } else if (gamesWon - pointLoser.getGamesWon() >= 1) {
      addPointInWinningSetGame();
    } else if (gamesWon < LAST_SET_MAX_GAMES) {
      addPointInNonWinningSetGame();
    } else {
      addPointInTieBreak();
    }
  }
}
