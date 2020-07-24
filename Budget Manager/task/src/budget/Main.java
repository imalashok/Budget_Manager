package budget;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

enum MenuState {
    MAIN, ADD_PURCHASE, SHOW_PURCHASE, ANALYZE
}

public class Main {
    static MenuState menuState = MenuState.MAIN;

    public static void main(String[] args) {
        BudgetManager budgetManager = new BudgetManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (menuState == MenuState.MAIN) {
                budgetManager.showMainMenu();
                switch (scanner.nextLine()) {
                    case "1":
                        System.out.println("\nEnter income:");
                        String income = scanner.nextLine();
                        try {
                            budgetManager.addBalance(Double.parseDouble(income));
                            System.out.println("Income was added!");
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong balance value.");
                        }
                        break;
                    case "2":
                        menuState = MenuState.ADD_PURCHASE;
                        break;
                    case "3":
                        menuState = MenuState.SHOW_PURCHASE;
                        break;
                    case "4":
                        System.out.printf("\nBalance: $%.2f\n", budgetManager.getBalance() < 0 ? 0 : budgetManager.getBalance());
                        break;
                    case "5":
                        budgetManager.saveToFile();
                        break;
                    case "6":
                        budgetManager.loadFromFile();
                        break;
                    case "7":
                        menuState = MenuState.ANALYZE;
                        break;
                    case "0":
                        System.out.println("\nBye!");
                        System.exit(0);
                    default:
                        System.out.println("\nWrong menu option!");
                        break;
                }
            } else if (menuState == MenuState.ADD_PURCHASE) {
                budgetManager.showAddPurchaseMenu();

                String productCategory = "";

                switch (scanner.nextLine()) {
                    case "1":
                        productCategory = "Food";
                        break;
                    case "2":
                        productCategory = "Clothes";
                        break;
                    case "3":
                        productCategory = "Entertainment";
                        break;
                    case "4":
                        productCategory = "Other";
                        break;
                    case "5":
                        menuState = MenuState.MAIN;
                        break;
                    default:
                        System.out.println("Wrong menu option.");
                }

                if (!productCategory.isEmpty()) {
                    System.out.println("\nEnter purchase name:");
                    String purchasedItem = scanner.nextLine();
                    System.out.println("Enter its price:");
                    String purchasedItemPrice = scanner.nextLine();
                    try {
                        double price = Double.parseDouble(purchasedItemPrice);
                        budgetManager.addPurchase(purchasedItem, price, productCategory);
                        System.out.println("Purchase was added!");
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong price.");
                    }
                }

            } else if (menuState == MenuState.SHOW_PURCHASE) {
                budgetManager.showShowPurchaseMenu();

                switch (scanner.nextLine()) {
                    case "1":
                        budgetManager.getPurchases("Food");
                        break;
                    case "2":
                        budgetManager.getPurchases("Clothes");
                        break;
                    case "3":
                        budgetManager.getPurchases("Entertainment");
                        break;
                    case "4":
                        budgetManager.getPurchases("Other");
                        break;
                    case "5":
                        budgetManager.getPurchases("All");
                        break;
                    case "6":
                        menuState = MenuState.MAIN;
                        break;
                    default:
                        System.out.println("Wrong menu option.");
                        break;
                }
            } else if (menuState == MenuState.ANALYZE) {
                budgetManager.showAnalyzeMenu();

                switch (scanner.nextLine()) {
                    case "1":
                        if (budgetManager.getPurchaseList("All").size() == 0) {
                            System.out.println("\nPurchase list is empty!");
                        } else {
                            SortingAlgorithm.sortByPriceDesc(budgetManager.getPurchaseList("All"));
                            budgetManager.getPurchases("All");
                        }
                        break;
                    case "2":
                        Map<String, Double> types = new LinkedHashMap<>();

                        types.put("Food", budgetManager.getTotalPrice("Food"));
                        types.put("Clothes", budgetManager.getTotalPrice("Clothes"));
                        types.put("Entertainment", budgetManager.getTotalPrice("Entertainment"));
                        types.put("Other", budgetManager.getTotalPrice("Other"));

                        Map<String,Double> sortedTypes = SortingAlgorithm.sortByTypeOfPurchase(types);

                        System.out.println("\nTypes:");
                        for (var entry : sortedTypes.entrySet()) {
                            System.out.printf(entry.getKey() + " - $%.2f\n", entry.getValue());
                        }
                        break;
                    case "3":
                        budgetManager.showProductTypes();
                        String purchaseType = "";
                        switch (scanner.nextLine()) {
                            case "1":
                                purchaseType = "Food";
                                break;
                            case "2":
                                purchaseType = "Clothes";
                                break;
                            case "3":
                                purchaseType = "Entertainment";
                                break;
                            case "4":
                                purchaseType = "Other";
                                break;
                            default:
                                System.out.println("Wrong type of purchases.");
                                break;
                        }
                        if (!purchaseType.isEmpty()) {
                            if (budgetManager.getPurchaseList(purchaseType).size() == 0) {
                                System.out.println("\nPurchase list is empty!");
                            } else {
                                SortingAlgorithm.sortByPriceDesc(budgetManager.getPurchaseList(purchaseType));
                                budgetManager.getPurchases(purchaseType);
                            }
                        }
                        break;
                    case "4":
                        menuState = MenuState.MAIN;
                        break;
                }
            }
        }
    }
}
