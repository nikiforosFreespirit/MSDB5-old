package deck;

import org.junit.Before;

/**
 * Created by nikiforos on 23/08/15.
 */
public class SideDeckTest extends DeckTest {

    @Before
    public void setUp() throws Exception {
        this.setTestObjectAbstractDeck(new SideDeck());
    }

}