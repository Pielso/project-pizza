package jek.controllers;

import jek.models.Progress;
import jek.models.User;
import jek.services.system.DatabaseService;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static jek.services.ProgressService.loadProgressFromDatabase;
import static jek.services.ProgressService.saveNewProgress;
import static jek.services.UserService.*;

public class LoginController {

    public static Progress activeProgress;
    public static User activeUser = new User();
    private static String tempUserName;
    private static String tempUserPassword;
    private static final Scanner scan = new Scanner(System.in);
    private static boolean loggedInOrExited = false;

    private static void flushTemp(){
        tempUserName = "";
        tempUserPassword = "";
    }

    private static void flushActiveUserAndTemp(){
        tempUserName = "";
        tempUserPassword = "";
        activeUser.setUserId(0);
        activeUser.setUsername(tempUserName);
        activeUser.setPassword(tempUserPassword);
    }

    public static void loginOrRegister() throws SQLException, InterruptedException {
        flushActiveUserAndTemp();
        TextService.loginScreen();
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
                SaveNewUser(user);
                saveNewProgress(getUserIdByUsername(tempUserName), new BigDecimal(10000), new BigDecimal(50000), 5, 7, 1, 0);
                flushTemp();
                loginOrRegister();
            }

            else if (!doesUsernameExistInDB(tempUserName)){
                flushTemp();
                System.out.println("User not found!");
            }

            else if (doesUsernameExistInDB(tempUserName)) {
                if (checkPassword(3)){

                    setActiveUserByUsername(tempUserName);
                    activeProgress = loadProgressFromDatabase(activeUser.getUserId());
                    PizzaGameController.menu();
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

    private static User createAndReturnNewUser() throws SQLException {
        TextService.createNewUserScreen();

        while (true){
            System.out.print("CHOOSE USERNAME: ");
            tempUserName = scan.nextLine().trim();
            if (tempUserName.equalsIgnoreCase("exit")
                    || tempUserName.equalsIgnoreCase("register")
                    || tempUserName.isEmpty()
                    || doesUsernameExistInDB(tempUserName)) {
                System.out.println("That name is reserved, taken or invalid and cannot be chosen. Choose something else.");
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


    private static boolean checkPassword(int attempts) {
        String passInDB = getPasswordByUsername(tempUserName);

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

    private static String getPasswordByUsername(String name) {
        String query = "SELECT password FROM users WHERE username = ?;";
        String passInDB = "";

        try (Connection connection = DatabaseService.getConnection()){

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                passInDB = rs.getString("password");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passInDB;
    }

    private static boolean doesUsernameExistInDB(String name) throws SQLException {
        List<String> listOfUsernames = DatabaseService.columnToList("username", "users");
        return listOfUsernames.contains(name);
    }

}
