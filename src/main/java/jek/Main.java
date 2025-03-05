package jek;

import jek.controllers.LoginController;
import jek.dependecies.DependencyContainer;


public class Main {
    public static void main(String[] args) {
        DependencyContainer dependencyContainer = new DependencyContainer();
        LoginController loginController = dependencyContainer.getLoginController();

        try {
            loginController.setPizzaGameController(dependencyContainer.getPizzaGameController());
            loginController.loginOrRegister();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}