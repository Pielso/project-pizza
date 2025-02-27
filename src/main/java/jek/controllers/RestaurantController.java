package jek.controllers;

import jek.models.Customer;
import jek.services.CustomerService;
import jek.services.ProgressService;
import jek.services.RecipeService;
import jek.services.system.TextService;

import java.sql.SQLException;
import java.util.Scanner;

public class RestaurantController {
    private final Scanner scan = new Scanner(System.in);
    private final TextService textService;
    private final ProgressService progressService;
    private final CustomerService customerService;
    private final RecipeService recipeService;

    public RestaurantController(TextService textService, ProgressService progressService, CustomerService customerService, RecipeService recipeService) {
        this.textService = textService;
        this.progressService = progressService;
        this.customerService = customerService;
        this.recipeService = recipeService;
    }

    public void goToRestaurant() throws SQLException {

        for (int i = 10; i > 0; i--) {
            customerService.createCustomer(new Customer());
        }

        while (true){

            boolean exitFromRestaurant = false;

            do {
                textService.restaurantScreen();
                System.out.println("2: SERVE A CUSTOMER");
                System.out.println("3: EXIT RESTAURANT");

                int menuChoice = Integer.parseInt(scan.nextLine());
                try {
                    switch (menuChoice) {
                        case 1: {
                            //serveCustomer();
                            break;
                        }
                        case 2: {
                            exitFromRestaurant = true;
                            break;
                        }
                        default: System.out.println("Invalid choice. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            } while (!exitFromRestaurant);
        }





        // SERVE CUSTOMER - WHICH CUSTOMER? - WHICH RECIPE? - HAS ENOUGH INGREDIENTS?

//        private void serveCustomer() {
//            System.out.println("Enter customer ID to serve: ");
//            int customerId = scan.nextInt();
//            scan.nextLine();
//
//            System.out.println("Enter recipe ID to serve: ");
//            int recipeId = scan.nextInt();
//            scan.nextLine();
//
//            if (!recipeService.hasEnoughIngredients(recipeId)) {
//                System.out.println("Not enough ingredients to prepare this recipe.");
//                return;
//            }
//
//            // SUBTRACT INGREDIENTS WHERE (SQL-JOIN)
//
//            recipeService.subtractIngredients(recipeId);
//            BigDecimal payment = calculatePayment(customerId, recipeId);
//            activeProgress.setCash(activeProgress.getCash().add(payment));
//            progressService.updateProgress(activeProgress);
//
//            customerService.deleteCustomer(customerId);
//            System.out.println("Customer served! Earned: $" + payment);
//        }
//
//        private BigDecimal calculatePayment(int customerId, int recipeId) {
//            BigDecimal basePrice = BigDecimal.valueOf(6);
//            int bonus = customerService.calculateBonus(customerId, recipeId);
//            return basePrice.add(BigDecimal.valueOf(bonus));
//        }
//    }
//
//
//
//
//
//
//            // GET PAID (BASE PRICE $6 AND +2, +5, +9 FOR MATCHED desired_toppings).
//
//            // ADD TO PROGRESS, SAVE.
//
//            // DELETE CUSTOMER
//
//            // SAVE
//
//        }
    }

}
