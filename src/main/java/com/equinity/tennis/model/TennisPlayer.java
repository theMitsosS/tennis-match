package com.equinity.tennis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TennisPlayer {
  private int setsWon;
  private int gamesWon;
  private int pointsWon;
  private TennisPlayer opponent;

  public TennisPlayer() {
    setsWon = gamesWon = pointsWon = 0;
  }

  public void addPoint() {
    pointsWon++;
  }

  public void addGame() {
    gamesWon++;
  }

  public void addSet() {
    setsWon++;
  }

  public void resetPoints() {
    pointsWon = 0;
  }

  public void resetGames() {
    gamesWon = 0;
  }

}
