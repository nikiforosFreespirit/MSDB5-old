package game.factory.cardset;

import game.cardset.Deck;

/**
 * Created by nikiforos on 08/09/15.
 */
public class DeckFactory extends CardSetFactory {

    public DeckFactory() {
        super(Deck.DECK_DEFAULT_SIZE);
    }

    public Deck createDeck() {
        return new Deck(createCardSet());
    }

}