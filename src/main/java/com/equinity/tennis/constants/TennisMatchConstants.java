package com.equinity.tennis.constants;

public class TennisMatchConstants {
  public static final int WINNING_SETS = 3;
  public static final int LAST_SET_MAX_GAMES = 12;
  public static final int NON_LAST_SET_MAX_GAMES = 6;
  public static final String GAME_ON_CURRENT_SCORE = "The current score is: ";
  public static final String GAME_OVER_CURRENT_SCORE = "The game has already finished with score: ";
  public static final String TENNIS_MATCH_GREETING = "Welcome on this awesome tennis match";
  public static final String CHOOSE_THE_PLAYER_TO_WIN_A_POINT =
      "Please type 1 to give a point to player number 1 and 2 to give a point to player number 2";
  public static final String INVALID_INPUT = "Not a valid input, please retry";

  private TennisMatchConstants() {
    //this class should never be instantiated
  }
}
