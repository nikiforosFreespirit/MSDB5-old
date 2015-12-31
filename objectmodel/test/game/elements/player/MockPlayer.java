package game.elements.player;

import game.elements.cardset.Hand;
import game.elements.player.auction.info.AuctionInfo;
import game.elements.player.auction.info.AuctionScore;
import game.elements.player.auction.info.AuctionStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 04/09/15.
 */
@RunWith(Parameterized.class)
public class MockPlayer extends Player {

    private static final float CHANCE_TO_FOLD = 0.4F;

    @Parameterized.Parameters
    public static Collection initParameters() {
        return Arrays.asList(new Object[][]{
                {true},
                {false}
        });
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MockPlayer: " + this);
    }

    @Test
    public void testPerformAuctionAction0() throws Exception {
        byte initialScore = 0;
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getAuctionStatus() != AuctionStatus.FOLDED) {
            assertTrue(auctionInfo.getAuctionScore().getScore() > initialScore);
        }
    }

    @Test
    public void testPerformAuctionAction60() throws Exception {
        byte initialScore = 60;
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getAuctionStatus() != AuctionStatus.FOLDED) {
            assertTrue(auctionInfo.getAuctionScore().getScore() > initialScore);
        }
    }

    @Test
    public void testPerformAuctionAction89() throws Exception {
        byte initialScore = 89;
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getAuctionStatus() != AuctionStatus.FOLDED) {
            assertTrue(auctionInfo.getAuctionScore().getScore() > initialScore);
        }
        // TODO: turn the card when side deck is present
    }

    @Test
    public void testPerformAuctionAction119() throws Exception {
        byte initialScore = 119;
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getAuctionStatus() != AuctionStatus.FOLDED) {
            assertTrue(auctionInfo.getAuctionScore().getScore() == 120);
        }
    }

    @Test
    public void testPerformAuctionAction120() throws Exception {
        byte initialScore = 120;
        final AuctionInfo auctionInfo = this.getAuctionInfo();
        if (auctionInfo.getAuctionStatus() != AuctionStatus.FOLDED) {
            assertTrue(auctionInfo.getAuctionScore().getScore() == initialScore);
        }
    }

    @Test
    public void testValidity() throws Exception {
        assertNotNull(this.getHand());
        assertFalse(this.getHand().isEmpty());
        assertNotNull(this.getAuctionInfo());
        assertTrue(this.getAuctionInfo().getAuctionScore().getScore() >= AuctionScore.MIN_SCORE);
        assertTrue(this.getAuctionInfo().getAuctionScore().getScore() <= AuctionScore.MAX_SCORE);
        assertNotNull(this.getAuctionInfo().getAuctionStatus());
    }

    @Override
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
        nextScore = Math.max(nextScore, MIN_AUCTION_SCORE);
        nextScore = Math.min(nextScore, MAX_AUCTION_SCORE);
        return nextScore;
    }

}
