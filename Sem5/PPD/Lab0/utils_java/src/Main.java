import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void createFile(String path, int size, int min, int max) {
        Random random = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i=0; i<size; i++) {
                int x = random.nextInt(max-min + 1);
                x += min;
                writer.write(x + "\n");
            }

            System.out.println(size + " integers in range " + "["+min + ", " + max+ "] have been written to: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean compareFiles(String path1, String path2) {

        List<Double> file1 = new ArrayList<>();
        List<Double> file2 = new ArrayList<>();

        try (BufferedReader reader1 = new BufferedReader(new FileReader(path1)); BufferedReader reader2 = new BufferedReader(new FileReader(path2))) {
            String line = null;
            while ((line = reader1.readLine()) != null ) {
                String[] list = line.split(" ");
                for (String s : list) {
                    file1.add(Double.parseDouble(s));
                }
            }
            while ((line = reader2.readLine()) != null ) {
                String[] list = line.split(" ");
                for (String s : list) {
                    file2.add(Double.parseDouble(s));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file1.size() != file2.size()) {
            return false;
        }

        for (int i=0; i<file1.size(); i++) {
            if (!file1.get(i).equals(file2.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
//        createFile("test.txt", 10, -10, 10);
        System.out.println(compareFiles("test.txt", "test2.txt"));
    }
}
