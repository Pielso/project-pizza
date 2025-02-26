package jek.dependecies;

import jek.controllers.*;
import jek.repositories.*;
import jek.services.*;
import jek.services.system.*;

public class DependencyContainer {

        // System Services
        private final DatabaseService databaseService;
        private final DynamoDBService dynamoDBService;
        private final SaveAndLoadService saveAndLoadService;
        private final TextService textService;

        // Repositories
        private final BasicIngredientRepository basicIngredientRepository;
        private final RawIngredientRepository rawIngredientRepository;
        private final CustomerRepository customerRepository;
        private final ProgressRepository progressRepository;
        private final RecipeRepository recipeRepository;
        private final ToppingRepository toppingRepository;
        private final UserRepository userRepository;
        private final RecipeToppingRepository recipeToppingRepository;

        // Services
        private final IngredientInventoryService ingredientInventoryService;
        private final BasicIngredientService basicIngredientService;
        private final RawIngredientService rawIngredientService;
        private final CustomerService customerService;
        private final ProgressService progressService;
        private final RecipeService recipeService;
        private final ToppingService toppingService;
        private final UserService userService;
        private final RecipeToppingService recipeToppingService;

        // Controllers
        private final BankController bankController;
        private final KitchenController kitchenController;
        private final LoginController loginController;
        private final OfficeController officeController;
        private final PantryController pantryController;
        private final PizzaGameController pizzaGameController;
        private final RestaurantController restaurantController;

        public DependencyContainer() {

            // Create System Services
            this.databaseService = new DatabaseService();
            this.dynamoDBService = new DynamoDBService();
            this.saveAndLoadService = new SaveAndLoadService();

            // Create repositories, which should only need DatabaseService
            this.basicIngredientRepository = new BasicIngredientRepository(databaseService);
            this.rawIngredientRepository = new RawIngredientRepository(databaseService);
            this.customerRepository = new CustomerRepository(databaseService);
            this.progressRepository = new ProgressRepository(databaseService);
            this.recipeRepository = new RecipeRepository(databaseService);
            this.toppingRepository = new ToppingRepository(databaseService);
            this.userRepository = new UserRepository(databaseService);
            this.recipeToppingRepository = new RecipeToppingRepository(databaseService);

            // Create services that for most part only should need their own repository.
            this.recipeToppingService = new RecipeToppingService(recipeToppingRepository);
            this.ingredientInventoryService = new IngredientInventoryService(databaseService);
            this.basicIngredientService = new BasicIngredientService(basicIngredientRepository);
            this.rawIngredientService = new RawIngredientService(rawIngredientRepository);
            this.customerService = new CustomerService(customerRepository);
            this.progressService = new ProgressService(progressRepository);
            this.recipeService = new RecipeService(recipeRepository);
            this.toppingService = new ToppingService(toppingRepository);
            this.userService = new UserService(userRepository);
            this.textService = new TextService(progressService, rawIngredientService, ingredientInventoryService, basicIngredientService, toppingService);

            // Skapa controllers och injicera tjänster
            this.bankController = new BankController(textService, progressService);
            this.kitchenController = new KitchenController(textService, progressService, recipeService, rawIngredientService, basicIngredientService, toppingService, recipeToppingService);
            this.officeController = new OfficeController(textService, progressService);
            this.pantryController = new PantryController(textService, progressService, rawIngredientService, basicIngredientService, toppingService);
            this.restaurantController = new RestaurantController(textService, progressService, customerService, recipeService);
            this.loginController = new LoginController(textService, progressService, userService);
            this.pizzaGameController = new PizzaGameController(textService, progressService, loginController, officeController, pantryController, restaurantController, kitchenController, bankController);
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

        public KitchenController getKitchenController() {
            return kitchenController;
        }

        public BankController getBankController() {
            return bankController;
        }

        public OfficeController getOfficeController() {
            return officeController;
        }

        public PantryController getPentryController() {
            return pantryController;
        }

        public RestaurantController getRestaurantController() {
            return restaurantController;
        }

        public DynamoDBService getDynamoDBService() {
            return dynamoDBService;
        }

        public SaveAndLoadService getSaveAndLoadService() {
            return saveAndLoadService;
        }

        public TextService getTextService() {
            return textService;
        }

        public BasicIngredientRepository getBasicIngredientRepository() {
            return basicIngredientRepository;
        }

        public RawIngredientRepository getRawIngredientRepository() {
            return rawIngredientRepository;
        }

        public CustomerRepository getCustomerRepository() {
            return customerRepository;
        }

        public ProgressRepository getProgressRepository() {
            return progressRepository;
        }

        public RecipeRepository getRecipeRepository() {
            return recipeRepository;
        }

        public ToppingRepository getToppingRepository() {
            return toppingRepository;
        }

        public UserRepository getUserRepository() {
            return userRepository;
        }

        public BasicIngredientService getBasicIngredientService() {
            return basicIngredientService;
        }

        public RawIngredientService getRawIngredientService() {
            return rawIngredientService;
        }

        public CustomerService getCustomerService() {
            return customerService;
        }

        public ProgressService getProgressService() {
            return progressService;
        }

        public RecipeService getRecipeService() {
            return recipeService;
        }

        public ToppingService getToppingService() {
            return toppingService;
        }

        public UserService getUserService() {
            return userService;
        }
        public IngredientInventoryService getIngredientInventoryService(){
            return ingredientInventoryService;
        }
}
