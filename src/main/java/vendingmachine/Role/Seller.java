package vendingmachine.Role;

import vendingmachine.Log.User;
import vendingmachine.MachineEngine;
import vendingmachine.Snacks.Item;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private MachineEngine machineEngine;
    public Seller(String type, String userName, String password,MachineEngine machineEngine) {
        super(type, userName, password);
        this.machineEngine=machineEngine;

    }
    public void changePrice(String name, double price){
        machineEngine.changeSnackPrice(name,price);
    }
    public String[] getItemName(){
        return this.machineEngine.getItemName();
    }

    public String[] getItemPrice(){
        return this.machineEngine.getItemPrice();
    }

    public String[] getItemStock(){
        return this.machineEngine.getItemStock();
    }

    public boolean refill(String name, int amount){
        return this.machineEngine.refill(name,amount);
    }

    public void addItem(String category, String name, double price,int stock){
        this.machineEngine.addItem(category,name,price,stock);
    }

    public void display(){
        for(Item i: this.machineEngine.getAllSnacks()){
            i.display();
        }
    }
}
