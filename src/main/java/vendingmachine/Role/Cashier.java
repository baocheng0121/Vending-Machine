package vendingmachine.Role;

import vendingmachine.Log.User;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Cashier extends User {
    private String userName;
    private String password;
    private String type;

    public Cashier( String userName, String password) {
        super("Cashier", userName, password);
        this.userName=userName;
        this.password=password;
        this.type="Cashier";
    }
    public  void modifyCash(String type,int quantity){
        Map<String,Integer> map=new HashMap<>();
        List<String> newQuantity=new ArrayList<>();
        try{
            List<String> lines= Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));

            for(String line:lines){
                map.put(line.split(",")[0],Integer.parseInt(line.split(",")[1]));
                
            }
            map.put(type,quantity);
            for(String s:map.keySet()){
                newQuantity.add(s+","+map.get(s));
            }

            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"),newQuantity);
            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"),availableCashReport());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  List<String>  availableCashReport(){
        Map<Double,Integer> cashMap=new HashMap<>();
        List<String> cashList=new ArrayList<>();
        List<Double> list=new ArrayList<>();
        try{
            List<String> lines= Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            for(String line:lines){
                list.add(Double.parseDouble(line.split(",")[0]));

                cashMap.put(Double.parseDouble(line.split(",")[0]),Integer.parseInt(line.split(",")[1]));

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        list.sort((a,b)->b.compareTo(a));

//        Collections.sort(list);
//        Collections.reverse(list);
        System.out.println(list);
        for (int i = 0; i <list.size() ; i++) {


            cashList.add(list.get(i)+","+cashMap.get(list.get(i)));
        }


        return cashList;


    }



}
