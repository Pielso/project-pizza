package jek.controllers;

import jek.services.*;
import jek.services.system.NextDayService;
import jek.services.system.SaveAndLoadService;
import jek.services.system.TextService;
import java.sql.SQLException;
import java.util.Scanner;

public class PizzaGameController {

    public static boolean exit = false;
    private static final Scanner scan = new Scanner(System.in);

    private final TextService textService;
    private final LoginController loginController;
    private final OfficeController officeController;
    private final PantryController pantryController;
    private final RestaurantController restaurantController;
    private final KitchenController kitchenController;
    private final BankController bankController;
    private final SaveAndLoadService saveAndLoadService;
    private final CustomerService customerService;
    private final NextDayService nextDayService;


    public PizzaGameController(TextService textService, LoginController loginController, OfficeController officeController, PantryController pantryController, RestaurantController restaurantController, KitchenController kitchenController, BankController bankController, SaveAndLoadService saveAndLoadService, CustomerService customerService, NextDayService nextDayService) {
        this.textService = textService;
        this.loginController = loginController;
        this.officeController = officeController;
        this.pantryController = pantryController;
        this.restaurantController = restaurantController;
        this.kitchenController = kitchenController;
        this.bankController = bankController;
        this.saveAndLoadService = saveAndLoadService;
        this.customerService = customerService;
        this.nextDayService = nextDayService;

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
            }

            switch (menuChoice){
                case 1:{
                    officeController.goToOffice();
                    break;
                }
                case 2:{
                    pantryController.goToPantry();
                    break;
                }
                case 3:{
                    restaurantController.goToRestaurant();
                    break;
                }
                case 4:{
                    kitchenController.goToKitchen();
                    break;
                }
                case 5:{
                    bankController.goToBank();
                    break;
                }
                case 6:{
                    nextDayService.goToNextDay();
                    break;
                }
                case 7:{
                    exit = true;
                    customerService.deleteAllCustomers();
                    saveAndLoadService.saveAmountInStock();
                    saveAndLoadService.resetAmountInStock();
                    loginController.loginOrRegister();
                    break;
                }
                case 8:{
                    customerService.deleteAllCustomers();
                    saveAndLoadService.saveAmountInStock();
                    saveAndLoadService.dropInventory();
                    exit = true;
                    break;
                }
            }
        }
    }
}
