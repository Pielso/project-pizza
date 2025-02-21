package jek;

import jek.accounts.LoginService;
import jek.database.DatabaseManager;
import jek.game.PizzaGame;

public class Main {

    public static void main(String[] args) throws Exception {

        DatabaseManager.dropDatabase();
        DatabaseManager.createDatabase();
        DatabaseManager.insertToppingsAndIngredients();

        while (!PizzaGame.exit){
            LoginService.loginScreen();
        }



    }
}