package Fruit_Basket;

import java.util.Date;

public class Banana extends Fruit implements Fruit_Interface {
    private String taste;
    private double price;
    private static int id = 0;
    private int bananaId;

    public Banana(String taste, double price, Date expiryDate) {
        this.taste = taste;
        this.price = price;
        this.bananaId = ++id;
    }

    @Override
    public double getExpiryDuration() {
        return 0;
    }

    @Override
    public String getTaste() {
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
