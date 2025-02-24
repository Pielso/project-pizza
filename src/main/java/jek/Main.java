package jek;

import jek.controllers.LoginController;
import jek.dependecies.DependencyContainer;


public class Main {

    public static void main(String[] args) throws Exception {

        DependencyContainer dependencyContainer = new DependencyContainer();
        LoginController loginController = dependencyContainer.getLoginController();


        dependencyContainer.getDatabaseService().dropDatabase();
        dependencyContainer.getDatabaseService().createDatabase();


        try {
            loginController.setPizzaGameController(dependencyContainer.getPizzaGameController());
            loginController.loginOrRegister();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}