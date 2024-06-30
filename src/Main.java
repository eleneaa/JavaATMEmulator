import Exeption.DailyLimitExceededException;
import Exeption.InsufficientFundsException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException, DailyLimitExceededException {
        ATM atm = new ATM(500, 500, 500);
        User user = new User(null, null);
        int amount;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello!\n"+
                "To perform operations, you need to log in.\n" +
                "Enter a username:");
        String username = scanner.nextLine();
        System.out.println("Enter a password:");
        String password = scanner.nextLine();

        while (true) {
            if (User.Login(username, password)) {
                user.username = username;
                user.password = password;
                atm.currentUser = user;
                break;
            } else{
                System.out.println("Enter a username:");
                username = scanner.nextLine();
                System.out.println("Enter a password:");
                password = scanner.nextLine();
            }
        }

        System.out.println("You can choose the appropriate operation.\n" +
                "Write down the operation number.\n" +
                "1) Withdraw money\n" +
                "2) Deposit money\n" +
                "3) Logout");

        int operation = scanner.nextInt();

        switch (operation){
            case 1:
                System.out.println("Enter the amount:");
                amount = scanner.nextInt();
                atm.withdraw(amount, user);
                break;
            case 2:
                System.out.println("Enter the amount:");
                amount = scanner.nextInt();
                atm.deposit(amount);
                break;
            case 3:
                user.Logout();
                break;
        }
    }
}