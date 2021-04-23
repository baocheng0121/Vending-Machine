package vendingmachine;
import org.junit.Test;
import static org.junit.Assert.*;
public class CashManagementTest {

    @Test
    public void cashInTest(){
        MachineEngine engine = new MachineEngine();
        int cashBefore = engine.getCashQuantity("20.0");
        engine.cashIn("20.0",10);
        assertEquals(engine.getCashQuantity("20.0"),cashBefore+10);
        engine.setCashQuantity("20.0",cashBefore);
    }

    @Test
    public void cashOutTest(){
        MachineEngine engine = new MachineEngine();
        int cashBefore = engine.getCashQuantity("20.0");
        engine.cashOut("20.0",100);
        assertEquals(cashBefore,engine.getCashQuantity("20.0"));
        engine.cashOut("20.0",1);
        assertEquals(cashBefore-1,engine.getCashQuantity("20.0"));
        engine.setCashQuantity("20.0",cashBefore);
    }

    @Test
    public void changesTest1(){
        MachineEngine engine = new MachineEngine();
        int before = engine.getCashQuantity("1.0");
        engine.changes(20.0,19.0);
        assertEquals(before-1,engine.getCashQuantity("1.0"));
        engine.setCashQuantity("1.0",before);
    }

    @Test
    public void changesTest2(){
        MachineEngine engine = new MachineEngine();
        int before = engine.getCashQuantity("1.0");
        engine.changes(20.0,21.0);
        assertEquals(before,engine.getCashQuantity("1.0"));
        engine.setCashQuantity("1.0",before);
    }

    @Test
    public void changesTest3(){
        MachineEngine engine = new MachineEngine();
        engine.addCashType("800.0",10);
        assertEquals(10,engine.getCashQuantity("800.0"));
        engine.removeCashType("800.0");
    }

    @Test
    public void changesTest4(){
        MachineEngine engine = new MachineEngine();
        int quantity = engine.getCashQuantity("100.0");
        engine.addCashType("100.0",10);
        assertEquals(quantity,engine.getCashQuantity("100.0"));
    }

    @Test
    public void changesTest5(){
        MachineEngine engine = new MachineEngine();
        int before = engine.getCashQuantity("100.0");
        engine.changes(2000.0,100.0);
        assertEquals(before,engine.getCashQuantity("100.0"));

    }

}
