package vendingmachine;

import vendingmachine.Snacks.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class MachineEngine {
    private List<Item> candies;
    private List<Item> chips;
    private List<Item> chocolates;
    private List<Item> drinks;
    private List<Item> allSnacks;
    private Map<String,Integer> shoppingList;
    private ArrayList<Double> cashType = new ArrayList<>();
    private ArrayList<Integer> cashQuantity = new ArrayList<>();

    public MachineEngine() {
        allSnacks=new ArrayList<>();
        //init candy
        candies = new ArrayList<>();
        List<String> candy_info = readFile("Candy");
        for (String s : candy_info) {
            int itemCode=Integer.parseInt(s.split(",")[0]);
            String name = s.split(",")[1];
            int stock = Integer.parseInt(s.split(",")[2]);
            double price = Double.parseDouble(s.split(",")[3]);
            int sold=Integer.parseInt(s.split(",")[4]);

            Item candy = new Candy(name, stock, price,itemCode,sold);
            candies.add(candy);
            allSnacks.add(candy);

        }

        //init chip
        chips = new ArrayList<>();
        List<String> chip_info = readFile("Chip");
        for (String s : chip_info) {
            int itemCode=Integer.parseInt(s.split(",")[0]);
            String name = s.split(",")[1];
            int stock = Integer.parseInt(s.split(",")[2]);
            double price = Double.parseDouble(s.split(",")[3]);
            int sold=Integer.parseInt(s.split(",")[4]);
            Item chip = new Chip(name, stock, price,itemCode,sold);
            chips.add(chip);
            allSnacks.add(chip);
        }
        //init chocolate
        chocolates = new ArrayList<>();
        List<String> chocolate_info = readFile("Chocolate");
        for (String s : chocolate_info) {
            int itemCode=Integer.parseInt(s.split(",")[0]);
            String name = s.split(",")[1];
            int stock = Integer.parseInt(s.split(",")[2]);
            double price = Double.parseDouble(s.split(",")[3]);
            int sold=Integer.parseInt(s.split(",")[4]);
            Item chocolate = new Chocolate(name, stock, price,itemCode,sold);
            chocolates.add(chocolate);
            allSnacks.add(chocolate);
        }
        //init drink
        drinks = new ArrayList<>();
        List<String> drink_info = readFile("Drink");
        for (String s : drink_info) {
            int itemCode=Integer.parseInt(s.split(",")[0]);
            String name = s.split(",")[1];
            int stock = Integer.parseInt(s.split(",")[2]);
            double price = Double.parseDouble(s.split(",")[3]);
            int sold=Integer.parseInt(s.split(",")[4]);
            Item drink = new Drink(name, stock, price,itemCode,sold);
            drinks.add(drink);
            allSnacks.add(drink);
        }
        // init shopping list
        this.shoppingList = readShoppingList();
    }

    public static Map<String, Integer> readShoppingList(){
        Map<String, Integer> items = new HashMap<>();
        String fileName = "src/main/java/vendingmachine/ShoppingList.txt";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                items.put(tempString.split(",")[0],Integer.parseInt(tempString.split(",")[1]) );
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void updateShoppingList(){
        List<String> toWrite = new ArrayList<>();
        try {
            for (String s : this.shoppingList.keySet()) {
                String info = s + "," +this.shoppingList.get(s);
                toWrite.add(info);
            }
            Files.write(Paths.get("src/main/java/vendingmachine/ShoppingList.txt"), toWrite);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String name) {
        List<String> info = new ArrayList<>();
        String fileName = "src/main/java/vendingmachine/Snacks/Database/" + name + ".txt";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                info.add(tempString);
            }
            reader.close();
            return info;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateDatabase(List<Item> items) {
        String fileName = "src/main/java/vendingmachine/Snacks/Database/" + items.get(0).getCategoryName() + ".txt";
        try {
            List<String> replaced = new ArrayList<>();
            for (Item item : items) {
                String info = item.getItemCode()+","+item.getName() + "," + item.getStock() + "," + item.getPrice()+","+item.getNumOfSold();
                replaced.add(info);
            }
            Files.write(Paths.get(fileName), replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        updateDatabase(candies);
        updateDatabase(chips);
        updateDatabase(chocolates);
        updateDatabase(drinks);
        updateShoppingList();
    }

    public List<Item> getCandies() {
        return this.candies;
    }

    public List<Item> getChips() {
        return this.chips;
    }

    public List<Item> getChocolates() {
        return this.chocolates;
    }

    public List<Item> getDrinks() {
        return this.drinks;
    }

    public void changeSnackPrice(String name, double price) {
        for (Item c : allSnacks) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.changePrice(price);
                return;
            }
        }
    }

    public void cashIn(String type, int quantity) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            List<String> replaced = new ArrayList<>();
            for (String line : lines) {
                if (type.equals(line.split(",")[0])) {
                    replaced.add(type + "," + (Integer.parseInt(line.split(",")[1]) + quantity));
                } else {
                    replaced.add(line);
                }
            }
            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"), replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cashOut(String type, int quantity) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            List<String> replaced = new ArrayList<>();

            for (String line : lines) {
                if (type.equals(line.split(",")[0])) {
                    if(Integer.parseInt(line.split(",")[1]) >= quantity){
                        replaced.add(type + "," + (Integer.parseInt(line.split(",")[1]) - quantity));
                    }else{
                        replaced.add(line);
                        System.out.println("cashOut failed, no enough money in the Vending machine");
                    }
                } else {
                    replaced.add(line);
                }

            }
            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"), replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String changes(double amount, double totalPrice) {

        BigDecimal in = new BigDecimal(Double.toString(amount));
        BigDecimal fact = new BigDecimal(Double.toString(totalPrice));
        BigDecimal sub = in.subtract(fact);
        String ret = "";
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            String[][] changeList = new String[lines.size()][2];
            for (int i = 0; i < lines.size(); i++) {
                cashType.add(i, Double.parseDouble(lines.get(i).split(",")[0]));
                cashQuantity.add(i, Integer.parseInt(lines.get(i).split(",")[1]));
            }


            for (int i = 0; i < cashType.size(); i++) {
                BigDecimal b2 = new BigDecimal(Double.toString(cashType.get(i)));
                changeList[i][0] = String.valueOf(cashType.get(i));
                if ((sub.compareTo(b2) >= 0) && cashQuantity.get(i) > 0) {
                    if (Integer.parseInt(sub.divide(b2).setScale(0, RoundingMode.DOWN).toString()) >= cashQuantity.get(i)) {
                        changeList[i][1] = String.valueOf(cashQuantity.get(i));
                    } else {
                        changeList[i][1] = sub.divide(b2).setScale(0, RoundingMode.DOWN).toString();
                    }
                } else {
                    changeList[i][1] = "0";
                }
                BigDecimal b3 = new BigDecimal(changeList[i][1]);
                sub = sub.subtract(b2.multiply(b3));


            }

            if (sub.compareTo(new BigDecimal("0")) == 0) {
                for (int i = 0; i < changeList.length; i++) {
                    cashOut(changeList[i][0], Integer.parseInt(changeList[i][1]));
                    ret += changeList[i][0] + "$ x" + changeList[i][1] + '\n';
                }

            } else {
                ret = "fail";
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;


    }

    public Map<String,Integer> getShoppingList(){
        return this.shoppingList;
    }

    public boolean add_cart(String name, int amount){
        Item product=null;
        for(Item item:allSnacks){
            if(item.getName().equals(name)){
                product=item;
            }
        }
        if (product != null){
            if(shoppingList.containsKey(product.getName())){
                int stock=product.getStock();
                int amountInList=shoppingList.get(product.getName());
                if(stock<amount+amountInList){
                    return false;
                }else{
                    shoppingList.replace(product.getName(),amount+amountInList);
                    return true;
                }
            }else{
                int stock=product.getStock();
                if(stock<amount){
                    return false;
                }else{
                    shoppingList.put(product.getName(),amount);
                    return true;
                }
            }
        } else {
            return false;
        }
    }
    public void cancel(){
        shoppingList=new HashMap<>();
        refreshShoppingList();
    }

    public static void refreshShoppingList(){
        String writeIn = "";
        File f = new File("src/main/java/vendingmachine/ShoppingList.txt");
        try(FileWriter fr = new FileWriter(f, false)){
            char[] chars = writeIn.toCharArray();
            fr.write(chars);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void checkoutCart(){
        for(Item item: allSnacks){
            if(shoppingList.containsKey(item.getName())){
                item.consume(shoppingList.get(item.getName()));
            }
        }
        shoppingList=new HashMap<>();
    }

    public String[] getCashTypes(){
        String[] ret = null;
        List<Double> types = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            for (String line : lines) {
                types.add(Double.parseDouble(line.split(",")[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ret = new String[types.size()];
        types.sort(Comparator.naturalOrder());
        for (int i =0; i<types.size(); i++){
            ret[i] = types.get(i) + "";
        }
        return ret;
    }

    public String[][] getCashData(){
        String[][] ret = null;
        List<Double> types = new ArrayList<>();
        Map<Double, Integer> data = new HashMap<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            for (String line : lines) {
                types.add(Double.parseDouble(line.split(",")[0]));
                data.put(Double.parseDouble(line.split(",")[0]), Integer.parseInt(line.split(",")[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ret = new String[types.size()][2];
        types.sort(Comparator.naturalOrder());
        for (int i =0; i<types.size(); i++){
            ret[i][0] = types.get(i) + "";
            ret[i][1] = data.get(types.get(i))+"";
        }

        return ret;
    }

    public int getCashQuantity(String type) {
        int cashQuantity;
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            for (String line : lines) {
                if (type.equals(line.split(",")[0])) {
                    cashQuantity = Integer.parseInt(line.split(",")[1]);
                    return cashQuantity;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("invalid type");
        return -1;
    }

    public void setCashQuantity(String type,int cashQuantity){

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            List<String> replaced = new ArrayList<>();
            for (String line : lines) {
                if (type.equals(line.split(",")[0])) {
                    replaced.add(type + "," + String.valueOf(cashQuantity));
                } else {
                    replaced.add(line);
                }
            }
            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"), replaced);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getPrice(String name){
        for(Item item:allSnacks){
            if(name.equals(item.getName())){
                return item.getPrice();
            }
        }
        return 0;
    }
    public double totalAmount(){
        double total=0.0;
        for(String key: shoppingList.keySet()){
            total+=getPrice(key)*shoppingList.get(key);
        }
        return total;
    }

    public void addCashType(String type,int cashQuantity){

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            List<String> replaced = new ArrayList<>();
            for (String line : lines) {
                if (type.equals(line.split(",")[0])) {
                    System.out.println("The type is existing");
                    return;
                }
            }
            lines.add(type + "," + String.valueOf(cashQuantity));
            for(int i = 0;i<lines.size();i++){
                for(int j = i+1;j<lines.size();j++){
                    if(Double.parseDouble(lines.get(i).split(",")[0])<Double.parseDouble(lines.get(j).split(",")[0])){
                        String temp = lines.get(i);
                        lines.set(i,lines.get(j));
                        lines.set(j,temp);
                    }
                }

            }
            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"), lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCashType(String type){

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            List<String> replaced = new ArrayList<>();
            for (String line : lines) {
                if (!type.equals(line.split(",")[0])) {
                    replaced.add(line);
                }
            }
            Files.write(Paths.get("src/main/java/vendingmachine/Cash.txt"), replaced);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllSnacks(){
        return this.allSnacks;
    }

    public String[] getItemName(){
        int length=getAllSnacks().size();
        String[] info=new String[length];
        for(int i=0;i<length;i++){
            info[i]=getAllSnacks().get(i).getName();
        }
        return info;
    }

    public String[] getItemPrice(){
        int length = getAllSnacks().size();
        String[] info=new String[length];
        for(int i=0;i<length;i++){
            info[i]=String.valueOf(getAllSnacks().get(i).getPrice());
        }
        return info;
    }

    public String[] getItemStock(){
        int length= getAllSnacks().size();
        String[] info=new String[length];
        for(int i=0;i<length;i++){
            info[i]=String.valueOf(getAllSnacks().get(i).getStock());
        }
        return info;
    }

    public Item getItem(String name){
        Item item=null;
        for(Item i: this.allSnacks){
            if(i.getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return null;
    }

    public void availableChangesList(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Cash.txt"));
            for (String line : lines) {

                    System.out.println(line);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean refill(String name, int amount){
        for(Item i:this.allSnacks){
            if(name.equalsIgnoreCase(i.getName())){
                if(amount>15 || amount<0){
                    return false;
                }
                i.refill(amount);
                return true;
            }
        }
        return false;
    }
// 0:valid / 1:item has existed / 2:negative price / 3:amount not in range of 0 to 15 / 4: invalid category
    public int addItem(String category, String name, double price,int stock){
        if(category.equalsIgnoreCase("candy")){
            int i=checkNewItemValid(name, price, stock);
            if(i==0){
                Item item=new Candy(name,stock,price,this.candies.size()+1001,0);
                candies.add(item);
                allSnacks.add(item);
                return 0;
            }else{
                return i;
            }
//            if (checkNewItemValid(name, price, stock)) return;


        }else if(category.equalsIgnoreCase("chip")){
            int i=checkNewItemValid(name, price, stock);
            if(i==0){
                Item item=new Chip(name,stock,price,chips.size()+2001,0);
                chips.add(item);
                allSnacks.add(item);
                return 0;

            }else {
                return i;
            }
//            if (checkNewItemValid(name, price, stock)) return;

        }else if(category.equalsIgnoreCase("chocolate")){
            int i=checkNewItemValid(name, price, stock);
            if(i==0){
                Item item=new Chocolate(name,stock,price,chocolates.size()+3001,0);
                chocolates.add(item);
                allSnacks.add(item);
            }else{
                return i;
            }
//            if (checkNewItemValid(name, price, stock)) return;


        }else if(category.equalsIgnoreCase("drink")){
            int i=checkNewItemValid(name, price, stock);
            if(i==0){
                Item item=new Drink(name,stock,price,drinks.size()+4001,0);
                drinks.add(item);
                allSnacks.add(item);
            }else{
                return i;
            }
//            if () return;


        }else{
            return 4;
        }
        return 4;
    }

    private int checkNewItemValid(String name, double price, int stock) {
        for(Item item: allSnacks){
            if(item.getName().equalsIgnoreCase(name)){
                return 1;
            }
        }
        if(price<=0){
            return 2;
        }
        if(stock<=0 || stock>15){
            return 3;
        }
        return 0;
    }

    public void recordTransaction(String itemList,String paymentMethod,double moneyPaid,double totalPrice){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String saleTime = dateFormat.format( now );
        String changes;
        if(paymentMethod == "Cash"){
            changes = this.changes(moneyPaid,totalPrice);
        }else{
            changes = "0";
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Record.txt"));
            List<String> replaced = new ArrayList<>();
            for (String line : lines) {
                    replaced.add(line);
            }
            String newLine = new String();
            newLine =saleTime +"&" + itemList + "&"+ moneyPaid + "$&" + changes+
                    "$&"+ paymentMethod;
            replaced.add(newLine);
            Files.write(Paths.get("src/main/java/vendingmachine/Record.txt"), replaced);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[][] getTransactionRecord(){

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/vendingmachine/Record.txt"));
            String[][] transactionRecord = new String[lines.size()][5];
            for (int i = 0; i<lines.size();i++) {
                transactionRecord[i][0]= lines.get(i).split("&")[0];
                transactionRecord[i][1]= lines.get(i).split("&")[1];
                transactionRecord[i][2]= lines.get(i).split("&")[2];
                transactionRecord[i][3]= lines.get(i).split("&")[3];
                transactionRecord[i][4]= lines.get(i).split("&")[4];
            }
            return transactionRecord;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateItemReport(){
        String fileName = "src/main/java/vendingmachine/Snacks/Database/" + "ItemReport" + ".txt";
        try {
            List<String> replaced = new ArrayList<>();
            String candyCategory="Candy: ";
            replaced.add(candyCategory);
            for (Item item : this.candies) {
//                String category="Candy: ";

                if(item.getStock()>0){
                    String info = item.getItemCode()+"; "+item.getName() + "; " + item.getStock() + "; " + item.getPrice()+"; "+item.getNumOfSold();
                    replaced.add(info);
                }

            }
            String chipCategory="Chips: ";
            replaced.add(chipCategory);
            for (Item item : this.chips) {
//                String category="Chips: ";
//                replaced.add(category);
                if(item.getStock()>0){
                    String info = item.getItemCode()+"; "+item.getName() + "; " + item.getStock() + "; " + item.getPrice()+"; "+item.getNumOfSold();
                    replaced.add(info);
                }

            }
            String chocolateCategory="Chocolate: ";
            replaced.add(chocolateCategory);
            for (Item item : this.chocolates) {
//                String category="Chocolate: ";
//                replaced.add(category);
                if(item.getStock()>0){
                    String info = item.getItemCode()+"; "+item.getName() + "; " + item.getStock() + "; " + item.getPrice()+"; "+item.getNumOfSold();
                    replaced.add(info);
                }

            }
            String drinkCategory="Drink: ";
            replaced.add(drinkCategory);
            for (Item item : this.drinks) {
//                String category="Drink: ";
//                replaced.add(category);
                if(item.getStock()>0){
                    String info = item.getItemCode()+"; "+item.getName() + "; " + item.getStock() + "; " + item.getPrice()+"; "+item.getNumOfSold();
                    replaced.add(info);
                }

            }
            Files.write(Paths.get(fileName), replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

