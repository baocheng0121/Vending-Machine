package vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Cash {

    private Map<Double,Integer> receiveMoney=new HashMap<>();
    private double total = 0.0;

    public Cash(){
        this.receiveMoney.put(100.0, 0);
        this.receiveMoney.put(50.0, 0);
        this.receiveMoney.put(20.0, 0);
        this.receiveMoney.put(10.0, 0);
        this.receiveMoney.put(5.0, 0);
        this.receiveMoney.put(2.0, 0);
        this.receiveMoney.put(1.0, 0);
        this.receiveMoney.put(0.5, 0);
        this.receiveMoney.put(0.2, 0);
        this.receiveMoney.put(0.1, 0);
    }

    public boolean receiveMoney(double money){
        if(receiveMoney.containsKey(money)){
            receiveMoney.put(money,receiveMoney.get(money)+1);
            this.total += money;
            return true;
        }else{
            return false;
        }
    }

    public int getLength(){
        return this.receiveMoney.keySet().size();
    }

    public String[] getTypes(){
        String[] cashes = new String[getLength()];
        int counter = 0;
        for (Double d: receiveMoney.keySet()){
            cashes[counter] = d+"";
            counter ++;
        }
        return cashes;
    }


    public double getTotal(){
        return this.total;
    }



}
