package jek.game;

import java.util.Scanner;

public class PizzaGame {

    public static boolean exit = false;

    // Här behövs det hämtas all sparad data ifrån DynamoDB

    public static void menu(){
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
                7: LOGOUT
                8: ACCOUNT MANAGEMENT
                9: SAVE & EXIT""");

            try {
                menuChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Not a valid choice");
            }

            switch (menuChoice){
                case 1:{
                    // SEE STATS BREAKDOWN & MANAGE UPGRADES
                }
                case 2:{
                    // ORDER NEW INGREDIENTS/TOPPINGS
                }
                case 3:{
                    // SERVE CUSTOMERS
                }
                case 4:{
                    // CREATE RECIPES & PREPARE BASIC INGREDIENTS
                }
                case 5:{
                    // PAY OFF LOAN, TAKE LOAN, CHANGE INTEREST?
                }
                case 6:{
                    // INCREASE DAYS PLAYED, GENERATE NEW CUSTOMERS, INCREMENT LOAN BY INTEREST / 365
                }
                case 7:{
                    // RETURN TO LOGINSCREEN & LOGOUT (what is logout? reset of activeUsername?)
                }
                case 8:{
                    // CHANGE PASSWORD AND/OR REMOVE ACCOUNT.
                }
                case 9:{

                    // SAVE-LOGIC HERE

                    exit = true;
                }
            }
        }


    }
}
