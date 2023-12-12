import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Expense {
    String category;
    double amount;
    String date;

    public Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }
}

class ExpenseManager {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private Map<String, Double> budgets = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void logExpense(String category, double amount, String date) {
        Expense expense = new Expense(category, amount, date);
        expenses.add(expense);
        System.out.println("Expense logged successfully.");
    }

    public void setBudget(String category, double budgetAmount) {
        budgets.put(category, budgetAmount);
        System.out.println("Budget set for category '" + category + "'.");
    }

    public void visualizeExpenses() {
        System.out.println("\nExpense Visualization:");
        for (Expense expense : expenses) {
            System.out.println("Category: " + expense.category + ", Amount: $" + expense.amount + ", Date: " + expense.date);
        }
    }

    public void visualizeBudgets() {
        System.out.println("\nBudgets:");
        for (Map.Entry<String, Double> entry : budgets.entrySet()) {
            System.out.println("Category: " + entry.getKey() + ", Budget: $" + entry.getValue());
        }
    }

    public void checkOverspending() {
        System.out.print("Enter the category to check overspending: ");
        String checkCategory = scanner.nextLine();
       
        if (budgets.containsKey(checkCategory)) {
            double totalExpense = calculateTotalExpense(checkCategory);
            double budget = budgets.get(checkCategory);
           
            if (totalExpense > budget) {
                System.out.println("Warning: You have overspent in category '" + checkCategory +
                        "'. Budget: $" + budget + ", Total Expense: $" + totalExpense);
            } else {
                System.out.println("You are within the budget for category '" + checkCategory + "'.");
            }
        } else {
            System.out.println("Budget not set for category '" + checkCategory + "'.");
        }
    }

    private double calculateTotalExpense(String category) {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            if (expense.category.equals(category)) {
                totalExpense += expense.amount;
            }
        }
        return totalExpense;
    }
}
class ExpenseManagementSystem {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Management System");
            System.out.println("1. Log Expense");
            System.out.println("2. Set Budget");
            System.out.println("3. Visualize Expenses");
            System.out.println("4. Visualize Budgets");
            System.out.println("5. Check Overspending");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter expense category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter expense amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter expense date (MM/DD/YYYY): ");
                    String date = scanner.nextLine();
                    expenseManager.logExpense(category, amount, date);
                    break;
                case 2:
                    System.out.print("Enter budget category: ");
                    String budgetCategory = scanner.nextLine();
                    System.out.print("Enter budget amount: $");
                    double budgetAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    expenseManager.setBudget(budgetCategory, budgetAmount);
                    break;
                case 3:
                    expenseManager.visualizeExpenses();
                    break;
                case 4:
                    expenseManager.visualizeBudgets();
                    break;
                case 5:
                    expenseManager.checkOverspending();
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
