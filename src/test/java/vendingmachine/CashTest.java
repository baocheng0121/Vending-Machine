package vendingmachine;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class CashTest{
    Cash cash=new Cash();
    @Test
    public void testCash(){
        assertNotNull(cash);
    }

    @Test
    public void testReceiveInvalidMoney(){
        assertFalse(cash.receiveMoney(0.0));
        assertFalse(cash.receiveMoney(13.0));
        assertFalse(cash.receiveMoney(-50.0));
        assertFalse(cash.receiveMoney(1000.0));
        assertFalse(cash.receiveMoney(0.3));

    }

    @Test
    public void testReceiveValidMoney(){
        assertTrue(cash.receiveMoney(100.0));
        assertTrue(cash.receiveMoney(50.0));
        assertTrue(cash.receiveMoney(20.0));
        assertTrue(cash.receiveMoney(10.0));
        assertTrue(cash.receiveMoney(5.0));
        assertTrue(cash.receiveMoney(2.0));
        assertTrue(cash.receiveMoney(1.0));
        assertTrue(cash.receiveMoney(0.5));
        assertTrue(cash.receiveMoney(0.2));
        assertTrue(cash.receiveMoney(0.1));
    }

    @Test
    public void testGetTotal(){
        assertEquals(cash.getTotal(), 0.0, 1);
        cash.receiveMoney(50);
        assertEquals(cash.getTotal(), 50.0, 1);
        cash.receiveMoney(0.5);
        assertEquals(cash.getTotal(), 50.5, 1);
    }

}