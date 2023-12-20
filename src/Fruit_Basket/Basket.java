package Fruit_Basket;

import java.util.Arrays;
import java.util.Date;

public class Basket {
    /*
    Example usage:

    Basket basket = new Basket();
    Date date = new Date();
    Fruit aFruit = new Banana("sweet", 100, date);
    basket.addFruit(aFruit);

    System.out.println(basket); // [Banana with ID: 1]
     */
    private Fruit[] fruits;
    private int fruitsCount;

    public Basket(){
        this.fruits = new Fruit[0];
        this.fruitsCount = 0;
    }

    public void addFruit(Fruit fruit){
        // better just use ArrayList so that we can also remove fruitsCount using ArrayList.size()
        Fruit[] newFruits = new Fruit[fruits.length + 1];
        System.arraycopy(fruits, 0, newFruits, 0, fruits.length);
        newFruits[fruitsCount++] = fruit;
        fruits = newFruits;
    }

    @Override
    public String toString(){
        return Arrays.toString(fruits);
    }

    public static void main(String[] args) {
        Basket basket = new Basket();
        Fruit fruit = new Banana("sweet", 1.99, new Date());
        basket.addFruit(fruit);
        System.out.println(basket); // [Banana with ID: 1]
    }
}
