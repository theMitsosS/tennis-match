package com.equinity.tennis;

import java.util.Scanner;

import static com.equinity.tennis.constants.TennisMatchConstants.CHOOSE_THE_PLAYER_TO_WIN_A_POINT;
import static com.equinity.tennis.constants.TennisMatchConstants.INVALID_INPUT;
import static com.equinity.tennis.constants.TennisMatchConstants.TENNIS_MATCH_GREETING;

public class TennisApplication {
  public static void main(String[] args) {

    TennisMatch match = new TennisMatch();
    System.out.println(TENNIS_MATCH_GREETING);
    System.out.println(CHOOSE_THE_PLAYER_TO_WIN_A_POINT);
    Scanner sc = new Scanner(System.in, "UTF-8");

    while (sc.hasNextLine()) {
      String currentScore = "";
      int input = Integer.parseInt(sc.next());
      if (input == 1) {
        currentScore = match.playerAWonPoint();
      } else if (input == 2) {
        currentScore = match.playerBWonPoint();
      } else {
        System.out.println(INVALID_INPUT);
      }
      System.out.println(currentScore);
      System.out.println(CHOOSE_THE_PLAYER_TO_WIN_A_POINT);
    }

  }
}
