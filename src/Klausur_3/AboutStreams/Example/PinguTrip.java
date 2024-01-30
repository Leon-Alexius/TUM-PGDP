package Klausur_3.AboutStreams.Example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final public class PinguTrip {
    private PinguTrip() {} // To hide constructor in utility class.

    /**
     * 4.0;11.5 <br>
     * // comment <br>
     * 19.1;3.2 <br>
     * --- <br>
     * 9.11;1.1 <br>
     * <br>
     * returns: Stream.of(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2));

     */
    private static Stream<WayPoint> readWayPoints(String pathToWayPoints) {
        try {
            return Files.lines(Path.of(pathToWayPoints))
                    .takeWhile(line -> !line.equals("---"))
                    .filter(line -> !line.startsWith("//"))
                    .map(WayPoint::ofString); // line -> WayPoint.ofString(line)
        }
        catch (IOException ioe) {
            return Stream.empty();
        }
    }

    /**
     * List.of(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2), new WayPoint(2.1, 7.4));
     * <br><br>
     * Stream.of(new OneWay(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2)), new OneWay(new WayPoint(19.1, 3.2), new WayPoint(2.1, 7.4)))
     */
    private static Stream<OneWay> transformToWays(List<WayPoint> wayPoints) {
        return IntStream.range(0, wayPoints.size() - 1) // 0, 1, 2, 3, ...
                .mapToObj(i -> new OneWay(wayPoints.get(i), wayPoints.get(i + 1))); // wayPoints(0), wayPoints(1)
    }

    /**
     * Simple example of .sum() usage which is only available in DoubleStream
     */
    private static double pathLength(Stream<OneWay> oneWays) {
        return oneWays.mapToDouble(OneWay::getLength).sum();
    }

    /**
     * get Average of the length of the OneWays in the list (default = 0.0) <br>
     * takeWhile current oneWay.getLength() <= average
     */
    private static List<OneWay> kidFriendlyTrip(List<OneWay> oneWays) {
        double average = oneWays.stream().mapToDouble(OneWay::getLength).average().orElse(0.0);
        return oneWays.stream().takeWhile(way -> way.getLength() <= average).toList();
    }

    /**
     * Get the WayPoint with max distance from home (input parameter), default value = home
     */
    public static WayPoint furthestAwayFromHome(Stream<WayPoint> wayPoints, WayPoint home) {
        return wayPoints.max(Comparator.comparingDouble(wayOne -> wayOne.distanceTo(home))).orElse(home);
    }

    /**
     * Example usage of anyMatch
     */
    public static boolean onTheWay(Stream<OneWay> oneWays, WayPoint visit) {
        return oneWays.anyMatch(oneWay -> oneWay.isOnPath(visit));
    }

    /**
     * Usage of Collectors.joining(): <br>
     * concatenates the input elements, separated by the specified delimiter, in encounter order.
     */
    public static String prettyDirections(Stream<OneWay> oneWays) {
        return oneWays.map(OneWay::prettyPrint).collect(Collectors.joining("\n"));
    }

    /**
     * Test Here
     */
    public static void main(String[] args) {
        List<WayPoint> wayPoints = readWayPoints("src/Klausur_3/AboutStreams/Example/Path.txt").toList();
        System.out.println(wayPoints);
        // List.of(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2));

        List<OneWay> oneWays = transformToWays(wayPoints).toList();
        System.out.println(oneWays);
        // List.of(new OneWay(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2)));

        double length = pathLength(oneWays.stream());
        System.out.println(length);
        // 17.230 ...

        List<OneWay> kidFriendly = kidFriendlyTrip(oneWays);
        System.out.println(kidFriendly);
        // List.of(new OneWay(new WayPoint(4.0, 11.5), new WayPoint(19.1, 3.2)));

        WayPoint furthest = furthestAwayFromHome(wayPoints.stream(), wayPoints.get(0));
        System.out.println(furthest);
        // new WayPoint(19.1, 3.2);

        boolean onTheWay = onTheWay(oneWays.stream(), new WayPoint(0.0, 0.0));
        System.out.println(onTheWay);
        // false

        onTheWay = onTheWay(oneWays.stream(), new WayPoint(19.1, 3.2));
        System.out.println(onTheWay);
        // true

        String directions = prettyDirections(oneWays.stream());
        System.out.println(directions);
        // "25 Schritte Richtung 331 Grad."
    }

}
