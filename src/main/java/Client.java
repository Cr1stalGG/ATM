public class Client {
    private DBController dbController = new DBController();
    private String cardNumber;
    private String password;
    public Client(String cardNumber, String password) {
        if (dbController.isCardValid(cardNumber)) {
            this.cardNumber = cardNumber;
            this.password = password;
            dbController.write(String.format("%s %s %s",cardNumber, password, 0.0));
            System.out.println("Success!");
        } else {
            System.out.println("Invalid cardNumber");
        }
    }
}
