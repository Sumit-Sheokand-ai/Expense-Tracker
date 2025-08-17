package model;

import java.time.LocalDate;

/**
 * Represents a financial transaction (income or expense).
 */
public class Transaction {
    private String type;      // income / expense
    private String category;  // category of transaction
    private double amount;    // transaction amount
    private LocalDate date;   // date of transaction

    public Transaction(String type, String category, double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return String.format("%s | %-7s | %-10s | $%.2f",
                date, type.toUpperCase(), category, amount);
    }
}
