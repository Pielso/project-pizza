package jek.controllers;

import jek.services.ProgressService;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import static jek.controllers.BankController.goToTheBank;
import static jek.controllers.LoginController.activeProgress;
import static jek.controllers.OfficeController.goToOffice;
import static jek.services.ProgressService.saveProgress;
import static jek.services.system.TextService.*;

public class PizzaGameController {

    public static boolean exit = false;
    private static final Scanner scan = new Scanner(System.in);


    public static void menu() throws SQLException, InterruptedException {
        exit = false;

        int menuChoice = 0;

        while (!exit){

            pizzaGameMenu();
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
                    goToOffice();
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

                    goToTheBank();

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
                    LoginController.loginOrRegister();

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
