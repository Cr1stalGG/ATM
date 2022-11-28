import java.util.Objects;

public class Server {
    DBController dbController = new DBController();

    void register(String cardNumber, String password){
        new Client(cardNumber, password);
    }
    boolean logIn(String cardNumber, String password){
        if(!Objects.equals(dbController.returnDataByCardnumber(cardNumber), "-1")) {
            String[] data = dbController.returnDataByCardnumber(cardNumber).split(" ");
            if (data[1].equals(password)) {
                System.out.println("Success!");
                return true;
            } else {
                System.out.println("Incorrect input data");
                return false;
            }
        } else {
            return false;
        }
    }
    double getAmount(String cardNumber){
        double amount = 0;
        String[] data = dbController.returnDataByCardnumber(cardNumber).split(" ");
        amount = Double.parseDouble(data[2]);
        return amount;
    }

    void putMoney(String cardNumber, double amount){
        if (amount < 1000000 && amount > 0)
            dbController.updateClientsAmount(cardNumber, getAmount(cardNumber)+amount);
        else
            System.out.println("Incorrect amount");
    }

    void getMoney(String cardNumber, String password, double amount){
        if(!Objects.equals(dbController.returnDataByCardnumber(cardNumber), "-1")) {
            String[] data = dbController.returnDataByCardnumber(cardNumber).split(" ");
            if (data[1].equals(password)) {
                System.out.println("Success!");
                if (amount < getAmount(cardNumber) && amount > 0)
                    dbController.updateClientsAmount(cardNumber, getAmount(cardNumber) - amount);
                else
                    System.out.println("Incorrect amount");
            } else {
                System.out.println("Incorrect input data");
            }
        }
    }

}
