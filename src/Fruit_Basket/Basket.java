package Fruit_Basket;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class Basket implements Iterable<Fruit> {
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

    @Override
    public Iterator<Fruit> iterator() {
        return new BasketIterator();
    }

    private class BasketIterator implements Iterator<Fruit>{
        int index = 0;
        @Override
        public boolean hasNext() {
            return index < fruits.length;
        }

        @Override
        public Fruit next() {
            return fruits[index++];
        }
    }

    public static void main(String[] args) {
        Basket basket = new Basket();

        for(int i = 0; i < 5; i++){
            basket.addFruit(new Banana("sweet", 1.99, new Date()));
        }

        /*
        [Banana with ID: 1, Banana with ID: 2, Banana with ID: 3, Banana with ID: 4, Banana with ID: 5]
         */
        System.out.println(basket);

        /*
        Banana with ID: 1
        Banana with ID: 2
        Banana with ID: 3
        Banana with ID: 4
        Banana with ID: 5
         */
        for(Fruit fruit : basket){
            System.out.println(fruit);
        }
    }
}
