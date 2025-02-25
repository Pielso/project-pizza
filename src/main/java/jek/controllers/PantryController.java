package jek.controllers;

import jek.services.BasicIngredientService;
import jek.services.ProgressService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.util.Scanner;

import static jek.controllers.LoginController.activeProgress;

public class PantryController {
    private TextService textService;
    private ProgressService progressService;
    private RawIngredientService rawIngredientService;
    private BasicIngredientService basicIngredientService;
    private ToppingService toppingService;
    private final Scanner scan = new Scanner(System.in);

    public PantryController(TextService textService, ProgressService progressService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.textService = textService;
        this.progressService = progressService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
    }

    public void goToPantry() {
        boolean exitFromPantry = false;

        do {
            textService.pentryScreen();

            System.out.println("1: BUY MORE RAW INGREDIENTS");
            System.out.println("2: BUY MORE CHEESE");
            System.out.println("3: BUY MORE TOPPINGS");
            System.out.println("4: EXIT FROM PANTRY");

            int menuChoice = scan.nextInt();
            try {
                switch (menuChoice) {
                    case 1: {
                        buyRawIngredients();
                        break;
                    }
                    case 2: {
                        buyCheese();
                        break;
                    }
                    case 3: {
                        buyToppings();
                        break;
                    }
                    case 4: {
                        exitFromPantry = true;
                    }
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (!exitFromPantry);
    }

    private void buyRawIngredients() {
        System.out.println("\nHow many of each do you want to buy? (COST = $0.2 per unit) - To exit back to menu, input '0'");
        int quantity;
        quantity = scan.nextInt();
        scan.nextLine();
        BigDecimal costPerUnit = BigDecimal.valueOf(0.2);
        BigDecimal costOfPurchase = BigDecimal.valueOf(quantity);
        costOfPurchase = costOfPurchase.multiply(costPerUnit);
        costOfPurchase = costOfPurchase.multiply(BigDecimal.valueOf(6));

        if (BigDecimal.valueOf(quantity).equals(BigDecimal.valueOf(0))) {
            return;
        }
        if (activeProgress.getCash().compareTo(costOfPurchase) < 0) {
            System.out.println("Not enough money.");
        }
        else {
            activeProgress.setCash(activeProgress.getCash().subtract(costOfPurchase));
            progressService.updateProgress(activeProgress);
            addStockOfRawIngredients(quantity);
        }
    }

    private void buyToppings() {
        System.out.println("\nHow many of each do you want to buy? (COST = $2 per unit) - To exit back to menu, input '0'");
        int quantity;
        quantity = scan.nextInt();
        scan.nextLine();
        BigDecimal costPerUnit = BigDecimal.valueOf(2);
        BigDecimal costOfPurchase = BigDecimal.valueOf(quantity);
        costOfPurchase = costOfPurchase.multiply(costPerUnit);
        costOfPurchase = costOfPurchase.multiply(BigDecimal.valueOf(15));

        if (BigDecimal.valueOf(quantity).equals(BigDecimal.valueOf(0))) {
            return;
        }
        if (activeProgress.getCash().compareTo(costOfPurchase) < 0) {
            System.out.println("Not enough money.");
        }
        else {
            activeProgress.setCash(activeProgress.getCash().subtract(costOfPurchase));
            progressService.updateProgress(activeProgress);
            addStockOfToppings(quantity);
        }
    }

    private void buyCheese() {
        System.out.println("\nHow many units of cheese do you want to buy? (COST = $0.5 per unit) - To exit back to menu, input '0'");
        int quantity;
        quantity = scan.nextInt();
        scan.nextLine();
        BigDecimal costPerUnit = BigDecimal.valueOf(0.5);
        BigDecimal costOfPurchase = BigDecimal.valueOf(quantity);
        costOfPurchase = costOfPurchase.multiply(costPerUnit);

        if (BigDecimal.valueOf(quantity).equals(BigDecimal.valueOf(0))) {
            return;
        }
        if (activeProgress.getCash().compareTo(costOfPurchase) < 0) {
            System.out.println("Not enough money.");
        }
        else {
            activeProgress.setCash(activeProgress.getCash().subtract(costOfPurchase));
            progressService.updateProgress(activeProgress);
            addStockOfCheese(quantity);
        }
    }

    private void addStockOfRawIngredients(int quantity) {
        for (int i = 1; i < 7; i++){
           rawIngredientService.addRawIngredientAmountInStock(i, quantity);
        }
    }

    private void addStockOfToppings(int quantity) {
        for (int i = 1; i < 16; i++){
            toppingService.addToppingAmountInStock(i, quantity);
        }
    }

    private void addStockOfCheese(int quantity) {
        basicIngredientService.addCheeseAmountInStock(3, quantity);

    }

}
