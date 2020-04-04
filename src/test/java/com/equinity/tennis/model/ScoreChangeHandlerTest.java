package com.equinity.tennis.model;

import com.equinity.tennis.handler.ScoreChangeHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScoreChangeHandlerTest {

  TennisPlayer player;

  TennisPlayer opponent;

  ScoreChangeHandler handler;

  @Before
  public void setUp() {
    player = new TennisPlayer();
    opponent = new TennisPlayer();
    handler = new ScoreChangeHandler();
  }

  @Test
  public void addNewPoint_WinAGameStraight_registerThePoint() {
    //GIVEN
    mockSpyAndPlayer(0, 0, 0, 0, 0, 0);

    //WHEN
    for (int i = 0; i < 4; i++) {
      handler.addNewPoint(player, opponent);
    }

    //THEN
    assertPlayersScore(0, 1, 0, 0, 0, 0);
  }

  @Test
  public void addNewPoint_deuce_behaveAppropriately() {
    //GIVEN
    mockSpyAndPlayer(3, 0, 0, 3, 0, 0);

    //WHEN
    handler.addNewPoint(player, opponent);
    handler.addNewPoint(opponent, player);

    //THEN
    assertPlayersScore(3, 0, 0, 3, 0, 0);
  }

  @Test
  public void addNewPoint_gameAfterDeuce_behaveAppropriately() {
    //GIVEN
    mockSpyAndPlayer(3, 0, 0, 3, 0, 0);

    //WHEN
    handler.addNewPoint(player, opponent);
    handler.addNewPoint(opponent, player);
    handler.addNewPoint(player, opponent);
    handler.addNewPoint(player, opponent);

    //THEN
    assertPlayersScore(0, 1, 0, 0, 0, 0);
  }

  @Test
  public void addNewPoint_cleanSet_registerSet() {
    //GIVEN
    mockSpyAndPlayer(2, 3, 0, 3, 5, 0);

    //WHEN
    handler.addNewPoint(player, opponent);


    //THEN
    assertPlayersScore(0, 0, 1, 0, 0, 0);
  }

  @Test
  public void addNewPoint_tieBreakNonFinalSet_tieBreakIsReached() {
    //GIVEN
    mockSpyAndPlayer(0, 6, 1, 0, 5, 1);

    //WHEN
    for (int i = 0; i < 10; i++) {
      handler.addNewPoint(player, opponent);
    }

    //THEN
    assertPlayersScore(6, 6, 1, 0, 6, 1);
  }

  @Test
  public void addNewPoint_finalSetBeforeTieBreak_gamesUntil12() {
    //GIVEN
    mockSpyAndPlayer(0, 6, 2, 0, 5, 2);

    for (int i = 0; i < 10; i++) {
      handler.addNewPoint(player, opponent);
    }
    assertPlayersScore(2, 7, 2, 0, 6, 2);
    handler.addNewPoint(player, opponent);
    handler.addNewPoint(player, opponent);
    assertPlayersScore(0, 0, 3, 0, 0, 2);

  }

  @Test
  public void addNewPoint_finalSetTieBreak_enterTieBreak() {
    //GIVEN
    mockSpyAndPlayer(0, 12, 2, 0, 11, 2);


    for (int i = 0; i < 10; i++) {
      handler.addNewPoint(player, opponent);
    }
    assertPlayersScore(6, 12, 2, 0, 12, 2);
    handler.addNewPoint(player, opponent);
    assertPlayersScore(0, 0, 3, 0, 0, 2);
  }

  //o for opponent and p for player
  private void mockSpyAndPlayer(int oPoints, int oGames, int oSets, int pPoints, int pGames, int pSets) {
    opponent.setPointsWon(oPoints);
    opponent.setGamesWon(oGames);
    opponent.setSetsWon(oSets);
    player.setPointsWon(pPoints);
    player.setGamesWon(pGames);
    player.setSetsWon(pSets);
  }

  //o for opponent annd p for player
  private void assertPlayersScore(int pPoints, int pGames, int pSets, int oPoints, int oGames, int oSets) {
    assertEquals(pPoints, player.getPointsWon());
    assertEquals(pGames, player.getGamesWon());
    assertEquals(pSets, player.getSetsWon());
    assertEquals(oPoints, opponent.getPointsWon());
    assertEquals(oGames, opponent.getGamesWon());
    assertEquals(oSets, opponent.getSetsWon());

  }
}