import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        final String path = "src/main/resources/database.txt";
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> list = new ArrayList<>();

        while(scanner.hasNext()){
            list.add(scanner.nextLine() + "\n");
        }
        System.out.println(list);



    }
}
