package com.equinity.tennis.player;

import lombok.Getter;
import lombok.Setter;

import static com.equinity.tennis.constants.TennisMatchConstants.LAST_SET_MAX_GAMES;
import static com.equinity.tennis.constants.TennisMatchConstants.WINNING_SETS;

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

    public void addNewPoint(TennisPlayer opponent) {
        this.opponent = opponent;
        if (setsWon == WINNING_SETS - 1 && opponent.setsWon == WINNING_SETS - 1) {
            addPointInLastSet();
        } else {
            addPointInNonFinalSet();
        }
    }

    private void addPointInNonFinalSet() {
        if (gamesWon < 5) {
            addPointInNonWinningSetGame();
        } else if (gamesWon - opponent.gamesWon >= 1) {
            addPointInWinningSetGame();
        } else if (gamesWon == 5) {
            addPointInNonWinningSetGame();
        } else {
            addPointInTieBreak();
        }
    }

    private void addPointInTieBreak() {
        if (pointsWon >= 6 && pointsWon - opponent.getPointsWon() >= 1) {
            registerWinningSet();
        } else {
            pointsWon++;
        }
    }

    private void addPointInNonWinningSetGame() {
        if (hasWonThisGame()) {
            registerWinningGame();
        } else {
            addPointInNonWinningGamePoint();
        }
    }

    private void addPointInNonWinningGamePoint() {
        if (opponent.pointsWon == 4) {
            opponent.setPointsWon(3);
        } else {
            pointsWon++;
        }
    }

    private void addPointInWinningSetGame() {
        if (hasWonThisGame()) {
            registerWinningSet();
        } else {
            addPointInNonWinningGamePoint();
        }
    }

    private void registerWinningGame() {
        gamesWon++;
        pointsWon = 0;
        opponent.setPointsWon(0);
    }

    private void registerWinningSet() {
        setsWon++;
        gamesWon = 0;
        pointsWon = 0;
        opponent.setGamesWon(0);
        opponent.setPointsWon(0);
    }

    private boolean hasWonThisGame() {
        return pointsWon == 3 && opponent.getPointsWon() < 3 || pointsWon == 4;
    }

    private void addPointInLastSet() {
        if (gamesWon < 5) {
            addPointInNonWinningSetGame();
        } else if (gamesWon - opponent.gamesWon >= 1) {
            addPointInWinningSetGame();
        } else if (gamesWon < LAST_SET_MAX_GAMES) {
            addPointInNonWinningSetGame();
        } else {
            addPointInTieBreak();
        }
    }
}
