import java.util.ArrayList;
import java.util.Scanner;

// Helper class to store expense details
class Expense {
    String description;
    double amount;

    Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Description: %-15s | Amount: $%.2f", description, amount);
    }
}

// Main class containing the execution logic
public class Main {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Expense Tracker Menu ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. View Total");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Added basic check to prevent crashing if user enters non-numeric input
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> updateExpense();
                case 4 -> deleteExpense();
                case 5 -> showTotal();
                case 6 -> {
                    System.out.println("Exiting... Stay on budget!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addExpense() {
        System.out.print("Enter expense description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amt = scanner.nextDouble();
        expenses.add(new Expense(desc, amt));
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    private static void updateExpense() {
        viewExpenses();
        if (expenses.isEmpty()) return;

        System.out.print("Enter the ID to update: ");
        int id = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (id >= 0 && id < expenses.size()) {
            System.out.print("Enter new description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter new amount: ");
            double amt = scanner.nextDouble();
            expenses.set(id, new Expense(desc, amt));
            System.out.println("Expense updated!");
        } else {
            System.out.println("Invalid ID.");
        }
    }

    private static void deleteExpense() {
        viewExpenses();
        if (expenses.isEmpty()) return;

        System.out.print("Enter the ID to delete: ");
        int id = scanner.nextInt() - 1;
        if (id >= 0 && id < expenses.size()) {
            expenses.remove(id);
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Invalid ID.");
        }
    }

    private static void showTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.amount;
        }
        System.out.printf("Total Expenses: $%.2f%n", total);
    }
}