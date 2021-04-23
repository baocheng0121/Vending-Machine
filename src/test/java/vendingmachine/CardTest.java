package vendingmachine;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    Card card1 = new Card("10000", "DMHR");
    Card card2 = new Card("10001", "WBC");
    Card card3 = new Card("10000", "KJ");
    Card card4 = new Card("1", "TJF");


    @Test
    public void testCard() {
        assertNotNull(card1);
        assertNotNull(card2);
        assertNotNull(card3);
    }

    @Test
    public void testIsValid() {
        assertTrue(card1.isValid());//test valid card
        assertTrue(card2.isValid());
        assertFalse(card4.isValid());//test invalid card
        assertFalse(card3.isValid());
    }

    @Test
    public void testGetNo() {
        assertEquals("10000", card1.getCardNo());
        assertEquals("10001", card2.getCardNo());
    }

    @Test
    public void testSetNo() {
        card4.setCardNo("10001");
        assertEquals("10001", card4.getCardNo());

    }

    @Test
    public void testGetName() {
        assertEquals("DMHR", card1.getName());
        assertEquals("WBC", card2.getName());

    }

    @Test
    public void testSetName() {
        assertFalse(card3.isValid());
        card3.setName("DMHR");
        assertEquals("DMHR", card3.getName());


    }
}