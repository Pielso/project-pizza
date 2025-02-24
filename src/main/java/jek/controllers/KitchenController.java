package jek.controllers;

import jek.services.*;
import jek.services.system.TextService;

public class KitchenController {
    private TextService textService;
    private ProgressService progressService;
    private RecipeService recipeService;
    private RawIngredientService rawIngredientService;
    private BasicIngredientService basicIngredientService;
    private ToppingService toppingService;

    public KitchenController(TextService textService, ProgressService progressService, RecipeService recipeService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.textService = textService;
        this.progressService = progressService;
        this.recipeService = recipeService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
    }
}
