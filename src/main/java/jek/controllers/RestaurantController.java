package jek.controllers;

import jek.services.CustomerService;
import jek.services.ProgressService;
import jek.services.RecipeService;
import jek.services.system.TextService;

public class RestaurantController {
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

    public void goToRestaurant(){

        while (true){

            // SHOW USER ALL CUSTOMERS FOR TODAY, AND THEIR desired_toppings.

            // SERVE CUSTOMER - WHICH CUSTOMER? - WHICH RECIPE? - HAS ENOUGH INGREDIENTS?

            // SUBTRACT INGREDIENTS WHERE (SQL-JOIN)

            // GET PAID (BASE PRICE $6 AND +2, +5, +9 FOR MATCHED desired_toppings).

            // ADD TO PROGRESS, SAVE.

            // DELETE CUSTOMER

            // SAVE

        }
    }

}
