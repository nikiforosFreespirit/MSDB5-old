package com.msdb5.game.factory.cardset;

import com.msdb5.game.cardset.Deck;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory extends CardSetFactory {

    public DeckFactory() {
        super(Deck.DEFAULT_DECK_SIZE);
    }

    public Deck createDeck() {
        return new Deck(createCardSet());
    }

}