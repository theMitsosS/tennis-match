package com.equinity.tennis;

import com.equinity.tennis.player.TennisPlayer;
import com.equinity.tennis.utils.ScoreCalculator;

import static com.equinity.tennis.constants.TennisMatchConstants.GAME_ON_CURRENT_SCORE;
import static com.equinity.tennis.constants.TennisMatchConstants.GAME_OVER_CURRENT_SCORE;
import static com.equinity.tennis.constants.TennisMatchConstants.WINNING_SETS;

public class TennisMatch {
    private TennisPlayer player1;
    private TennisPlayer player2;

    public TennisMatch(TennisPlayer player1, TennisPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public TennisMatch() {
        player1 = new TennisPlayer();
        player2 = new TennisPlayer();
    }

    public boolean gameIsOn() {
        return player1.getSetsWon() != WINNING_SETS && player2.getSetsWon() != WINNING_SETS;
    }

    public String playerAWonPoint() {
        return calculateNewScore(player1, player2);
    }

    public String playerBWonPoint() {
        return calculateNewScore(player2, player1);

    }

    private String calculateNewScore(TennisPlayer wonPointPlayer, TennisPlayer lostPointPlayer) {
        StringBuilder score = new StringBuilder();
        if (gameIsOn()) {
            score.append(GAME_ON_CURRENT_SCORE);
            wonPointPlayer.addNewPoint(lostPointPlayer);
        } else {
            score.append(GAME_OVER_CURRENT_SCORE);
        }
        return score.append(currentScore()).toString();
    }

    public String currentScore() {
        return ScoreCalculator.calculateCurrentScore(player1, player2);
    }


}
