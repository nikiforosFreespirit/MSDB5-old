package game.elements.player.strategy.auction.msdb5;

import game.elements.cardset.DeckAwareHand;
import game.elements.player.Player;
import game.elements.player.auction.AuctionInfo;
import game.elements.player.auction.Score;
import game.elements.player.auction.Status;
import game.elements.player.strategy.auction.IAuctionAction;
import game.elements.player.strategy.evaluation.hand.IHandEvaluator;

/**
 * Created by nikiforos on 10/09/15.
 */
public class AuctionAction_BuonCompagno implements IAuctionAction {

    @Override
    public Score chooseNextScore(DeckAwareHand deckAwareHand, int currentScore) {
        // TODO: put appropriate implementation of IHandEvaluator and extract class
        IHandEvaluator handEvaluator = handToEvaluate -> 0;
        int handEvaluation = handEvaluator.evaluateHand(deckAwareHand);
        int nextScore = decideNextScore(currentScore, handEvaluation);

        final Score score = new Score();
        score.setSafeScore(nextScore);
        return score;
    }

    @Override
    public AuctionInfo chooseNextStance(Player playerDeciding, int currentScore) {
        // TODO: logic is not complete (improve test)
        final AuctionInfo auctionInfo = new AuctionInfo();
        auctionInfo.setStatus(Status.IN_AUCTION);
        auctionInfo.setScore(chooseNextScore(playerDeciding.getDeckAwareHand(), currentScore));
        return auctionInfo;
    }

    private int decideNextScore(int currentScore, int handEvaluation) {
        int nextScore = currentScore;
        nextScore++; // TODO: change logic (and include handEvaluation)
        return nextScore;
    }
}
