package msdb5.game.table;

import msdb5.game.card.set.SideDeck;
import msdb5.game.player.MockClassicPlayer;
import msdb5.game.player.MockCowardPlayer;
import msdb5.game.player.MockUnwaveringPlayer;
import msdb5.game.player.Player;
import org.junit.After;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nikiforos on 31/08/15.
 */
public abstract class GameTableFactoryTest {

    private final GameTable mockGameTable;

    public GameTableFactoryTest(boolean isSideDeckUsed) {
        final Player[] mockPlayers = {new MockClassicPlayer(), new MockCowardPlayer(), new MockClassicPlayer(), new MockUnwaveringPlayer(), new MockClassicPlayer()};
        this.mockGameTable = new GameTableFactory(isSideDeckUsed).create(mockPlayers);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("GameTableFactoryTest: showing table after creation");
        System.out.println(mockGameTable.toString());
    }

    @Test
    public void testNumberOfPlayersCreated() throws Exception {
        assertEquals("Number of players should be " + 5, getMockGameTable().getPlayers().length, 5);
    }

    @Test
    public abstract void testPlayersHandsSize() throws Exception;

    public void verifyPlayersHandsSize(int expectedHandSize) throws Exception {
        Player[] players = this.getMockGameTable().getPlayers();
        assertTrue("Hand size should be of " + expectedHandSize + " for all players",
                Stream.of(players).
                        mapToInt(player -> player.getHand().size()).
                        allMatch(handSize -> handSize == expectedHandSize));
    }

    @Test
    public abstract void testSideDeckIsCreated() throws Exception;

    public void verifySideDeckSize(int size) {
        SideDeck sideDeck = this.getMockGameTable().getSideDeck();
        assertEquals("The size of the deck should be " + size, sideDeck.size(), size);
    }

    public final GameTable getMockGameTable() {
        return mockGameTable;
    }

}