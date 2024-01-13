package Klausur_3.AboutExceptions;

import java.util.Date;
import java.util.Random;

// Reference: W09P01 - Passwortprüfung
public class Fruit {
    private final Date expirationDate;

    public Fruit(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // since Moldy inherits Expired, we don't need to declare "throws Expired, Moldy"
    public void eat() throws Expired {
        Date currentDate = new Date();

        // check if moldy
        this.checkIfMoldy();

        // check if expirationDate is still in the future
        if (currentDate.after(expirationDate)) {
            // throw new Expired(expirationDate); // Exception with default Message
            throw new Expired("Fruit has expired, don't eat!"); // Exception with custom Message
        }
        else {
            System.out.println("Fruit is safe to eat.");
        }
    }

    public void checkIfMoldy() throws Moldy{
        Random random = new Random();

        // randomly give mold to a Fruit
        if(random.nextInt(0, 100) >= 50){
            throw new Moldy(new Date());
        }
        else {
            System.out.println("Fruit is not moldy!");
        }
    }
}
