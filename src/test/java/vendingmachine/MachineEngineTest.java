package vendingmachine;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

public class MachineEngineTest {
    @Test
    public void testReadFile(){
        List<String> info=MachineEngine.readFile("Candy");
        assertEquals(3,info.size());
        assertEquals("Mentos",info.get(0).split(",")[1]);

    }

    @Test
    public void testConstructor(){
        MachineEngine machineEngine=new MachineEngine();
        assertEquals(3,machineEngine.getCandies().size());
        assertNotNull(machineEngine.getChips());
        assertNotNull(machineEngine.getChocolates());
        assertNotNull(machineEngine.getDrinks());
    }

    @Test
    public void testUpDate(){
        MachineEngine machineEngine=new MachineEngine();
        machineEngine.changeSnackPrice("Mentos",6.0);
        MachineEngine.updateDatabase(machineEngine.getCandies());
        MachineEngine m=new MachineEngine();
        assertEquals("6.0",String.valueOf(m.getCandies().get(0).getPrice()));
        machineEngine.changeSnackPrice("Mentos",5.5);
        MachineEngine.updateDatabase(machineEngine.getCandies());

    }
    @Test
    public void testAddToCart1(){
        MachineEngine machineEngine=new MachineEngine();
        machineEngine.add_cart("Mentos",8);
        machineEngine.add_cart("Mentos",7);
        machineEngine.checkoutCart();
        assertEquals(0,machineEngine.getCandies().get(0).getStock());
    }
    @Test
    public void testAddToCart2(){
        MachineEngine machineEngine=new MachineEngine();
        machineEngine.add_cart("Mentos",2);
        machineEngine.add_cart("Mentos",1);
        assertEquals(1,machineEngine.getShoppingList().size());
        machineEngine.checkoutCart();
        assertEquals(4,machineEngine.getCandies().get(0).getStock());
        machineEngine.add_cart("Mentos",1);
        machineEngine.add_cart("Mentos",6);
        machineEngine.checkoutCart();
        assertEquals(3,machineEngine.getCandies().get(0).getStock());
    }
    @Test
    public void testAddToCart3(){
        /* checking the spelling error, it won't raise error */
        MachineEngine machineEngine=new MachineEngine();
        try {
            machineEngine.add_cart("Mento", 2);
        } catch(NullPointerException e){
            assertFalse(true);
        }
    }
    @Test
    public void testCancel(){
        MachineEngine machineEngine=new MachineEngine();
        machineEngine.add_cart("Mentos",6);
        int amount=machineEngine.getShoppingList().get("Mentos");
        assertEquals(6,amount);
        machineEngine.cancel();
        assertEquals(0,machineEngine.getShoppingList().size());
    }


    @Test
    public void testGetPrice(){
        MachineEngine machineEngine=new MachineEngine();
        assertEquals(5.5,machineEngine.getPrice("Mentos"),1);
    }

    @Test
    public void testAddItem(){
        MachineEngine machineEngine=new MachineEngine();
        int num=machineEngine.getAllSnacks().size();
        machineEngine.addItem("aslkjglka","a",1,1);
        assertEquals(num,machineEngine.getAllSnacks().size());

        machineEngine.addItem("candy","a",-1,-1);
        assertEquals(num,machineEngine.getAllSnacks().size());

        machineEngine.addItem("candy","a",1,-1);
        assertEquals(num,machineEngine.getAllSnacks().size());

        machineEngine.addItem("candy","mentos",1,1);
        assertEquals(num,machineEngine.getAllSnacks().size());

        machineEngine.addItem("candy","a",1,1);
        assertEquals(num+1,machineEngine.getAllSnacks().size());

        machineEngine.addItem("chip","b",1,1);
        assertEquals(num+2,machineEngine.getAllSnacks().size());

        machineEngine.addItem("chocolate","c",1,1);
        assertEquals(num+3,machineEngine.getAllSnacks().size());

        machineEngine.addItem("drink","d",1,1);
        assertEquals(num+4,machineEngine.getAllSnacks().size());

    }

    @Test
    public void getItemTest(){
        MachineEngine machineEngine=new MachineEngine();
        assertEquals(null,machineEngine.getItem("adsdf"));
        assertEquals("Mentos",machineEngine.getItem("mentos").getName());

    }

    @Test
    public void generateReport(){
        MachineEngine machineEngine=new MachineEngine();
        assertNotNull(machineEngine);
        machineEngine.generateItemReport();


    }
}
