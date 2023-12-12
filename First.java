import java.util.ArrayList;
import java.util.Scanner;

class InventoryManagementSystem {
    private ArrayList<String> productNames = new ArrayList<>();
    private ArrayList<Double> productPrices = new ArrayList<>();
    private ArrayList<Integer> productQuantities = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addProduct(String name, double price, int stock) {
        productNames.add(name);
        productPrices.add(price);
        productQuantities.add(stock);
        System.out.println("Product '" + name + "' added successfully.");
    }

    public void updateProduct(String name, double price, int stock) {
        int index = getProductIndex(name);
        if (index != -1) {
            productPrices.set(index, price);
            productQuantities.set(index, stock);
            System.out.println("Product '" + name + "' updated successfully.");
        } else {
            System.out.println("Product '" + name + "' not found.");
        }
    }

    public void removeProduct(String name) {
        int index = getProductIndex(name);
        if (index != -1) {
            productNames.remove(index);
            productPrices.remove(index);
            productQuantities.remove(index);
            System.out.println("Product '" + name + "' removed successfully.");
        } else {
            System.out.println("Product '" + name + "' not found.");
        }
    }

    public void displayProducts() {

    System.out.println("\nAvailable Products:");
    System.out.printf("%-25s %-15s %-10s%n", "Name", "Price", "Stock");
    for (int i = 0; i < productNames.size(); i++) {
        System.out.printf("%-25s %-15.2f %-10d%n", productNames.get(i), productPrices.get(i), productQuantities.get(i));
    }
    }

    public void sellProduct(String name, int quantity) {
        int index = getProductIndex(name);
        if (index != -1) {
            int currentStock = productQuantities.get(index);
            if (currentStock >= quantity) {
                productQuantities.set(index, currentStock - quantity);
                System.out.println("Sold " + quantity + " units of '" + name + "'.");
            } else {
                System.out.println("Insufficient stock for '" + name + "'.");
            }
        } else {
            System.out.println("Product '" + name + "' not found.");
        }
    }

    public void generateReport() {
        System.out.println("\nInventory Report:");
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println("Name: " + productNames.get(i) + ", Stock: " + productQuantities.get(i));
        }
    }

    private int getProductIndex(String name) {
        return productNames.indexOf(name);
    }
}

class Main {
    public static void main(String[] args) {
        InventoryManagementSystem inventorySystem = new InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Display Products");
            System.out.println("5. Sell Product");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter initial stock quantity: ");
                    int stock = scanner.nextInt();
                    inventorySystem.addProduct(name, price, stock);
                    break;
                case 2:
                    System.out.print("Enter product name to update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double updatePrice = scanner.nextDouble();
                    System.out.print("Enter new stock quantity: ");
                    int updateStock = scanner.nextInt();
                    inventorySystem.updateProduct(updateName, updatePrice, updateStock);
                    break;
                case 3:
                    System.out.print("Enter product name to remove: ");
                    String removeName = scanner.nextLine();
                    inventorySystem.removeProduct(removeName);
                    break;
                case 4:
                    inventorySystem.displayProducts();
                    break;
                case 5:
                    System.out.print("Enter product name to sell: ");
                    String sellName = scanner.nextLine();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    inventorySystem.sellProduct(sellName, sellQuantity);
                    break;
                case 6:
                    inventorySystem.generateReport();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
