package jek.dependecies;

import jek.controllers.*;
import jek.repositories.*;
import jek.services.*;
import jek.services.system.*;

public class DependencyContainer {

    private final DatabaseService databaseService;
    private final LoginController loginController;
    private final PizzaGameController pizzaGameController;

    public DependencyContainer() {

        this.databaseService = new DatabaseService();

        // Create repositories, which should only need DatabaseService
        RawIngredientRepository rawIngredientRepository = new RawIngredientRepository(databaseService);
        BasicIngredientRepository basicIngredientRepository = new BasicIngredientRepository(databaseService);
        ToppingRepository toppingRepository = new ToppingRepository(databaseService);
        RecipeRepository recipeRepository = new RecipeRepository(databaseService);
        RecipeToppingRepository recipeToppingRepository = new RecipeToppingRepository(databaseService);
        CustomerRepository customerRepository = new CustomerRepository(databaseService);
        UserRepository userRepository = new UserRepository(databaseService);
        ProgressRepository progressRepository = new ProgressRepository(databaseService);

        // Create services that for most part only should need their own repository.
        RawIngredientService rawIngredientService = new RawIngredientService(rawIngredientRepository);
        BasicIngredientService basicIngredientService = new BasicIngredientService(basicIngredientRepository);
        ToppingService toppingService = new ToppingService(toppingRepository);
        RecipeService recipeService = new RecipeService(recipeRepository);
        RecipeToppingService recipeToppingService = new RecipeToppingService(recipeToppingRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        UserService userService = new UserService(userRepository);
        ProgressService progressService = new ProgressService(progressRepository);

        // Services that is not represented by models.
        TextService textService = new TextService(progressService, rawIngredientService, basicIngredientService, toppingService, customerService, recipeService, recipeToppingService);
        DynamoDBService dynamoDBService = new DynamoDBService(progressService, rawIngredientService, basicIngredientService, toppingService);
        SaveAndLoadService saveAndLoadService = new SaveAndLoadService(dynamoDBService, rawIngredientService, basicIngredientService, toppingService);
        LoginService loginService = new LoginService(textService, userService, saveAndLoadService);
        NextDayService nextDayService = new NextDayService(progressService, customerService);

        // Controllers
        OfficeController officeController = new OfficeController(textService);
        PantryController pantryController = new PantryController(textService, progressService, rawIngredientService, basicIngredientService, toppingService);
        RestaurantController restaurantController = new RestaurantController(textService, progressService, customerService, recipeToppingService, toppingService, basicIngredientService);
        KitchenController kitchenController = new KitchenController(textService, recipeService, rawIngredientService, basicIngredientService, recipeToppingService);
        BankController bankController = new BankController(textService, progressService);

        this.loginController = new LoginController(loginService, textService, progressService, userService, saveAndLoadService);
        this.pizzaGameController = new PizzaGameController(textService, loginController, officeController, pantryController, restaurantController, kitchenController, bankController, saveAndLoadService, customerService, nextDayService);

        }

        public DatabaseService getDatabaseService(){
            return databaseService;
        }

        public LoginController getLoginController() {
            return loginController;
        }

        public PizzaGameController getPizzaGameController() {
            return pizzaGameController;
        }


}
