package Klausur_3.AboutExceptions;

import java.util.Date;

public class Moldy extends Expired {

    // an Exception that inherits another Exception (Expired)
    public Moldy(Date expirationDate) {
        super(expirationDate);
    }

    // this will be used by the inherited .getMessage() from Expired.java
    @Override
    public String toString() {
        return "Fruit is moldy! Don't eat!";
    }
}
