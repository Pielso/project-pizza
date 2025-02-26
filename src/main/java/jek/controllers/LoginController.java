package jek.controllers;

import jek.models.Progress;
import jek.models.User;
import jek.services.ProgressService;
import jek.services.UserService;
import jek.services.system.TextService;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class LoginController {

    public static Progress activeProgress;
    public static User activeUser = new User();
    public static String tempUserName;
    public static String tempUserPassword;
    private final Scanner scan = new Scanner(System.in);

    private final TextService textService;
    private final ProgressService progressService;
    private final UserService userService;
    private PizzaGameController pizzaGameController; // Set by workaround in Main due to circular dependency w/ LoginController

    public LoginController(TextService textService, ProgressService progressService, UserService userService) {
        this.textService = textService;
        this.progressService = progressService;
        this.userService = userService;
    }

    public void setPizzaGameController(PizzaGameController pizzaGameController){
        this.pizzaGameController = pizzaGameController;
    }

    // Login session starts.
    public void loginOrRegister() throws SQLException, InterruptedException {
        flushActiveUserAndTemp();
        textService.loginScreen();
        boolean loggedInOrExited = false;

        do {
            System.out.print("USERNAME: ");
            tempUserName = scan.nextLine().trim();

            if (tempUserName.equalsIgnoreCase("exit")){
                flushTemp();
                loggedInOrExited = true;
            }

            else if (tempUserName.equalsIgnoreCase("register")){
                flushTemp();
                textService.createNewUserScreen();
                userChoosesUsername();
                userChoosesPassword();
                loggedInOrExited = true;
                progressService.createProgress(userService.createUserAndReturnId(tempUserName, tempUserPassword));
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

    private void userChoosesUsername() throws SQLException {
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
    }

    private void userChoosesPassword(){
        while (true) {
            System.out.print("CHOOSE PASSWORD: ");
            tempUserPassword = scan.nextLine();

            if (tempUserPassword.isEmpty()) {
                System.out.println("You cannot have an empty password!");
            }
            else break;
        }
    }

    private boolean checkPassword(int attempts) {
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

    private void flushTemp(){
        tempUserName = "";
        tempUserPassword = "";
    }

    private void flushActiveUserAndTemp(){
        tempUserName = "";
        tempUserPassword = "";
        activeUser.setUserId(0);
        activeUser.setUsername(tempUserName);
        activeUser.setPassword(tempUserPassword);
    }

}
