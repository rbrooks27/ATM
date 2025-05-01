import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Class to handle login and account type selections for the ATM.
 */
public class OptionMenu extends Account {

    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Integer> data = new HashMap<>(); // Maps Customer Number to PIN
    int selection;
    Random random = new Random();

    /* Validate login information for customer number and pin number */
    public void getLogin() throws IOException {
        int x = 1;

        do {
            try {
                // Dummy data for customers (customer number, pin)
                data.put(9876543, 9876);
                data.put(8989898, 1890);
                data.put(2000000, 2005);

                // Prompt user: login or create account
                System.out.println("Welcome to Ray's ATM Company!");
                System.out.println("1: Login");
                System.out.println("2: Create a New Account");
                System.out.print("Selection: ");
                int loginOption = menuInput.nextInt();

                if (loginOption == 1) {
                    // Existing account login
                    System.out.print("Please Enter Your Customer Number: ");
                    setCustomerNumber(menuInput.nextInt());

                    System.out.print("Please Enter Your Pin Number: ");
                    setPinNumber(menuInput.nextInt());

                    // Check if customer number and pin match
                    if (data.containsKey(getCustomerNumber()) && data.get(getCustomerNumber()) == getPinNumber()) {
                        getAccountType();
                        return; // Exit the method upon successful login
                    } else {
                        System.out.println("\nWrong Customer Number or Pin Number.\n");
                    }
                } else if (loginOption == 2) {
                    createNewAccount();
                } else {
                    System.out.println("Invalid option. Please try again.\n");
                }

            } catch (Exception e) {
                // Handle invalid input (non-numeric values)
                System.out.println("\nInvalid character(s). Only numbers allowed.\n");
                x = 2;
                menuInput.next(); // Clear invalid input
            }

        } while (x == 1);
    }

    /* Create a new account */
    public void createNewAccount() {
        System.out.println("\n--- Create a New Account ---");

        // Generate a unique customer number
        int newCustomerNumber = generateUniqueCustomerNumber();
        System.out.println("Your new Customer Number is: " + newCustomerNumber);

        // Generate a new PIN number
        int newPinNumber = generatePinNumber();
        System.out.println("Your new PIN Number is: " + newPinNumber);

        // Store the new account details
        data.put(newCustomerNumber, newPinNumber);

        System.out.println("\nAccount created successfully!");
        System.out.println("Please login with your new credentials.\n");

        // Optionally, you can store more account details or initialize balances here
    }

    /* Generate a unique 7-digit customer number */
    private int generateUniqueCustomerNumber() {
        int customerNumber;
        do {
            customerNumber = 1000000 + random.nextInt(9000000); // Generates a number between 1000000 and 9999999
        } while (data.containsKey(customerNumber));
        return customerNumber;
    }

    /* Generate a random 4-digit PIN number */
    private int generatePinNumber() {
        return 1000 + random.nextInt(9000); // Generates a number between 1000 and 9999
    }

    /* Display account type menu and handle user selection */
    public void getAccountType() {
        System.out.println("\nSelect the Account you want to access:");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Savings Account");
        System.out.println("Type 3 - Exit");
        System.out.print("Choice: ");

        try {
            selection = menuInput.nextInt();

            switch (selection) {
                case 1:
                    getChecking();
                    break;
                case 2:
                    getSaving();
                    break;
                case 3:
                    System.out.println("Thank you for using Ray's ATM Company!\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Choice\n");
                    getAccountType();
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input. Please enter a number.\n");
            menuInput.next(); // Clear invalid input
            getAccountType();
        }
    }

    /* Handle checking account operations */
    public void getChecking() {
        System.out.println("\n--- Checking Account ---");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        try {
            selection = menuInput.nextInt();

            switch (selection) {
                case 1:
                    System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
                    getAccountType();
                    break;
                case 2:
                    getCheckingWithdrawInput();
                    getAccountType();
                    break;
                case 3:
                    getCheckingDepositInput();
                    getAccountType();
                    break;
                case 4:
                    System.out.println("Thank you for using Ray's ATM Company!\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Choice\n");
                    getChecking();
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input. Please enter a number.\n");
            menuInput.next(); // Clear invalid input
            getChecking();
        }
    }

    /* Handle saving account operations */
    public void getSaving() {
        System.out.println("\n--- Savings Account ---");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        try {
            selection = menuInput.nextInt();

            switch (selection) {
                case 1:
                    System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
                    getAccountType();
                    break;
                case 2:
                    getSavingWithdrawInput();
                    getAccountType();
                    break;
                case 3:
                    getSavingDepositInput();
                    getAccountType();
                    break;
                case 4:
                    System.out.println("Thank you for using Ray's ATM Company!\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid Choice\n");
                    getSaving();
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input. Please enter a number.\n");
            menuInput.next(); // Clear invalid input
            getSaving();
        }
    }
}
