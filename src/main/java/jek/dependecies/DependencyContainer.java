package jek.dependecies;

import jek.controllers.*;
import jek.repositories.*;
import jek.services.*;
import jek.services.system.*;

public class DependencyContainer {

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

        // Services
        private final BasicIngredientService basicIngredientService;
        private final RawIngredientService rawIngredientService;
        private final CustomerService customerService;
        private final ProgressService progressService;
        private final RecipeService recipeService;
        private final ToppingService toppingService;
        private final UserService userService;

        // Controllers
        private final BankController bankController;
        private final KitchenController kitchenController;
        private final LoginController loginController;
        private final OfficeController officeController;
        private final PentryController pentryController;
        private final PizzaGameController pizzaGameController;
        private final RestaurantController restaurantController;

        public DependencyContainer() {

            // Skapa systemtjänster
            this.databaseService = new DatabaseService();
            this.dynamoDBService = new DynamoDBService();
            this.saveAndLoadService = new SaveAndLoadService();


            // Skapa repositories (de behöver bara DatabaseService)
            this.basicIngredientRepository = new BasicIngredientRepository(databaseService);
            this.rawIngredientRepository = new RawIngredientRepository(databaseService);
            this.customerRepository = new CustomerRepository(databaseService);
            this.progressRepository = new ProgressRepository(databaseService);
            this.recipeRepository = new RecipeRepository(databaseService);
            this.toppingRepository = new ToppingRepository(databaseService);
            this.userRepository = new UserRepository(databaseService);


            // Skapa services och injicera repositories
            this.basicIngredientService = new BasicIngredientService(basicIngredientRepository);
            this.rawIngredientService = new RawIngredientService(rawIngredientRepository);
            this.customerService = new CustomerService(customerRepository);
            this.progressService = new ProgressService(progressRepository);
            this.recipeService = new RecipeService(recipeRepository);
            this.toppingService = new ToppingService(toppingRepository);
            this.userService = new UserService(userRepository);
            this.textService = new TextService(progressService);

            // Skapa controllers och injicera tjänster
            this.bankController = new BankController(textService, progressService);
            this.kitchenController = new KitchenController(textService, progressService, recipeService, rawIngredientService, basicIngredientService, toppingService);
            this.officeController = new OfficeController(textService, progressService);
            this.pentryController = new PentryController(textService, progressService, rawIngredientService, basicIngredientService, toppingService);
            this.restaurantController = new RestaurantController(textService, progressService, customerService, recipeService);
            this.loginController = new LoginController(textService, progressService, userService);
            this.pizzaGameController = new PizzaGameController(textService, progressService, loginController, officeController, pentryController, restaurantController, kitchenController, bankController);
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

        public PentryController getPentryController() {
            return pentryController;
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
}
