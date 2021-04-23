package vendingmachine;
import org.junit.Test;
import vendingmachine.Role.Cashier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class CashierTest {
    Cashier cashier=new Cashier("cashier","cashier");

    @Test
    public void testModifyCash() {
        MachineEngine engine = new MachineEngine();
        int before = engine.getCashQuantity("10.0");
        cashier.modifyCash("10.0",5);
        List<String> cashList;
        cashList=new ArrayList<>();
        cashList=cashier.availableCashReport();
        boolean temp=false;
        for(String s:cashList){
            if(s.equals("10.0,5")){
                temp=true;
            }
        }
//        cashier.availableCashReport();
        assertTrue(temp);


        engine.setCashQuantity("10.0",before);


    }

    @Test
    public void testCashier() {
        assertNotNull(cashier);
    }

}
