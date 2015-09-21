package gameplay.auction;

import game.player.Player;
import game.table.GameTable;

/**
 * TODO: to be deleted and replaced by AuctionRoulette soon
 * Created by nikiforos on 01/09/15.
 */
@Deprecated
class AuctionRound {

    private static final int MAX_FOLDED_PLAYERS = 4;

    public void execute(GameTable gameTable) {
        Player[] players = gameTable.getPlayers();
        for (int i = 0; i < players.length; i++) {
            if (allOtherPlayersFolded(gameTable)) {
                gameTable.setWinningPlayer();
//                gameTable.setAuctionIsOver();
                break;
            } else {
                final Player currentPlayerPlaying = players[i];
                // perform auction action
                currentPlayerPlaying.performAuctionAction(gameTable.getAuctionScore());
                // update table score locally
                if (!currentPlayerPlaying.hasFolded()) {
                    gameTable.setAuctionScore(currentPlayerPlaying.tellScore());
                }
            }
        }
    }

    private boolean allOtherPlayersFolded(GameTable gameTable) {
        return gameTable.getFoldedCount() == MAX_FOLDED_PLAYERS;
    }

}
