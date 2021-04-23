package vendingmachine.Snacks;

public interface Item {

    String getName();
    String getCategoryName();
    int getStock();
    double getPrice();
    int getItemCode();
    int getNumOfSold();

    void refill(int amount);
    void changePrice(double price);

    boolean isOutOfStock();

    void display();
    void consume(int amount);


}
