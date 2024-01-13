package Klausur_3.AboutExceptions;

import java.util.Date;

public class Person {
    public static void main(String[] args) {
        // a Fruit that is safe to eat
        Fruit fruit = new Fruit(new Date(1000, 1, 20)); // replaced by Calendar.set(year + 1900, month, date)

        // an expired Fruit
        Fruit expiredFruit = new Fruit(new Date(0, 1, 20)); // replaced by Calendar.set(year + 1900, month, date)

        /*
        check if Moldy
        try {
            fruit.checkIfMoldy();
            expiredFruit.checkIfMoldy();
        }
        catch (Moldy moldy){
            System.out.println(moldy.getMessage());
        }
        */

        // try to eat (2 Exceptions: Expired and Moldy)
        try {
            fruit.eat();
            expiredFruit.eat();
        }
        catch (Expired e) {
            // using the parent Exception (Expired) to also catch Moldy
            System.out.println(e.getMessage());
        }

        // suppose there is another exception that is not inherited like (Moldy -> Expired)
        // catch (anotherException | Expired exception) {}
    }
}
