package budget;

import java.io.*;
import java.util.*;

public class BudgetManager {
    private double balance = 0.0;
    private double totalFoodPrice = 0.0;
    private double totalClothesPrice = 0.0;
    private double totalEntertainmentPrice = 0.0;
    private double totalOtherPrice = 0.0;
    private double totalSumOfPurchases = 0.0;
    private Map<String, Double> purchases = new LinkedHashMap<>();
    private List<Product> allList = new ArrayList<>();
    private List<Product> foodList = new ArrayList<>();
    private List<Product> clothesList = new ArrayList<>();
    private List<Product> entertainmentList = new ArrayList<>();
    private List<Product> otherList = new ArrayList<>();

    public void showMainMenu() {
        System.out.println("\nChoose your action:");
        System.out.println("1) Add income" +
                "\n2) Add purchase" +
                "\n3) Show list of purchases" +
                "\n4) Balance" +
                "\n5) Save" +
                "\n6) Load" +
                "\n7) Analyze (Sort)" +
                "\n0) Exit");
    }

    public void showAddPurchaseMenu() {
        System.out.println("\nChoose the type of purchase" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) Back");
    }

    public void showShowPurchaseMenu() {
        System.out.println("\nChoose the type of purchases" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other" +
                "\n5) All" +
                "\n6) Back");
    }

    public void showAnalyzeMenu() {
        System.out.println("\nHow do you want to sort?" +
                "\n1) Sort all purchases" +
                "\n2) Sort by type" +
                "\n3) Sort certain type" +
                "\n4) Back");
    }

    public void showProductTypes() {
        System.out.println("\nChoose the type of purchase" +
                "\n1) Food" +
                "\n2) Clothes" +
                "\n3) Entertainment" +
                "\n4) Other");
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double balance) {
        this.balance += balance;
    }

    public double getTotalPrice(String purchaseType) {
        switch (purchaseType) {
            case "Food":
                return totalFoodPrice;
            case "Clothes":
                return totalClothesPrice;
            case "Entertainment":
                return totalEntertainmentPrice;
            case "Other":
                return totalOtherPrice;
            default:
                return totalSumOfPurchases;
        }
    }

    public void getPurchases(String productCategory) {

        System.out.println("\n" + productCategory + ":");

        switch (productCategory) {
            case "Food":
                if (foodList.isEmpty()) {
                    System.out.println("Purchase list is empty!");
                    return;
                }
                for (Product product : foodList) {
                    System.out.println(product.toString());
                }
                System.out.printf("Total sum: $%.2f\n", totalFoodPrice);
                break;
            case "Clothes":
                if (clothesList.isEmpty()) {
                    System.out.println("Purchase list is empty!");
                    return;
                }
                for (Product product : clothesList) {
                    System.out.println(product.toString());
                }
                System.out.printf("Total sum: $%.2f\n", totalClothesPrice);
                break;
            case "Entertainment":
                if (entertainmentList.isEmpty()) {
                    System.out.println("Purchase list is empty!");
                    return;
                }
                for (Product product : entertainmentList) {
                    System.out.println(product.toString());
                }
                System.out.printf("Total sum: $%.2f\n", totalEntertainmentPrice);
                break;
            case "Other":
                if (otherList.isEmpty()) {
                    System.out.println("Purchase list is empty!");
                    return;
                }
                for (Product product : otherList) {
                    System.out.println(product.toString());
                }
                System.out.printf("Total sum: $%.2f\n", totalOtherPrice);
                break;
            default:
                if (allList.isEmpty()) {
                    System.out.println("Purchase list is empty!");
                    return;
                }
                for (Product product : allList) {
                    System.out.println(product.toString());
                }
                System.out.printf("Total sum: $%.2f\n", totalSumOfPurchases);
                break;
        }
    }

    public void addPurchase(String productName, double price, String productCategory) {
        Product product = null;

        switch (productCategory) {
            case "Food":
                product = new Food(productName, price, productCategory);
                foodList.add(product);
                totalFoodPrice += price;
                break;
            case "Clothes":
                product = new Clothes(productName, price, productCategory);
                clothesList.add(product);
                totalClothesPrice += price;
                break;
            case "Entertainment":
                product = new Entertainment(productName, price, productCategory);
                entertainmentList.add(product);
                totalEntertainmentPrice += price;
                break;
            case "Other":
                product = new Other(productName, price, productCategory);
                otherList.add(product);
                totalOtherPrice += price;
                break;
        }
        allList.add(product);
        totalSumOfPurchases += price;
        addBalance(-price);
    }

    public List<Product> getPurchaseList(String productCategory) {
        switch (productCategory) {
            case "Food":
                return foodList;
            case "Clothes":
                return clothesList;
            case "Entertainment":
                return entertainmentList;
            case "Other":
                return otherList;
            default:
                return allList;
        }
    }

    public void saveToFile() {
        File file = new File("purchases.txt");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(balance + "\n");

            for (Product product : allList) {
                writer.write(product.getProductCategory() + ";" + product.getProductName() + ";" + product.getProductPrice() + "\n");
            }

            System.out.println("\nPurchases were saved!");

        } catch (IOException e) {
            System.out.println("Unable to write to the file " + file);
            System.out.printf("\nAn exception occurs %s", e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File("purchases.txt");
        try (Scanner fileScanner = new Scanner(file)) {
            double newBalance = Double.parseDouble(fileScanner.nextLine());
            while (fileScanner.hasNextLine()) {
                String[] productLine = fileScanner.nextLine().split(";");
                addPurchase(productLine[1], Double.parseDouble(productLine[2]), productLine[0]);
            }
            balance = newBalance;

            System.out.println("\nPurchases were loaded!");

        } catch (FileNotFoundException e) {
            System.out.println("Unable to load from the file " + file);
            System.out.printf("\nAn exception occurs %s", e.getMessage());
        } catch (NumberFormatException e) {
                System.out.println("Wrong number format in the file.");
        }
    }
}
