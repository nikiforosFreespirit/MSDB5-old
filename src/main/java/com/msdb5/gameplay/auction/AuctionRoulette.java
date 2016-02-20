package com.msdb5.gameplay.auction;

import com.msdb5.game.player.Player;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;
import com.msdb5.game.table.GameTable;
import com.msdb5.game.table.GameTableInfo;

/**
 * Created by nikiforos on 18/09/15.
 */
public class AuctionRoulette {

    public void execute(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        int playerInTurnIndex = 0;

        boolean isAuctionOver = false;
        Player playerInTurn = players[playerInTurnIndex];
        while (!isAuctionOver) {
            // 1) player takes his decision
            int currentScore = gameTable.getInfo().getAuctionScore();
// TODO: under total refactor
//            playerInTurn.performAuctionAction(currentScore);

            // 2) update game table
            int playerScore = playerInTurn.getAuctionInfo().getAuctionScore().getScore();
            if (currentScore < playerScore) {
                gameTable.getInfo().setAuctionScore(playerScore);
            }

            // 2.1) TODO: turn card if side deck is present

            // 3) set next player to do the auction
            playerInTurnIndex = setNextPlayerToGo(playerInTurnIndex);
            playerInTurn = players[playerInTurnIndex];

            // 4) verify auction is still ongoing
            isAuctionOver = isAuctionOver(gameTable);
        }
        // 5) set last player remaining as winner
        final int auctionScore = gameTable.getInfo().getAuctionScore();
        setWinner(players, auctionScore);
    }

    private void setWinner(Player[] players, int auctionScore) {
        Player winner = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getAuctionInfo().getAuctionScore().getScore() == auctionScore) {
                winner = players[i];
            }
        }
        winner.getAuctionInfo().setAuctionStatus(AuctionStatus.AUCTION_WINNER);
    }

    private boolean isAuctionOver(GameTable gameTable) {
        final int foldedCount = gameTable.getFoldedCount();
        final int auctionScore = gameTable.getInfo().getAuctionScore();
        return foldedCount == 4 || auctionScore >= AuctionScore.MAX_SCORE;
    }

    private int setNextPlayerToGo(int playerInTurn) {
        playerInTurn++;
        playerInTurn %= GameTableInfo.NUMBER_OF_PLAYERS;
        return playerInTurn;
    }
}