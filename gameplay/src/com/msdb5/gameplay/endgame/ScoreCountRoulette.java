package com.msdb5.gameplay.endgame;

import com.msdb5.game.cardset.CardSet;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.player.Player;
import com.msdb5.game.table.GameTable;
import com.msdb5.gameplay.GameRoulette;

/**
 * Created by nikiforos on 18/02/16.
 */
public class ScoreCountRoulette implements GameRoulette {

    public void executeOn(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        for (Player player : players) {
            byte playerScore = 0;

            CardSet cardPile = player.getCardPile();
            for (Card card : cardPile.getCardSet()) {
                playerScore += card.pointsForTheCard();
            }

            player.setScore(playerScore);
        }
    }
}
