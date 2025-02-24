package jek.controllers;

import jek.services.ProgressService;
import jek.services.system.TextService;
import java.sql.SQLException;
import java.util.Scanner;

import static jek.controllers.LoginController.activeProgress;



public class PizzaGameController {

    public static boolean exit = false;
    private static final Scanner scan = new Scanner(System.in);

    private TextService textService;
    private ProgressService progressService;
    private LoginController loginController;
    private OfficeController officeController;
    private PentryController pentryController;
    private RestaurantController restaurantController;
    private KitchenController kitchenController;
    private BankController bankController;

    public PizzaGameController(TextService textService, ProgressService progressService, LoginController loginController, OfficeController officeController, PentryController pentryController, RestaurantController restaurantController, KitchenController kitchenController, BankController bankController) {
        this.textService = textService;
        this.progressService = progressService;
        this.loginController = loginController;
        this.officeController = officeController;
        this.pentryController = pentryController;
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
                menuChoice = scan.nextInt();
            }
            catch (Exception e) {
                System.out.println("Not a valid choice");
                scan.nextLine();

            }

            switch (menuChoice){
                case 1:{
                    // SEE STATS BREAKDOWN & MANAGE UPGRADES
                    officeController.goToOffice();
                }
                case 2:{
                    // ORDER NEW INGREDIENTS/TOPPINGS

                    break;
                }
                case 3:{
                    // SERVE CUSTOMERS

                    break;
                }
                case 4:{
                    // CREATE RECIPES & PREPARE BASIC INGREDIENTS

                    break;
                }
                case 5:{
                    // PAY OFF LOAN, TAKE LOAN, CHANGE INTEREST?

                    bankController.goToTheBank();

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

                    // SAVE-LOGIC HERE

                    // CONFIRM-SAVE-LOGIC HERE

                    exit = true;
                    break;
                }
            }
        }
    }
}
