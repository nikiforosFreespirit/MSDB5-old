package table;

import deck.Deck;
import player.Player;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTable {
    private Deck deck;
    private Player[] players;

    public GameTable() {
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
