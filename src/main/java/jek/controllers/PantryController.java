package jek.controllers;

import jek.services.BasicIngredientService;
import jek.services.ProgressService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class PantryController {
    private final TextService textService;
    private final ProgressService progressService;
    private final RawIngredientService rawIngredientService;
    private final BasicIngredientService basicIngredientService;
    private final ToppingService toppingService;
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

            int menuChoice = Integer.parseInt(scan.nextLine());
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
                        break;
                    }
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (!exitFromPantry);
    }

    private void buyRawIngredients() throws SQLException {
        int quantity = getAmountToBuyFromUser("raw ingredients", BigDecimal.valueOf(0.2));
        boolean paid = handlePurchase(BigDecimal.valueOf(quantity), BigDecimal.valueOf(0.2), BigDecimal.valueOf(6));
        if (paid){
            addStockOfRawIngredients(quantity);
        }
    }

    private void buyToppings() throws SQLException {
        int quantity = getAmountToBuyFromUser("toppings", BigDecimal.valueOf(2));
        boolean paid = handlePurchase(BigDecimal.valueOf(quantity), BigDecimal.valueOf(2), BigDecimal.valueOf(15));
        if (paid){
            addStockOfToppings(quantity);
        }
    }

    private void buyCheese() throws SQLException {
        int quantity = getAmountToBuyFromUser("cheese", BigDecimal.valueOf(0.5));
        boolean paid = handlePurchase(BigDecimal.valueOf(quantity), BigDecimal.valueOf(0.5), BigDecimal.valueOf(1));
        if (paid) {
            basicIngredientService.addCheese(quantity);
        }
    }

    private Boolean handlePurchase(BigDecimal quantity, BigDecimal costPerUnit, BigDecimal numberOfItems) throws SQLException {
        BigDecimal costOfPurchase = costPerUnit.multiply(quantity).multiply(numberOfItems);
        if (quantity.equals(BigDecimal.valueOf(0))) {
            return false;
        }
        if (progressService.getProgress().getCash().compareTo(costOfPurchase) < 0) {
            System.out.println("Not enough money.");
            return false;
        }
        else {
            progressService.getProgress().setCash(progressService.getProgress().getCash().subtract(costOfPurchase));
            progressService.updateProgressCashById(progressService.getProgress().getUserId(), progressService.getProgress().getCash());
            return true;
        }
    }

    private int getAmountToBuyFromUser(String typeOfIngredient, BigDecimal costPerUnit){
        int quantity;
        while (true){
            try {
                System.out.println("\nHow many units of "+ typeOfIngredient + " do you want to buy? (COST = $" + costPerUnit + " per unit) - To exit back to menu, input '0'");
                quantity = Integer.parseInt(scan.nextLine());
                if (quantity < 0){
                    System.out.println("You cannot un-buy stock.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again");
            }
        }
        return quantity;
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
}
