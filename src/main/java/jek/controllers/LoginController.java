package jek.controllers;

import jek.models.Progress;
import jek.models.User;
import jek.services.ProgressService;
import jek.services.UserService;
import jek.services.system.LoginService;
import jek.services.system.SaveAndLoadService;
import jek.services.system.TextService;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class LoginController {

    public static Progress activeProgress = new Progress();
    public static User activeUser = new User();
    public static String tempUserName;
    public static String tempUserPassword;
    private final Scanner scan = new Scanner(System.in);

    private final LoginService loginService;
    private final TextService textService;
    private final ProgressService progressService;
    private final UserService userService;
    private final SaveAndLoadService saveAndLoadService;
    private PizzaGameController pizzaGameController; // Set by workaround in Main due to circular dependency w/ LoginController

    public LoginController(LoginService loginService, TextService textService, ProgressService progressService, UserService userService, SaveAndLoadService saveAndLoadService) {
        this.loginService = loginService;
        this.textService = textService;
        this.progressService = progressService;
        this.userService = userService;
        this.saveAndLoadService = saveAndLoadService;
    }

    public void setPizzaGameController(PizzaGameController pizzaGameController){
        this.pizzaGameController = pizzaGameController;
    }

    // Login session starts.
    public void loginOrRegister() throws SQLException, InterruptedException {
        loginService.flushActiveUserAndTemp();
        textService.loginScreen();
        boolean completedLogin = false;

        do {
            System.out.print("USERNAME: ");
            tempUserName = scan.nextLine().trim();
            if (tempUserName.equalsIgnoreCase("admin")) {
                loginService.adminMenu();
            }
            if (tempUserName.equalsIgnoreCase("exit")){
                loginService.flushTemp();
                break;
            }
            if (tempUserName.equalsIgnoreCase("register")){
                loginService.registerNewUser();
                completedLogin = true;
                progressService.createProgress(new Progress(userService.createUserAndReturnId(new User(tempUserName, tempUserPassword))));
                activeProgress.setUserId(userService.getUserIdByUsername(tempUserName));
                saveAndLoadService.saveAmountInStock();
                loginService.flushTemp();
                loginOrRegister();
            }
            else if (!userService.doesUsernameExistInDB(tempUserName)){
                loginService.flushTemp();
                System.out.println("User not found!");
            }
            else if (userService.doesUsernameExistInDB(tempUserName)) {
                if (loginService.checkPassword(3)){

                    userService.setActiveUserByUsername(tempUserName);
                    activeProgress = progressService.getProgressById(activeUser.getUserId());

                    // HERE DYNAMO_DB WILL FILL ALL AMOUNT_IN_STOCK
                    completedLogin = true;

                    saveAndLoadService.loadAmountInStock();
                    pizzaGameController.menu();
                }
                else {
                    loginService.flushActiveUserAndTemp();
                    System.out.println("You are logged out!");
                    loginOrRegister();
                }
            }
        }
        while (!completedLogin);
    }


}
