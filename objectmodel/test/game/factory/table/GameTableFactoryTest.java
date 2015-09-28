package game.factory.table;

import game.elements.cardset.Deck;
import game.elements.player.Player;
import game.elements.table.GameTable;
import org.junit.After;
import org.junit.Test;

import static game.elements.cardset.Deck.DECK_DEFAULT_SIZE;
import static game.elements.table.GameTable.NUMBER_OF_PLAYERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by nikiforos on 30/08/15.
 */
public class GameTableFactoryTest {

    final GameTable outputGameTable;
    private final GameTableFactory gameTableFactoryTestObject;

    public GameTableFactoryTest() {
        this(new GameTableFactory());
    }

    GameTableFactoryTest(GameTableFactory gameTableFactoryTestObject) {
        this.gameTableFactoryTestObject = gameTableFactoryTestObject;
        outputGameTable = gameTableFactoryTestObject.create();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("GameTableFactoryTest: showing table after creation");
        System.out.println(outputGameTable.toString());
    }

    @Test
    public void testCreation() throws Exception {
        assertNotNull(outputGameTable);
    }

    @Test
    public void testOnDeck() throws Exception {
        // test for the created game
        Deck tableDeck = outputGameTable.getDeck();
        assertNotNull(tableDeck);
        testOnDeckSize(tableDeck.getCardSet().size());
    }

    void testOnDeckSize(int deckSize) {
        assertEquals("The size of the deck should be " + DECK_DEFAULT_SIZE, deckSize, DECK_DEFAULT_SIZE);
    }

    @Test
    public void testOnPlayers() throws Exception {
        // test for the created players
        Player[] players = outputGameTable.getPlayers();
        assertNotNull(players);
        int numberOfPlayers = players.length;
        assertEquals("Number of players should be " + NUMBER_OF_PLAYERS, numberOfPlayers, NUMBER_OF_PLAYERS);
    }
}