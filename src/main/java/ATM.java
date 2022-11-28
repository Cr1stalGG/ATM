import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        String cardNumber, password;
        double amount;
        Server server = new Server();
        boolean isLogIn = false;
        String StartMenu = """
        ATM SYSTEM
        1. Register client
        2. Log in
        3. Quit
        """;

        String clientsMenu= """
        ATM SYSTEM
        1. Check balance    
        2. Put money
        3. Get money
        4. Quit
        """;

        byte choose;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println(StartMenu);
            choose=scanner.nextByte();

            switch (choose){
                case 1:

                    System.out.println("Input card number: ");
                    cardNumber = scanner.next();

                    System.out.println("Input password: ");
                    password = scanner.next();

                    server.register(cardNumber, password);
                    break;
                case 2:
                    System.out.println("Input card number: ");
                    cardNumber = scanner.next();

                    System.out.println("Input password: ");
                    password = scanner.next();

                    if(server.logIn(cardNumber, password))
                        isLogIn=true;

                case 3:
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }
        }while(choose != 3 && !isLogIn);

        choose = 0;

        do{
            System.out.println(clientsMenu);
            choose = scanner.nextByte();

            switch (choose){
                case 1:
                    System.out.println("Input card number: ");
                    cardNumber = scanner.next();

                    System.out.println(server.getAmount(cardNumber));
                    break;
                case 2:
                    System.out.println("Input card number: ");
                    cardNumber = scanner.next();

                    System.out.println("Input amount: ");
                    amount = scanner.nextDouble();
                    server.putMoney(cardNumber, amount);
                    break;
                case 3:
                    System.out.println("Input card number: ");
                    cardNumber = scanner.next();

                    System.out.println("Input password: ");
                    password = scanner.next();

                    System.out.println("Input amount: ");
                    amount = scanner.nextDouble();

                    server.getMoney(cardNumber, password, amount);
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }


        }while (isLogIn && choose != 4);

    }
}
