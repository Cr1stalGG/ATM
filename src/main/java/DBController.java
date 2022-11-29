import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBController {
    private final byte cardLength = 19;
    private final String path = "src/main/resources/database.txt";
    private File file= new File(path);

    private BufferedWriter writer;
    Scanner scanner;

    public List<String> getDataBase() {
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
        return list;

    }
    boolean isDigid(String word){
        boolean isOnlyDigits = true;
        for(int i = 0; i < word.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(word.charAt(i))) {
                isOnlyDigits = false;
                break;
            }
        }
        return isOnlyDigits;
    }
    boolean isCardValid(String cardNumber) {
        List<String> numbers = List.of(cardNumber.split("-"));
        boolean isAllNum = true;
        boolean isAllLen = true;
        for(String num : numbers){
            if(!isDigid(num))
                isAllNum = false;
            if(num.length() != 4)
                isAllLen = false;
        }
        if (cardNumber.length() == cardLength && numbers.size() == 4 && isAllNum && isAllLen)
            return true;
        else
            return false;
    }
    public void write(String clientsData){
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            System.out.println("Something goes wrong:");
            e.printStackTrace();
        }
        try {
            writer.write(clientsData+"\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Something goes wrong:");
            e.printStackTrace();
        }
    }

    public String returnDataByCardnumber(String cardNumber){
        String data = "-1";
        for (String s : getDataBase()) {
            String[] els = s.split(" ");
            if (cardNumber.equals(els[0])) {
                data = s;
                return data;
            }
        }

        return data;
    }

    public void updateClientsAmount(String cardNumber, double amount) {

        List<String> list = new ArrayList<>();
        for (String s : getDataBase()) {
            String[] els = s.split(" ");
            if (cardNumber.equals(els[0])) {
                list.add(String.format("%s %s %s", els[0], els[1], amount));
            }else{
                list.add(s);
            }
        }
        try (FileOutputStream fos = new FileOutputStream(path, false)) { }
        catch (IOException e) {
            System.out.println("Something goes wrong:");
            e.printStackTrace();
        }

        for (String s : list)
            write(s);

    }
}
