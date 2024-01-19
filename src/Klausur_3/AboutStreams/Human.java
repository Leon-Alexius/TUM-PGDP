package Klausur_3.AboutStreams;

import java.util.Random;

public class Human{
    private final String name;
    private final int age;
    private final Ethnic ethnic;

    public Human(String name, int age){
        this.name = name;
        this.age = age;
        this.ethnic = getRandomEthnic();
    }

    public static Human[] getFriends(Human human){
        Random random = new Random((new Random()).nextInt());
        Human[] friends = new Human[random.nextInt(3) + 1]; // at least 1 and at most 3

        for (int i = 0; i < friends.length; i++) {
            friends[i] = new Human(getRandomName(), human.age + random.nextInt(3));
        }
        return friends;
    }

    public static String getRandomName() {
        String[] NAMES = {"Alice", "Bob", "Charlie", "Dave", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy"};
        Random random = new Random();
        int randomIndex = random.nextInt(0, NAMES.length);
        return NAMES[randomIndex];
    }

    public static Ethnic getRandomEthnic(){
        Ethnic[] ethnics = Ethnic.values();
        Random random = new Random();
        int randomIndex = random.nextInt(ethnics.length);
        return ethnics[randomIndex];
    }

    public enum Ethnic {
        ASIAN,
        AMERICAN,
        AUSTRALIAN,
        AFRICAN,
        EUROPEAN,
    }

    public int getAge() {
        return age;
    }

    public Ethnic getEthnic() {
        return ethnic;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Ethnic: " + ethnic;
    }
}
