package vendingmachine;

import org.junit.Test;
import vendingmachine.Snacks.*;

import static org.junit.Assert.*;
import java.util.List;
public class ItemTest {
    @Test
    public void testCandy(){
        Item item=new Candy("Mentos",7,5.5,1001,0);
        assertNotNull(item);
        assertEquals(1001,item.getItemCode());
        assertEquals("Mentos",item.getName());
        assertEquals("Candy",item.getCategoryName());
        assertEquals(7,item.getStock());
        assertEquals(5.5,item.getPrice(),1);
        item.changePrice(1.0);
        assertEquals(1.0,item.getPrice(),1);
        item.consume(7);
        assertEquals(7,item.getNumOfSold());
        assertEquals(0,item.getStock());
        assertTrue(item.isOutOfStock());
        item.display();
        item.refill(7);
        assertEquals(7,item.getStock());
        assertFalse(item.isOutOfStock());
        item.display();

    }

    @Test
    public void testChip(){
        Item item=new Chip("Mentos",7,5.5,1001,0);
        assertNotNull(item);
        assertEquals(1001,item.getItemCode());
        assertEquals("Mentos",item.getName());
        assertEquals("Chip",item.getCategoryName());
        assertEquals(7,item.getStock());
        assertEquals(5.5,item.getPrice(),1);
        item.changePrice(1.0);

        assertEquals(1.0,item.getPrice(),1);
        item.consume(7);
        assertEquals(7,item.getNumOfSold());
        assertEquals(0,item.getStock());
        assertTrue(item.isOutOfStock());
        item.display();
        item.refill(7);
        assertEquals(7,item.getStock());
        assertFalse(item.isOutOfStock());
        item.display();
    }
    @Test
    public void testChocolate(){
        Item item=new Chocolate("Mentos",7,5.5,1001,0);
        assertNotNull(item);
        assertEquals(1001,item.getItemCode());
        assertEquals("Mentos",item.getName());
        assertEquals("Chocolate",item.getCategoryName());
        assertEquals(7,item.getStock());
        assertEquals(5.5,item.getPrice(),1);
        item.changePrice(1.0);
        assertEquals(1.0,item.getPrice(),1);
        item.consume(7);
        assertEquals(7,item.getNumOfSold());
        assertEquals(0,item.getStock());
        assertTrue(item.isOutOfStock());
        item.display();
        item.refill(7);
        assertEquals(7,item.getStock());
        assertFalse(item.isOutOfStock());
        item.display();
    }
    @Test
    public void testDrink(){
        Item item=new Drink("Mentos",7,5.5,1001,0);
        assertNotNull(item);
        assertEquals(1001,item.getItemCode());
        assertEquals("Mentos",item.getName());
        assertEquals("Drink",item.getCategoryName());
        assertEquals(7,item.getStock());
        assertEquals(5.5,item.getPrice(),1);
        item.changePrice(1.0);
        assertEquals(1.0,item.getPrice(),1);
        item.consume(7);
        assertEquals(7,item.getNumOfSold());
        assertEquals(0,item.getStock());
        assertTrue(item.isOutOfStock());
        item.display();
        item.refill(7);
        assertEquals(7,item.getStock());
        assertFalse(item.isOutOfStock());
        item.display();
    }

}
