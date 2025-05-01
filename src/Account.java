public class Account {
    private String accountNumber;
    private String password;
    private double balance;

    public Account(String accountNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }

    public boolean authenticate(String passwordInput) {
        return this.password.equals(passwordInput);
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
