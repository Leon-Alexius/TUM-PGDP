package Fruit_Basket;

import java.util.Date;

public class Banana extends Fruit implements Fruit_Interface {
    private final Taste taste;
    private double price;
    private static int id = 0;
    private final int bananaId; // final attributes can't be changed after a value is assigned to them

    public Banana(Taste taste, double price, Date expiryDate) {
        super(expiryDate); // super() call must be first
        this.taste = taste;
        this.price = price;
        this.bananaId = ++id;
    }

    // Example usage of Enumerator Method
    public String isSweet(){
        if(taste.isSweet()){
            return "This Banana with ID: " + this.bananaId + " is Sweet";
        }
        else {
            return "This Banana with ID: " + this.bananaId + " is Not Sweet";
        }
    }

    @Override
    public double getExpiryDuration() {
        return 0;
    }

    @Override
    public Taste getTaste() {
        return this.taste;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Banana with ID: " + this.bananaId;
    }
}
