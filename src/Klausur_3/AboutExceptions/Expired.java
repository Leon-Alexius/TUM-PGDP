package Klausur_3.AboutExceptions;

import java.util.Date;

public class Expired extends Exception {
    private Date expirationDate;
    private Date currentDate;
    private final boolean customMessage;

    // default constructor
    public Expired(Date expirationDate) {
        this.expirationDate = expirationDate;
        this.currentDate = new Date();
        this.customMessage = false;
    }

    // Suppose the caller wants to use another custom message
    // https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html
    public Expired(String errorMessage) {
        super(errorMessage); // set custom Message in Exception
        this.customMessage = true;
    }

    /**
     * this will be printed out when an instance of Expired is thrown to System.out.println()
     * <br>
     * Example: <code>System.out.println(new Expired("aaa"));</code> "aaa" doesn't play any role here
     * @return default String value
     */
    public String toString() {
        return "Fruit has expired! Expiration date is: " + expirationDate + ", current Date: " + currentDate;
    }

    @Override
    public String getMessage() {
        if(customMessage){
            // get custom message saved in Exception
            return super.getMessage();
        }
        else{
            // get default message
            return toString();
        }
    }
}
