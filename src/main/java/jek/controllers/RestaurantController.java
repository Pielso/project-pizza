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
}
