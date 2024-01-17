package Fruit_Basket;

import java.util.Date;

public abstract class Fruit {
    protected Date expiryDate;

    // any child constructor must receive Date expiryDate parameter
    public Fruit(Date expiryDate){
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    // abstract methods
    public abstract double getExpiryDuration();

    public abstract String toString();
}
