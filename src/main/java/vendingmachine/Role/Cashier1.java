package vendingmachine.Role;

import vendingmachine.Log.User;
import vendingmachine.MachineEngine;

public class Cashier1 extends User {
    private MachineEngine machineEngine;
    public Cashier1(String userName, String password,MachineEngine machineEngine) {
        super("Cashier", userName, password);
        this.machineEngine = machineEngine;
    }
    public void setCashQuantity(String type, int quantity){
        machineEngine.setCashQuantity(type,quantity);
    }

    public void addCashType(String type,int quantity){
        machineEngine.addCashType(type,quantity);
    }
    public void removeCashType(String type){
        machineEngine.removeCashType(type);
    }
    public void showCashList(){
        machineEngine.availableChangesList();
    }
    public String[][] showTransactionList(){
        return machineEngine.getTransactionRecord();
    }
}
