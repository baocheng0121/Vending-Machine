package vendingmachine.Snacks;

public class Candy implements Item{
    private String name;
    private int stock;
    private double price;
    private String category;
    private int itemCode;
    private int numOfSold;

    public Candy(String name, int stock, double price,int itemCode, int numOfSold){
        this.name=name;
        this.stock = stock;
        this.price = price;
        this.category="Candy";
        this.itemCode=itemCode;
        this.numOfSold=numOfSold;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String getCategoryName() {
        return this.category;
    }

    public int getStock(){
        return this.stock;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public int getItemCode() {
        return this.itemCode;
    }

    @Override
    public int getNumOfSold() {
        return this.numOfSold;
    }

    public void refill(int amount) {
        this.stock = amount;
    }

    public void changePrice(double newPrice) {
        this.price = newPrice;
    }

    public boolean isOutOfStock() {
        return this.stock <= 0;
    }

    public void display(){
        System.out.print(getName());
        for (int i = 0; i<15-getName().length(); i++){
            System.out.print(" ");
        }
        System.out.print("$"+getPrice() + "       ");
        if (isOutOfStock()){
            System.out.println("out of stock");
        }else{
            System.out.println(getStock() + " left");
        }
    }

    @Override
    public void consume(int amount) {
        this.stock-=amount;
        this.numOfSold+=amount;
    }

}
