import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<String, Account> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public ATM() {
        loadAccounts();
    }

    private void loadAccounts() {
        try (Scanner fileScanner = new Scanner(new java.io.File("accounts.txt"))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                accounts.put(data[0], new Account(data[0], data[1], Double.parseDouble(data[2])));
            }
        } catch (Exception e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public void start() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();

        if (!accounts.containsKey(accNum)) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Account account = accounts.get(accNum);
        if (!account.authenticate(password)) {
            System.out.println("Authentication failed.");
            return;
        }

        System.out.println("Login successful!");

        boolean quit = false;
        while (!quit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Quit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.printf("Balance: $%.2f%n", account.getBalance());
                    break;
                case "2":
                    System.out.print("Enter amount: ");
                    double deposit = Double.parseDouble(scanner.nextLine());
                    account.deposit(deposit);
                    System.out.println("Deposited.");
                    break;
                case "3":
                    System.out.print("Enter amount: ");
                    double withdraw = Double.parseDouble(scanner.nextLine());
                    if (account.withdraw(withdraw)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "4":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        System.out.println("Thank you for using the ATM.");
    }
}
