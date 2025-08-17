package service;

import model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Handles all business logic for managing transactions.
 */
public class ExpenseManager {
    private List<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "expenses.csv";

    public void addTransaction(String type, String category, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Amount must be greater than 0.");
            return;
        }
        Transaction t = new Transaction(type, category, amount, LocalDate.now());
        transactions.add(t);
        saveToFile(t);
        System.out.println("✅ Transaction added successfully!");
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("⚠️ No transactions recorded yet.");
            return;
        }
        transactions.forEach(System.out::println);
    }

    public void summary() {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("income"))
                income += t.getAmount();
            else
                expense += t.getAmount();
        }
        System.out.println("\n--- Summary ---");
        System.out.printf("💵 Total Income:  $%.2f\n", income);
        System.out.printf("💸 Total Expense: $%.2f\n", expense);
        System.out.printf("📊 Balance:       $%.2f\n", (income - expense));
    }

    private void saveToFile(Transaction t) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(String.format("%s,%s,%.2f,%s%n",
                    t.getType(), t.getCategory(), t.getAmount(), t.getDate()));
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                transactions.add(new Transaction(
                        data[0], data[1], Double.parseDouble(data[2]), LocalDate.parse(data[3])));
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    public String getTransactionsAsString() {
        if (transactions.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder("📜 Transaction History:\n\n");
        for (Transaction t : transactions) {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    public String getSummaryAsString() {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("income"))
                income += t.getAmount();
            else
                expense += t.getAmount();
        }
        return String.format("💵 Income: $%.2f\n💸 Expense: $%.2f\n📊 Balance: $%.2f",
                income, expense, (income - expense));
    }

}
