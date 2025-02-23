package jek;

import jek.controllers.LoginController;
import jek.services.system.DatabaseService;
import jek.controllers.PizzaGameController;

public class Main {

    public static void main(String[] args) throws Exception {

        //DatabaseService.dropDatabase();
        //DatabaseService.createDatabase();
        DatabaseService.createInitialInventory();

        LoginController.loginOrRegister();

    }
}