package jek.services.system;

import com.google.protobuf.Value;
import jek.services.*;

import java.math.BigDecimal;



public class TextService {
    private final ProgressService progressService;
    private final RawIngredientService rawIngredientService;
    private final BasicIngredientService basicIngredientService;
    private final IngredientInventoryService ingredientInventoryService;
    private final ToppingService toppingService;

    public TextService(ProgressService progressService,  RawIngredientService rawIngredientService, IngredientInventoryService ingredientInventoryService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.progressService = progressService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.ingredientInventoryService = ingredientInventoryService;
        this.toppingService = toppingService;
    }

    public void centerText(String text){
        int consoleWidth = 200;
        int padding = (consoleWidth - text.length()) / 2;
        System.out.printf("%" + padding + "s%s%n", "", text);
    }

    public void loginScreen(){
        centerText("--------------------------------------------------------------------------< LOGIN >--------------------------------------------------------------------------");
        centerText("INPUT LOGIN CREDENTIALS TO CONTINUE PLAYING");
        centerText("IF YOU DONT HAVE AN ACCOUNT, INPUT 'REGISTER'");
        centerText("TO EXIT, INPUT 'EXIT'");
        centerText("");
    }

    public void createNewUserScreen(){
        centerText("--------------------------------------------------------------------< CREATE NEW USER >----------------------------------------------------------------------");
        centerText("");
        centerText("CHOOSE A USERNAME & PASSWORD");
        centerText("");
        centerText("YOU CANNOT HAVE 'REGISTER' OR 'EXIT' AS A USERNAME, AND IT CANNOT BE BLANK");
    }

    public void pizzaGameMenu(){
        System.out.println("""
                1: GO TO OFFICE
                2: GO TO PANTRY
                3: GO TO RESTAURANT
                4: GO TO KITCHEN
                5: GO TO THE BANK
                6: GO TO THE NEXT DAY
                7: RULES
                8: LOGOUT
                9: ACCOUNT MANAGEMENT
                10: SAVE & EXIT""");
    }

    public void officeStats(){
        centerText("----------------------------------------------------------------------< WELCOME TO YOUR OFFICE >----------------------------------------------------------------------");
        centerText("");
        centerText("Your cash: " + progressService.getProgress().getCash());
        centerText("Your loan: " + progressService.getProgress().getLoan());
        centerText("Your interest rate: " + progressService.getProgress().getInterestRate());
        centerText("");
        centerText("Your customers per day: " + progressService.getProgress().getCustomersPerDay());
        centerText("Your restaurant size: " + progressService.getProgress().getRestaurantSize());
        centerText("Your days played: " + progressService.getProgress().getDaysPlayed());
        centerText("");
    }

    public void printRawIngredientsAmountInStock(){
        centerText("RAW INGREDIENTS:");
        centerText("Flour: " + rawIngredientService.getRawIngredientAmountInStockById(1));
        centerText("Yeast: " + rawIngredientService.getRawIngredientAmountInStockById(2));
        centerText("Olive Oil: " + rawIngredientService.getRawIngredientAmountInStockById(3));
        centerText("Tomatoes: " + rawIngredientService.getRawIngredientAmountInStockById(4));
        centerText("Basil: " + rawIngredientService.getRawIngredientAmountInStockById(5));
        centerText("Garlic: " + rawIngredientService.getRawIngredientAmountInStockById(6));
    }

    public void printBasicIngredientsAmountInStock(){
        centerText("BASIC INGREDIENTS:");
        centerText("Dough: " + basicIngredientService.getBasicIngredientAmountInStockById(1));
        centerText("Tomato Sauce: " + basicIngredientService.getBasicIngredientAmountInStockById(2));
        centerText("Cheese: " + basicIngredientService.getBasicIngredientAmountInStockById(3));
    }

    public void printToppingsAmountInStock(){
        centerText("TOPPINGS:");
        centerText("Ham: " + toppingService.getToppingAmountInStockById(1));
        centerText("Mushroom: " + toppingService.getToppingAmountInStockById(2));
        centerText("Kebab: " + toppingService.getToppingAmountInStockById(3));
        centerText("Tuna: " + toppingService.getToppingAmountInStockById(4));
        centerText("Beef: " + toppingService.getToppingAmountInStockById(5));
        centerText("Chicken: " + toppingService.getToppingAmountInStockById(6));
        centerText("Pepperoni: " + toppingService.getToppingAmountInStockById(7));
        centerText("Olives: " + toppingService.getToppingAmountInStockById(8));
        centerText("Paprika: " + toppingService.getToppingAmountInStockById(9));
        centerText("Onion: " + toppingService.getToppingAmountInStockById(10));
        centerText("Pineapple: " + toppingService.getToppingAmountInStockById(11));
        centerText("Shrimps: " + toppingService.getToppingAmountInStockById(12));
        centerText("Bacon: " + toppingService.getToppingAmountInStockById(13));
        centerText("Jalapeños: " + toppingService.getToppingAmountInStockById(14));
        centerText("Sauce: " + toppingService.getToppingAmountInStockById(15));
    }


    public void pentryScreen(){
        centerText("--------------------------------------------------------------------------< YOUR PANTRY  >-------------------------------------------------------------------------");
        centerText("");
        centerText("YOUR CURRENT INVENTORY IS:");
        centerText("");
        printRawIngredientsAmountInStock();
        centerText("");
        printBasicIngredientsAmountInStock();
        centerText("");
        printToppingsAmountInStock();
        centerText("TOPPINGS:");

        centerText("");
        centerText("WHAT DO YOU WANT TO DO?");
        centerText("");
    }

    public void bankScreen(BigDecimal cash, BigDecimal loan, int interestRate){
        centerText("----------------------------------------------------------------< BANK OF CIRCULAR FOOD INVESTMENTS >----------------------------------------------------------------");
        centerText("");
        centerText("WELCOME");
        centerText("YOUR CURRENT STATUS IS:");
        centerText("");
        centerText("CASH: $" + cash);
        centerText("LOAN: $" + loan);
        centerText("INTEREST RATE: " + interestRate + "%");
        centerText("");
        centerText("WHAT CAN WE HELP YOU WITH TODAY?");
        centerText("");
    }



}




