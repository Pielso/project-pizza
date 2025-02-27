package jek.controllers;

import jek.services.ProgressService;
import jek.services.system.TextService;
import java.sql.SQLException;
import java.util.Scanner;

public class PizzaGameController {

    public static boolean exit = false;
    private static final Scanner scan = new Scanner(System.in);

    private final TextService textService;
    private final ProgressService progressService;
    private final LoginController loginController;
    private final OfficeController officeController;
    private final PantryController pantryController;
    private final RestaurantController restaurantController;
    private final KitchenController kitchenController;
    private final BankController bankController;

    public PizzaGameController(TextService textService, ProgressService progressService, LoginController loginController, OfficeController officeController, PantryController pantryController, RestaurantController restaurantController, KitchenController kitchenController,  BankController bankController) {
        this.textService = textService;
        this.progressService = progressService;
        this.loginController = loginController;
        this.officeController = officeController;
        this.pantryController = pantryController;
        this.restaurantController = restaurantController;
        this.kitchenController = kitchenController;
        this.bankController = bankController;
    }

    public void menu() throws SQLException, InterruptedException {
        exit = false;

        int menuChoice = 0;

        while (!exit){

            textService.pizzaGameMenu();
            try {
                menuChoice = Integer.parseInt(scan.nextLine());
            }
            catch (Exception e) {
                System.out.println("Not a valid choice");
                scan.nextLine();

            }

            switch (menuChoice){
                case 1:{
                    // SEE STATS BREAKDOWN & MANAGE UPGRADES
                    officeController.goToOffice();
                    break;
                }
                case 2:{
                    // ORDER NEW INGREDIENTS/TOPPINGS
                    pantryController.goToPantry();
                    break;
                }
                case 3:{
                    // SERVE CUSTOMERS
                    restaurantController.goToRestaurant();
                    break;
                }
                case 4:{
                    // CREATE RECIPES & PREPARE BASIC INGREDIENTS
                    kitchenController.goToKitchen();
                    break;
                }
                case 5:{
                    // PAY OFF LOAN, TAKE LOAN, CHANGE INTEREST?
                    bankController.goToBank();
                    break;
                }
                case 6:{
                    // INCREASE DAYS PLAYED, GENERATE NEW CUSTOMERS, INCREMENT LOAN BY INTEREST / 365

                    break;
                }
                case 7:{
                    // RULES HERE

                    break;
                }
                case 8:{
                    // RETURN TO LOGIN-SCREEN & LOGOUT (what is logout? reset of activeUsername?)
                    exit = true;
                    loginController.loginOrRegister();
                    break;
                }
                case 9:{
                    // CHANGE PASSWORD AND/OR REMOVE ACCOUNT.

                    break;
                }
                case 10:{
                    // SAVE AMOUNT_IN_STOCK LOGIC TO DYNAMODB HERE

                    // CONFIRM-SAVE-LOGIC HERE?

                    exit = true;
                    break;
                }
            }
        }
    }
}
