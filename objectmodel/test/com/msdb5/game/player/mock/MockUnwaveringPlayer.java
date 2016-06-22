package com.msdb5.game.player.mock;

import com.msdb5.game.cardset.Hand;
import com.msdb5.game.cardset.card.Card;
import com.msdb5.game.cardset.card.CardSuit;
import com.msdb5.game.mechanics.analysis.card.FixedScaleAnalyzer;
import com.msdb5.game.mechanics.analysis.card.ICardAnalyzer;
import com.msdb5.game.mechanics.analysis.hand.HandAnalysisData;
import com.msdb5.game.mechanics.analysis.hand.HandAnalyzer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikiforos on 04/09/15.
 */
public class MockUnwaveringPlayer extends MockPlayer {

    public MockUnwaveringPlayer() {
        super(0F, 5);
    }

    @Override
    public int evaluateHand(Hand handToEvaluate) {
        HandAnalyzer analyzer = new HandAnalyzer(handToEvaluate);
        HandAnalysisData analysisData = analyzer.analyze();

        Map<CardSuit, Integer> evaluationPerSuit = suitEvaluated(handToEvaluate);

        int evaluatedMaxScore = evaluationFunction(analysisData, evaluationPerSuit);

        return evaluatedMaxScore;
    }

    private int evaluationFunction(HandAnalysisData analysisData, Map<CardSuit, Integer> evaluationPerSuit) {
        int value = 60;

        int suitDensityAdder = Math.round(2 * analysisData.getSuitDensity());
        int weaknessIndexAdder = 12 / analysisData.getWeaknessIndex();
        int distanceFromSecondAdder = 3 * analysisData.getDistanceFromSecond();

        int suitHighestValue = Collections.max(evaluationPerSuit.values());

        System.out.println(analysisData);
        System.out.println(evaluationPerSuit);

        int total = value + suitDensityAdder + weaknessIndexAdder + distanceFromSecondAdder + suitHighestValue;

        return Math.round(0.85F * total);
    }

    private Map<CardSuit, Integer> suitEvaluated(Hand handToEvaluate) {
        Map<CardSuit, Integer> sumSuitEvaluation = getCardSuitIntegerMap(handToEvaluate);
        weightValuesInMap(sumSuitEvaluation);
        return sumSuitEvaluation;
    }

    private void weightValuesInMap(Map<CardSuit, Integer> sumSuitEvaluation) {
        for (Map.Entry<CardSuit, Integer> suitValue :
                sumSuitEvaluation.entrySet()) {
            CardSuit cardSuit = suitValue.getKey();
            int currentValue = suitValue.getValue();
            int weightedValue = (int) (currentValue / 20.0) * 3;
            sumSuitEvaluation.put(cardSuit, weightedValue);
        }
    }

    private Map<CardSuit, Integer> getCardSuitIntegerMap(Hand handToEvaluate) {
        Map<CardSuit, Integer> sumSuitEvaluation = new HashMap<>();
        for (CardSuit cardSuit : CardSuit.values()) {
            sumSuitEvaluation.put(cardSuit, 0);
        }

        ICardAnalyzer cardAnalyzer = new FixedScaleAnalyzer();

        for (Card card :
                handToEvaluate.getCardSet()) {
            CardSuit cardSuit = card.getCardSuit();
            Integer currentValue = sumSuitEvaluation.get(cardSuit);
            int evaluateCard = cardAnalyzer.analyze(card);
            sumSuitEvaluation.put(cardSuit, currentValue + evaluateCard);
        }
        return sumSuitEvaluation;
    }


}
