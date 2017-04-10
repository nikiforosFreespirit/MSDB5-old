package msdb5.game.card.set;

import msdb5.game.card.Card;
import org.junit.After;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by nikiforos on 29/08/15.
 */
public abstract class CardSetFactoryTest {

    private final CardSet mockCardSet;

    CardSetFactoryTest(CardSetFactory cardSetFactoryTestObject) {
        this.mockCardSet = cardSetFactoryTestObject.create();
    }

    CardSet getMockCardSet() {
        return mockCardSet;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(mockCardSet.toString());
    }

    @Test
    public void testOnSize() throws Exception {
        int cardSetSize = mockCardSet.getCardSet().size();
        assertTrue("Deck size should be greater than 0", cardSetSize > 0);
        testOnConcreteSize(cardSetSize);
    }

    protected abstract void testOnConcreteSize(int cardSetSize);

    @Test
    public void testOnContent() throws Exception {
        assertNotNull(mockCardSet);
        assertNotNull(mockCardSet.getCardSet());
        assertFalse(mockCardSet.isEmpty());
        Stream<Card> cards = mockCardSet.getCardSet().stream();
        assertTrue(cards.allMatch(c -> c.isValid()));
    }

    @Test
    public void testAllCardsAreDifferent() throws Exception {
        Collection<?> cards = mockCardSet.getCardSet();

        Object[] cardArray = cards.toArray();
        for (int i = 0; i < cardArray.length; i++) {
            Object cardToSearch = cardArray[i];
            for (int j = i + 1; j < cardArray.length; j++) {
                Object cardInSearch = cardArray[j];
                boolean cardIsFound = cardToSearch.equals(cardInSearch);
                String message = cardToSearch + " in index " + i + " was found in game in index " + j;
                assertFalse(message, cardIsFound);
            }
        }
    }
}