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
                7: SAVE & EXIT""");

            try {
                menuChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Not a valid choice");
            }

            switch (menuChoice){
                case 1:{}
                case 2:{}
                case 3:{}
                case 4:{}
                case 5:{}
                case 6:{}
                case 7:{

                    // SAVE-LOGIC

                    exit = true;
                }
            }
        }


    }
}
