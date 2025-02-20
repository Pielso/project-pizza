package jek.accounts;

import jek.database.DatabaseManager;
import jek.game.PizzaGame;
import jek.screens.StringScreens;

import java.sql.*;
import java.util.*;

public class LoginService {

    private static String activeUsername;
    private static String activeUserPassword;
    public static Scanner scan = new Scanner(System.in);

    public static void wait(int millisec) throws InterruptedException {
        Thread.sleep(millisec);
    }

    public static boolean checkIfUserNameAndPassWasSaved(String usernameToCheck, String passwordToCheck) throws SQLException {

        String lastSavedUser = "";
        String lastSavedPass = "";
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users ORDER BY user_id DESC LIMIT 1");

        if (resultSet.next()){
            lastSavedUser = resultSet.getString("username");
            lastSavedPass = resultSet.getString("password");
        }

        return lastSavedUser.equals(usernameToCheck) && lastSavedPass.equals(passwordToCheck);
    }

    public static boolean doesUsernameExistInDB(String nameGiven) throws SQLException {
        List <String> listOfUsernames = DatabaseManager.columnToList("username", "users");
        return listOfUsernames.contains(nameGiven.toLowerCase());
    }

    public static boolean checkPassword(String usernameToCheck) throws SQLException {
        String passInDB = "";
        int attempts = 0;
        System.out.print("PASSWORD:");
        activeUserPassword = scan.nextLine();
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT password FROM users WHERE username = '" + usernameToCheck + "'; ");

        if (resultSet.next()){
            passInDB = resultSet.getString("password");
        }
        while (!Objects.equals(activeUserPassword, passInDB)){
            if (attempts > 2) break;
            attempts = attempts+1;
            System.out.println("Incorrect password. Try again");
            activeUserPassword = scan.nextLine();
        }


        return (passInDB.equals(activeUserPassword));
    }

    public static void loginScreen() throws SQLException, InterruptedException {
        scan = new Scanner(System.in);
        System.out.print(StringScreens.loginScreen);
        activeUsername = scan.nextLine().trim().toLowerCase();

        if (activeUsername.equalsIgnoreCase("register")){
            createNewUser();
        }
        else if (doesUsernameExistInDB(activeUsername)) {
            if (checkPassword(activeUsername)){
                PizzaGame.menu();
            }
            else {
                System.out.println("You are logged out!");
            }


        }

    }

    public static void createNewUser() throws SQLException, InterruptedException {
        System.out.print(StringScreens.createNewUser);

        do {
            activeUsername = scan.next().trim().toLowerCase();
            if (doesUsernameExistInDB(activeUsername)){
                System.out.println("User already exists, try another name.");
            }
        }
        while (doesUsernameExistInDB(activeUsername));
        System.out.print("PASSWORD: ");

        do {
            activeUserPassword = scan.nextLine();
        }
        while (activeUserPassword.isEmpty());

        saveUsernameAndPassword(activeUsername, activeUserPassword);

        if (checkIfUserNameAndPassWasSaved(activeUsername, activeUserPassword)){
            wait(900);
            System.out.println("New user created and saved to the database.");
            wait(900);
        }
        System.out.println("\nWelcome. Log in to start your journey to fame, and most importantly, fortune!");
    }

    public static void saveUsernameAndPassword(String username, String password) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        ps.execute();
    }

}
