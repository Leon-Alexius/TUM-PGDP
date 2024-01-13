package Klausur_3.AboutIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOTools {
    /**
     * Appends a String to a file (append the content to the end of the file)
     * @param content string
     * @param filePath file
     * @param usingBuffer boolean
     */
    public static void append(String content, String filePath, Boolean usingBuffer) {
        try {
            if (usingBuffer) {
                //  passing true as the second argument to the FileWriter constructor to make sure that the FileWriter is in append mode
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                    bufferedWriter.write(content);
                }
            }
            else {
                Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * Writes a String to a file (the existing content of the file will be cleared - truncated and overwritten)
     * @param content string
     * @param filePath file
     * @param usingBuffer boolean
     */
    public static void write(String content, String filePath, Boolean usingBuffer){
        try {
            if (usingBuffer) {
                //  passing false or not provided, the file will be overwritten
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false))) {
                    bufferedWriter.write(content);
                }
            }
            else {
                Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * Appends multiple String to a file (append)
     * @param content Iterable of type CharBuffer, Segment, String, StringBuffer, StringBuilder
     * @param filePath String
     * @param usingBuffer Boolean
     */
    public static void appendLines(Iterable<? extends CharSequence> content, String filePath, Boolean usingBuffer){
        try {
            if (usingBuffer) {
                //  passing true as the second argument to the FileWriter constructor to make sure that the FileWriter is in append mode
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                    for (CharSequence line : content) {
                        bufferedWriter.write(line.toString());
                        bufferedWriter.newLine();  // to write each string on a new line
                    }
                }
            }
            else {
                Files.write(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * Write multiple String to a file (truncate and overwrite)
     * @param content Iterable of type CharBuffer, Segment, String, StringBuffer, StringBuilder
     * @param filePath String
     * @param usingBuffer Boolean
     */
    public static void writeLines(Iterable<? extends CharSequence> content, String filePath, Boolean usingBuffer){
        try {
            if (usingBuffer) {
                //  passing false or not provided, the file will be overwritten
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false))) {
                    for (CharSequence line : content) {
                        bufferedWriter.write(line.toString());
                        bufferedWriter.newLine();  // to write each string on a new line
                    }
                }
            }
            else {
                Files.write(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * reads a file specified and returns the lines from the file as a Stream
     * @param filePath String
     * @param usingBuffer Boolean
     * @return Stream&lt;String&gt;
     */
    public static Stream<String> read_toStream(String filePath, Boolean usingBuffer){
        try {
            if (usingBuffer) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                    return bufferedReader.lines();
                }
            }
            else {
                return Files.lines(Path.of(filePath));
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

    /**
     * reads a file specified and returns the lines from the file as a List
     * @param filePath String
     * @param usingBuffer Boolean
     * @return List&lt;String&gt;
     */
    public static List<String> read_toList(String filePath, Boolean usingBuffer){
        try {
            if (usingBuffer) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                    return bufferedReader.lines().toList();
                }
            }
            else {
                return Files.readAllLines(Path.of(filePath));
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

    /**
     * reads a file specified and returns a String containing the content read from the file
     * @param filePath String
     * @param usingBuffer Boolean
     * @return String
     */
    public static String read_toString(String filePath, Boolean usingBuffer){
        try {
            if (usingBuffer) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                    /*
                    bufferedReader.readLine() only reads a single line from the file

                    reads all lines from the BufferedReader and joins them together into a single String,
                    with each line separated by a newline character
                     */
                    return bufferedReader.lines().collect(Collectors.joining("\n"));
                }
            }
            else {
                return Files.readString(Path.of(filePath));
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

    /**
     * copy a specified file content to the other file specified
     * @param originalFilePath String
     * @param copyFilePath String
     * @param usingBuffer Boolean
     */
    public static void copyFile(String originalFilePath, String copyFilePath, Boolean usingBuffer) {
        // modified von Vorlesung EiDI
        try {
            if (usingBuffer) {
                // Using BufferedReader and BufferedWriter
                try (BufferedReader reader = new BufferedReader(new FileReader(originalFilePath));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(copyFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } else {
                // Using Files.lines and Files.write
                try (Stream<String> stream = Files.lines(Path.of(originalFilePath))) {
                    Iterable<String> it = stream::iterator;
                    Files.write(Path.of(copyFilePath), it);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a file's line count (with flag to determine whether trailing empty line should be counted in or not)
     * @param filePath String
     * @return long
     */
    public static long calculateFileLine(String filePath, boolean considerTrailingLine){
        try {
            if (considerTrailingLine) {
                // BufferedReader.lines() includes trailing empty lines
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    return reader.lines().count();
                }
            } else {
                // does not consider trailing empty lines as a line
                try (Stream<String> lines = Files.lines(Path.of(filePath))) {
                    return lines.count();
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read a file and separate each n-Lines into an Array
     * <br>
     * With n = 3 and a file with 5 Lines = <code>{ {line 1, line 2, line 3}, {line 4, line 5, null} }</code>
     * <br>
     * <strong>Notes</strong>: trailing new Line won't be added to return value
     * @param filePath String
     * @param n int
     * @return List&lt;String[]&gt;
     */
    public static List<String[]> readSpecial(String filePath, int n) {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = new String[n];
                lines[0] = line;
                for (int i = 1; i < n; i++) {
                    lines[i] = br.readLine();
                }
                result.add(lines);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // Examples
    public static void main(String[] args) {
        // https://docs.oracle.com/javase/8/docs/api/java/lang/CharSequence.html
        List<String> animals = new ArrayList<>();
        animals.add("Dog");
        animals.add("Lion");
        animals.add("Tiger");

        appendLines(animals, "src/Klausur_3/AboutIO/Dummy.txt", true);

        List<String[]> l = readSpecial("src/Klausur_3/AboutIO/Dummy.txt", 3);
        l.forEach(i -> System.out.println(Arrays.toString(i)));
    }
}
