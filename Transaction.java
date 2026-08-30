package model;

import java.time.LocalDateTime;

public class Transaction {

    private final LocalDateTime time;
    private final String type;
    private final double amount;
    private final String description;

    public Transaction(LocalDateTime time,
                       String type,
                       double amount,
                       String description) {

        this.time = time;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "time=" + time +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}