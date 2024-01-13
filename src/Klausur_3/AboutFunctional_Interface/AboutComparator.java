package Klausur_3.AboutFunctional_Interface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AboutComparator {
    // https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html

    // A helper class (record)
    public record Player(String name, int achievedPoints) {
        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", achievedPoints=" + achievedPoints +
                    '}';
        }
    }

    /*
    ====================================================================================================================
                                           SIMPLE SORTING (ONE NOTE)
    ====================================================================================================================
     */

    /**
     * Sort Descending using Lambda
     */
    private static void sortUsingLambda(List<Player> players){
        // Sort using a lambda expression
        players.sort((Player p1, Player p2) -> p2.achievedPoints - p1.achievedPoints);

        System.out.println("Sorted players using lambda: " + players);
    }

    /**
     * Sort Descending using Comparator; we need to use .reversed()
     */
    private static void sortUsingComparator(List<Player> players){
        // Sort using a Comparator
        players.sort(Comparator.comparing(Player::achievedPoints).reversed());

        System.out.println("Sorted players using Comparator: " + players);
    }

    /*
    ====================================================================================================================
                                                 CHAINED SORTING
                 This example will show why usingComparator is much cleaner and better than Lambda
    ====================================================================================================================
     */

    /**
     * Sort based on Score, then Alphabet (given same Score) using Lambda
     */
    private static void chainedSort_Lambda(List<Player> players){
        // Sort using a lambda expression
        players.sort((Player p1, Player p2) -> {
            if (p2.achievedPoints != p1.achievedPoints) {
                return p2.achievedPoints - p1.achievedPoints;
            }
            else {
                return p1.name().compareTo(p2.name());
            }
        });

        System.out.println("Sorted players using lambda: " + players);
    }

    /**
     * Sort based on Score, then Alphabet (given same Score) using Comparator
     * <br>
     * we use <code>.thenComparing()</code> to chain multiple Comparator instances
     */
    private static void chainedSort_Comparator(List<Player> players){
        // Sort using a Comparator
        players.sort(Comparator.comparing(Player::achievedPoints).reversed().thenComparing(Player::name));

        System.out.println("Sorted players using Comparator: " + players);
    }

    /*
    ====================================================================================================================
                                                     TEST HERE
    ====================================================================================================================
     */
    public static void main(String[] args) {
        List<Player> players = Arrays.asList(
                new Player("Alice", 100),
                new Player("Bob", 200),
                new Player("Charlie", 150)
        );

        sortUsingLambda(players);
        sortUsingComparator(players);

        List<Player> players1 = Arrays.asList(
                new Player("Bob", 200),
                new Player("Alice", 200),
                new Player("Charlie", 150)
        );

        chainedSort_Lambda(players1);
        chainedSort_Comparator(players1);
    }
}
