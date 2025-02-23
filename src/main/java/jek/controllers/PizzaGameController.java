package jek.controllers;

import jek.services.ProgressService;

import java.sql.SQLException;
import java.util.Scanner;

public class PizzaGameController {

    public static boolean exit = false;

    // Här behövs det hämtas all sparad data ifrån DynamoDB

    public static void menu() throws SQLException, InterruptedException {
        exit = false;
        Scanner scan = new Scanner(System.in);
        int menuChoice = 0;

        while (!exit){
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

            try {
                menuChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Not a valid choice");
            }

            switch (menuChoice){
                case 1:{
                    // SEE STATS BREAKDOWN & MANAGE UPGRADES
                System.out.println(     "Your cash: " + ProgressService.getProgress().getCash() +
                                        "\nYour loan: " + ProgressService.getProgress().getLoan() +
                                        "\nYour interest rate: " + ProgressService.getProgress().getInterestRate() +
                                        "\nYour customers per day: " + ProgressService.getProgress().getCustomersPerDay() +
                                        "\nYour restaurant size: " + ProgressService.getProgress().getRestaurantSize() +
                                        "\nYour days played: " + ProgressService.getProgress().getDaysPlayed());
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
