package jek.controllers;

import jek.models.Progress;
import jek.models.User;
import jek.services.ProgressService;
import jek.services.UserService;
import jek.services.system.DatabaseService;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LoginController {

    public static Progress activeProgress;
    public static User activeUser = new User();
    private static String tempUserName;
    private static String tempUserPassword;
    private static final Scanner scan = new Scanner(System.in);
    private static boolean loggedInOrExited = false;

    private TextService textService;
    private ProgressService progressService;
    private UserService userService;
    private PizzaGameController pizzaGameController; // Set by workaround in Main due to circular dependency w/ LoginController

    public LoginController(TextService textService, ProgressService progressService, UserService userService) {
        this.textService = textService;
        this.progressService = progressService;
        this.userService = userService;
    }

    public void setPizzaGameController(PizzaGameController pizzaGameController){
        this.pizzaGameController = pizzaGameController;
    }




    public void loginOrRegister() throws SQLException, InterruptedException {
        flushActiveUserAndTemp();
        textService.loginScreen();
        loggedInOrExited = false;

        do {
            System.out.print("USERNAME: ");
            tempUserName = scan.nextLine().trim();

            if (tempUserName.equalsIgnoreCase("exit")){
                flushTemp();
                loggedInOrExited = true;
            }

            else if (tempUserName.equalsIgnoreCase("register")){
                flushTemp();
                User user = createAndReturnNewUser();
                loggedInOrExited = true;
                userService.SaveNewUser(user);
                progressService.saveNewProgress(userService.getUserIdByUsername(tempUserName));
                flushTemp();
                loginOrRegister();
            }

            else if (!userService.doesUsernameExistInDB(tempUserName)){
                flushTemp();
                System.out.println("User not found!");
            }

            else if (userService.doesUsernameExistInDB(tempUserName)) {
                if (checkPassword(3)){

                    userService.setActiveUserByUsername(tempUserName);
                    activeProgress = progressService.getProgressById(activeUser.getUserId());

                    // HERE DYNAMO_DB WILL FILL ALL AMOUNT_IN_STOCK
                    loggedInOrExited = true;
                    pizzaGameController.menu();
                }
                else {
                    flushActiveUserAndTemp();
                    System.out.println("You are logged out!");
                    loginOrRegister();
                }
            }
        }
        while (!loggedInOrExited);
    }

    public User createAndReturnNewUser() throws SQLException {
        textService.createNewUserScreen();

        while (true){
            System.out.print("CHOOSE USERNAME: ");
            tempUserName = scan.nextLine().trim();
            if (tempUserName.equalsIgnoreCase("exit")
                    || tempUserName.equalsIgnoreCase("register")
                    || tempUserName.isEmpty()
                    || userService.doesUsernameExistInDB(tempUserName)) {
                System.out.println("That name is empty, reserved, taken or invalid and cannot be chosen. Choose something else.");
            }
            else break;
        }

        while (true) {
            System.out.print("CHOOSE PASSWORD: ");
            tempUserPassword = scan.nextLine();

            if (tempUserPassword.isEmpty()) {
                System.out.println("You cannot have an empty password!");
            }
            else break;
        }
        return new User(tempUserName, tempUserPassword);
    }


    public boolean checkPassword(int attempts) {
        String passInDB = userService.getPasswordByUsername(tempUserName);

        System.out.print("PASSWORD: ");
        tempUserPassword = scan.nextLine();

        while (!Objects.equals(tempUserPassword, passInDB)){
            if (attempts == 0) break;
            attempts--;
            System.out.println("Incorrect password. Try again");
            tempUserPassword = scan.nextLine();
        }
        return (passInDB.equals(tempUserPassword));
    }

    public void flushTemp(){
        tempUserName = "";
        tempUserPassword = "";
    }

    public void flushActiveUserAndTemp(){
        tempUserName = "";
        tempUserPassword = "";
        activeUser.setUserId(0);
        activeUser.setUsername(tempUserName);
        activeUser.setPassword(tempUserPassword);
    }

}
