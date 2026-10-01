import java.io.*;
import java.util.*;

public class FileManager {
    private static final String DIR = "data";

    public static void initialize() {
        new File(DIR).mkdirs();
        String[] files = {
            "books.txt", "members.txt", "issues.txt",
            "reservations.txt"
        };
        for (String name : files) {
            try {
                new File(DIR, name).createNewFile();
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }
    }

    public static List<String> read(String name) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(DIR + "/" + name))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        return lines;
    }

    public static void write(String name, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(DIR + "/" + name))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}