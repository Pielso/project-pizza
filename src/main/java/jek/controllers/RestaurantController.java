package jek.controllers;

import jek.models.Customer;
import jek.services.*;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class RestaurantController {
    private final Scanner scan = new Scanner(System.in);
    private final TextService textService;
    private final ProgressService progressService;
    private final CustomerService customerService;
    private final RecipeToppingService recipeToppingService;
    private final ToppingService toppingService;
    private final BasicIngredientService basicIngredientService;

    public RestaurantController(TextService textService, ProgressService progressService, CustomerService customerService, RecipeToppingService recipeToppingService, ToppingService toppingService, BasicIngredientService basicIngredientService) {
        this.textService = textService;
        this.progressService = progressService;
        this.customerService = customerService;
        this.recipeToppingService = recipeToppingService;
        this.toppingService = toppingService;
        this.basicIngredientService = basicIngredientService;
    }

    public void goToRestaurant() {
        boolean exitFromRestaurant = false;

        do {
            textService.restaurantScreen();
            System.out.println("1: SERVE A CUSTOMER");
            System.out.println("2: EXIT RESTAURANT");

            try {
                int menuChoice = Integer.parseInt(scan.nextLine());
                switch (menuChoice) {
                    case 1: {
                        serveCustomer();
                        break;
                    }
                    case 2: {
                        exitFromRestaurant = true;
                        break;
                    }
                    default:{
                        System.out.println("Invalid choice. Try again.");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (!exitFromRestaurant);
    }

    // SERVE CUSTOMER - WHICH CUSTOMER? - WHICH RECIPE? - HAS ENOUGH INGREDIENTS?

    private void serveCustomer() {
        textService.serveCustomerScreen();
        System.out.println("Enter customer ID to serve, or '0' to exit. ");
        int customerId = Integer.parseInt(scan.nextLine());
        if (customerId == 0) {
            return;
        }

        textService.showAvailableRecipesAndToppings();
        System.out.println("Enter recipe ID to serve: ");
        int recipeId = Integer.parseInt(scan.nextLine());

        if (!toppingService.checkIfToppingAmountInStockExists(recipeToppingService.getAllToppingIdByRecipeId(recipeId))
                || !basicIngredientService.checkIfBasicIngredientsAmountInStockExists()) {
            System.out.println("Not enough ingredients to prepare this recipe.");
        }
        else {
            basicIngredientService.preparedOnePizza();
            toppingService.servedOnePizza(recipeToppingService.getAllToppingIdByRecipeId(recipeId));

            BigDecimal payment = calculatePayment(customerId, recipeId);
            progressService.getActiveProgress().setCash(progressService.getActiveProgress().getCash().add(payment));
            progressService.updateProgressCashById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getCash());

            customerService.deleteCustomerById(customerId);
            System.out.println("Customer served! Earned: $" + payment);
        }
    }

    private BigDecimal calculatePayment(int customerId, int recipeId) {
        List<Integer> recipeToppings = recipeToppingService.getAllToppingIdByRecipeId(recipeId);  // Hämta en gång
        Customer customer = customerService.getCustomerById(customerId); // Hämta en gång

        int counter = 0;
        if (recipeToppings.contains(customer.getDesiredTopping1())) counter++;
        if (recipeToppings.contains(customer.getDesiredTopping2())) counter++;
        if (recipeToppings.contains(customer.getDesiredTopping3())) counter++;

        BigDecimal payment;
        switch (counter) {
            case 1 -> payment = BigDecimal.valueOf(8);
            case 2 -> payment = BigDecimal.valueOf(11);
            case 3 -> payment = BigDecimal.valueOf(15);
            default -> payment = BigDecimal.valueOf(6);
        }
        System.out.println("Total payment for customer " + customerId + " is $" + payment + ".");
        return payment;
    }
}
