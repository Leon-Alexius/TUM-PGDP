package Fruit_Basket;

import java.util.Date;

public abstract class Fruit {
    protected Date expiryDate;

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public abstract double getExpiryDuration();

    public abstract String toString();
}
