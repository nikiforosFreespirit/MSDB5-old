package com.msdb5.game.player;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.player.info.AuctionInfo;
import com.msdb5.game.player.info.AuctionScore;
import com.msdb5.game.player.info.AuctionStatus;

/**
 * Created by nikiforos on 11/09/15.
 * Il Dubbioso: Questo giocatore non ha grandissima esperienza di briscola ma ha dalla sua quel giusto mix di fortuna,
 * carichi e dubbi. Ogni sua mossa è decisiva, la tensione si taglia con un grissino e la quantità di sudore emesso
 * farebbe funzionare persino una centrale Nucleare. Due volte su tre capita in situazioni nelle quali fai una mossa
 * e sei spacciato o ne fai un'altra e sei portato in trionfo, e questo lo rende particolarmente nervoso.
 * Il picco massimo si raggiunge in un caso di Cappotto durante il quale con una sola sua mossa si decide l'intera partita:
 * il suo prossimo regalo di compleanno sarà un cappio portatile, completo di Boia gonfiabile.
 */
public class Dubbioso extends Player {

    private static final float CHANCE_TO_FOLD = 0.4F;

    public AuctionInfo performAuctionAction(int currentScore) {
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (!auctionInfo.getAuctionStatus().hasFolded()) {
            final double randomFlag = Math.random();
            if (randomFlag > CHANCE_TO_FOLD) {
                auctionInfo.setAuctionStatus(AuctionStatus.IN_AUCTION);
                auctionInfo.setAuctionScore(chooseNextScore(this.getHand(), currentScore));
            } else {
                auctionInfo.setAuctionStatus(AuctionStatus.FOLDED);
            }
        }
        return auctionInfo;
    }

    AuctionScore chooseNextScore(Hand hand, int currentScore) {
        final AuctionScore auctionScore = new AuctionScore();
        final int nextScore = decideNextScore(currentScore);
        auctionScore.setSafeScore(nextScore);
        return auctionScore;
    }

    int decideNextScore(int currentScore) {
        int nextScore = ++currentScore;
        nextScore = Math.max(nextScore, AuctionScore.MIN_SCORE);
        nextScore = Math.min(nextScore, AuctionScore.MAX_SCORE);
        return nextScore;
    }
}