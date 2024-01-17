package Fruit_Basket;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class Basket implements Iterable<Fruit> {
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
        Random random = new Random();

        // Generate random Bananas
        for(int i = 0; i < 10; i++){
            if(random.nextInt(0, 100) <= 50){
                basket.addFruit(new Banana(Taste.SWEET, 1.99, new Date()));
            }
            else {
                basket.addFruit(new Banana(Taste.SOUR, 0.99, new Date()));
            }
        }

        // see inside of Basket
        System.out.println(basket);

        // check if a fruit is a Banana, if yes, then get if it is sweet or not
        for(Fruit fruit : basket){
            if(fruit instanceof Banana){
                System.out.println(((Banana) fruit).isSweet());
            }
        }
    }
}
