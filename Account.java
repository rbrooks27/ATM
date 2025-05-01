import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to manage bank account details and perform transactions like
 * deposits and withdrawals on both checking and saving accounts.
 */
public class Account {

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    /* Set the customer number */
    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    /* Get the customer number */
    public int getCustomerNumber() {
        return customerNumber;
    }

    /* Set the pin number */
    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    /* Get the pin number */
    public int getPinNumber() {
        return pinNumber;
    }

    /* Get the checking account balance */
    public double getCheckingBalance() {
        return checkingBalance;
    }

    /* Get the saving account balance */
    public double getSavingBalance() {
        return savingBalance;
    }

    /* Perform a withdrawal from the checking account */
    public double calcCheckingWithdraw(double amount) {
        checkingBalance -= amount; // Subtract the amount from checking balance
        return checkingBalance;
    }

    /* Perform a withdrawal from the saving account */
    public double calcSavingWithdraw(double amount) {
        savingBalance -= amount; // Subtract the amount from saving balance
        return savingBalance;
    }

    /* Perform a deposit to the checking account */
    public double calcCheckingDeposit(double amount) {
        checkingBalance += amount; // Add the amount to checking balance
        return checkingBalance;
    }

    /* Perform a deposit to the saving account */
    public double calcSavingDeposit(double amount) {
        savingBalance += amount; // Add the amount to saving balance
        return savingBalance;
    }

    /* Handle checking account withdrawal with input validation */
    public void getCheckingWithdrawInput() {
        System.out.println("\nChecking Account Balance: " + moneyFormat.format(getCheckingBalance()));
        System.out.print("Amount you want to withdraw from Checking Account: ");

        try {
            double amount = input.nextDouble();

            // Ensure the balance will not go negative
            if ((checkingBalance - amount) >= 0) {
                calcCheckingWithdraw(amount);
                System.out.println("New Checking Account Balance: " + moneyFormat.format(getCheckingBalance()) + "\n");
            } else {
                System.out.println("Insufficient funds. Balance cannot be negative.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.\n");
            input.next(); // Clear invalid input
        }
    }

    /* Handle saving account withdrawal with input validation */
    public void getSavingWithdrawInput() {
        System.out.println("\nSaving Account Balance: " + moneyFormat.format(getSavingBalance()));
        System.out.print("Amount you want to withdraw from Saving Account: ");

        try {
            double amount = input.nextDouble();

            // Ensure the balance will not go negative
            if ((savingBalance - amount) >= 0) {
                calcSavingWithdraw(amount);
                System.out.println("New Saving Account Balance: " + moneyFormat.format(getSavingBalance()) + "\n");
            } else {
                System.out.println("Insufficient funds. Balance cannot be negative.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.\n");
            input.next(); // Clear invalid input
        }
    }

    /* Handle checking account deposit with input validation */
    public void getCheckingDepositInput() {
        System.out.println("\nChecking Account Balance: " + moneyFormat.format(getCheckingBalance()));
        System.out.print("Amount you want to deposit to Checking Account: ");

        try {
            double amount = input.nextDouble();

            if (amount > 0) {
                calcCheckingDeposit(amount);
                System.out.println("New Checking Account Balance: " + moneyFormat.format(getCheckingBalance()) + "\n");
            } else {
                System.out.println("Invalid deposit amount.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.\n");
            input.next(); // Clear invalid input
        }
    }

    /* Handle saving account deposit with input validation */
    public void getSavingDepositInput() {
        System.out.println("\nSaving Account Balance: " + moneyFormat.format(getSavingBalance()));
        System.out.print("Amount you want to deposit to Saving Account: ");

        try {
            double amount = input.nextDouble();

            if (amount > 0) {
                calcSavingDeposit(amount);
                System.out.println("New Saving Account Balance: " + moneyFormat.format(getSavingBalance()) + "\n");
            } else {
                System.out.println("Invalid deposit amount.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.\n");
            input.next(); // Clear invalid input
        }
    }
}

