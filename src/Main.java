import service.ExpenseManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.loadFromFile();

        while (true) {
            String[] options = { "Add Income", "Add Expense", "View Transactions", "Summary", "Exit" };
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "💰 Expense Tracker\nChoose an option:",
                    "Expense Tracker",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == -1)
                break; // user closed window

            switch (choice) {
                case 0: // Add Income
                    String incomeCat = JOptionPane.showInputDialog(null, "Enter Income Category:");
                    if (incomeCat == null)
                        break;
                    String incomeAmtStr = JOptionPane.showInputDialog(null, "Enter Amount:");
                    try {
                        double incomeAmt = Double.parseDouble(incomeAmtStr);
                        manager.addTransaction("income", incomeCat, incomeAmt);
                        JOptionPane.showMessageDialog(null, "✅ Income added successfully!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "❌ Invalid amount entered!");
                    }
                    break;

                case 1: // Add Expense
                    String expenseCat = JOptionPane.showInputDialog(null, "Enter Expense Category:");
                    if (expenseCat == null)
                        break;
                    String expenseAmtStr = JOptionPane.showInputDialog(null, "Enter Amount:");
                    try {
                        double expenseAmt = Double.parseDouble(expenseAmtStr);
                        manager.addTransaction("expense", expenseCat, expenseAmt);
                        JOptionPane.showMessageDialog(null, "✅ Expense added successfully!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "❌ Invalid amount entered!");
                    }
                    break;

                case 2: // View Transactions
                    String transactions = manager.getTransactionsAsString();
                    JOptionPane.showMessageDialog(null,
                            transactions.isEmpty() ? "⚠️ No transactions yet." : transactions);
                    break;

                case 3: // Summary
                    JOptionPane.showMessageDialog(null, manager.getSummaryAsString());
                    break;

                case 4: // Exit
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "👋 Goodbye!");
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}
