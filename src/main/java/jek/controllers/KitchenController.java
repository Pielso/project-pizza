package jek.controllers;

import jek.models.Recipe;
import jek.models.RecipeTopping;
import jek.services.*;
import jek.services.system.TextService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static jek.controllers.LoginController.activeUser;

public class KitchenController {
    private final Scanner scan = new Scanner(System.in);
    private final TextService textService;
    private final RecipeService recipeService;
    private final RawIngredientService rawIngredientService;
    private final BasicIngredientService basicIngredientService;
    private final RecipeToppingService recipeToppingService;

    public KitchenController(TextService textService, RecipeService recipeService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, RecipeToppingService recipeToppingService) {
        this.textService = textService;
        this.recipeService = recipeService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.recipeToppingService = recipeToppingService;
    }

    public void goToKitchen() {
        boolean exitFromKitchen = false;

        do {
            textService.kitchenScreen();
            System.out.println("1: CREATE A NEW RECIPE");
            System.out.println("2: PREPARE 10 DOUGH");
            System.out.println("3: PREPARE 10 TOMATO SAUCE");
            System.out.println("4: EXIT FROM KITCHEN");

            int menuChoice = Integer.parseInt(scan.nextLine());
            try {
                switch (menuChoice) {
                    case 1: {
                        createRecipe();
                        break;
                    }
                    case 2: {
                        prepareDough();
                        break;
                    }
                    case 3: {
                        prepareTomatoSauce();
                        break;
                    }
                    case 4: {
                        exitFromKitchen = true;
                        break;
                    }
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (!exitFromKitchen);
    }

    private void createRecipe() {
        List <Integer> chosenToppings = new ArrayList<>();
        System.out.println("Enter recipe name: ");
        String recipeName = scan.nextLine();

        while (true) {
            textService.displayAvailableToppings();
            System.out.println("Which topping would you like to add to the recipe? (enter ID).\nTo exit without saving the recipe, input '0'. \nWhen you are done with the recipe, input any number above 15");
            int toppingId = Integer.parseInt(scan.nextLine());
            if (toppingId == 0) break;
            if (toppingId > 0 && toppingId < 16) {
                chosenToppings.add(toppingId);
            }
            if (toppingId > 15){
                if (chosenToppings.isEmpty()) break;
                else {
                    Recipe recipe = new Recipe(recipeName, activeUser.getUserId());
                    recipeService.createRecipe(recipe);
                    System.out.println("Recipe created successfully!");
                    for (Integer topping: chosenToppings){
                        RecipeTopping recipeTopping = new RecipeTopping(recipeService.getRecipeByName(recipeName).getRecipeId(), topping);
                        recipeToppingService.createRecipeTopping(recipeTopping);
                    }
                }
                break;
            }
        }
    }

    private void prepareDough() {
        if (rawIngredientService.hasEnoughIngredientsForDough()) {
            rawIngredientService.subtractIngredientsForDough();
            basicIngredientService.addDough(10);
            System.out.println("Dough prepared successfully!");
        } else {
            System.out.println("Not enough ingredients to prepare dough.");
        }
    }

    private void prepareTomatoSauce() {
        if (rawIngredientService.hasEnoughIngredientsTomatoSauce()) {
            rawIngredientService.subtractIngredientsForTomatoSauce();
            basicIngredientService.addTomatoSauce(10);
            System.out.println("Tomato sauce prepared successfully!");
        } else {
            System.out.println("Not enough ingredients to prepare tomato sauce.");
        }
    }

}


