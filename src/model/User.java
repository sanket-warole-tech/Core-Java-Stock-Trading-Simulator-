package model;

public class User {

    // Instance Variables
    private int userId;
    private String username;
    private String password;
    private double balance;

    // Default Constructor
    public User() {

    }

    // Parameterized Constructor
    public User(int userId, String username, String password, double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    // Deposit Money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw Money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Display User Information
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}