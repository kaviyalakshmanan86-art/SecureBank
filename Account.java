package model;

public class Account implements Comparable<Account> {

    private final int id;
    private final String name;
    private double balance;

    public Account(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Account other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Account{id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance + '}';
    }
}