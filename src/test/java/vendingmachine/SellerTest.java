package vendingmachine;

import org.junit.Test;
import vendingmachine.Role.Seller;

import static org.junit.Assert.*;
import java.util.List;
public class SellerTest {
    @Test
    public void changePriceTest(){
        MachineEngine machineEngine=new MachineEngine();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
        assertEquals(5.5,machineEngine.getCandies().get(0).getPrice(),1);
        seller.changePrice("Mentos",5.0);
        assertEquals(5.0,machineEngine.getCandies().get(0).getPrice(),1);
        machineEngine.changeSnackPrice("Mentos",5.5);
        assertEquals(5.5,machineEngine.getCandies().get(0).getPrice(),1);
    }

    @Test
    public void getItemNameTest(){
        MachineEngine machineEngine=new MachineEngine();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
//        assertEquals(16,machineEngine.getItemName().length);
        assertEquals("Mentos",seller.getItemName()[0]);
    }

    @Test
    public void getItemPriceTest(){
        MachineEngine machineEngine=new MachineEngine();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
//        assertEquals(16,machineEngine.getItemPrice().length);
        assertEquals("5.5",seller.getItemPrice()[0]);
    }

    @Test
    public void getItemStockTest(){
        MachineEngine machineEngine=new MachineEngine();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
//        assertEquals(16,machineEngine.getItemStock().length);
        assertEquals("7",seller.getItemStock()[0]);
    }



    @Test
    public void refillItemTest1(){
        MachineEngine machineEngine=new MachineEngine();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
        seller.refill("Mentos",1);
        assertEquals(1,machineEngine.getCandies().get(0).getStock());
        assertFalse(machineEngine.refill("Mentos",-1));
        assertFalse(machineEngine.refill("Mentos",16));
    }

    @Test
    public void displayTest(){
        MachineEngine machineEngine=new MachineEngine();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
        seller.display();

    }

    @Test
    public void testAddItem(){
        MachineEngine machineEngine=new MachineEngine();
        int num=machineEngine.getAllSnacks().size();
        Seller seller=new Seller("Seller","abc","123",machineEngine);
        seller.addItem("chip","a",1,1);
        assertEquals(num+1,machineEngine.getAllSnacks().size());

    }
}
