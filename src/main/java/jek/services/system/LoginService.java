package jek.services.system;

import jek.dependecies.DependencyContainer;
import jek.services.UserService;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static jek.controllers.LoginController.*;

public class LoginService {
    private final Scanner scan  = new Scanner(System.in);
    private final TextService textService;
    private final UserService userService;
    private final SaveAndLoadService saveAndLoadService;

    public LoginService(TextService textService, UserService userService, SaveAndLoadService saveAndLoadService) {
        this.textService = textService;
        this.userService = userService;
        this.saveAndLoadService = saveAndLoadService;
    }

    public void registerNewUser() {
        flushTemp();
        textService.createNewUserScreen();
        userChoosesUsername();
        userChoosesPassword();
    }

    public void userChoosesUsername() {
        while (true){
            System.out.print("CHOOSE USERNAME: ");
            tempUserName = scan.nextLine().trim();
            if (tempUserName.equalsIgnoreCase("exit")
                    || tempUserName.equalsIgnoreCase("register")
                    || tempUserName.equalsIgnoreCase("admin")
                    || tempUserName.isEmpty()
                    || userService.doesUsernameExistInDB(tempUserName)) {
                System.out.println("That name is empty, reserved, taken or invalid and cannot be chosen. Choose something else.");
            }
            else break;
        }
    }

    public void userChoosesPassword(){
        while (true) {
            System.out.print("CHOOSE PASSWORD: ");
            tempUserPassword = scan.nextLine();

            if (tempUserPassword.isEmpty()) {
                System.out.println("You cannot have an empty password!");
            }
            else break;
        }
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

    /**
     * Lets admin drop and resurrect all sql-tables in database and/or drop all items in DynamoDB
     * @throws SQLException - Because I felt that a try-catch around IF > WHILE > SWITCH w/ Default would be overkill.
     */
    public void adminMenu() throws SQLException {
        System.out.print("PASSWORD: ");
        tempUserPassword = scan.nextLine().trim();
        if (tempUserPassword.equalsIgnoreCase("admin")){
            boolean menuLoop = true;
            while (menuLoop){
                System.out.println("1: DROP AND RESURRECT DATABASE (MySql)");
                System.out.println("2: DROP DATABASE (NoSql)");
                System.out.println("3: EXIT");
                int choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1:{
                        DependencyContainer dependencyContainer = new DependencyContainer();
                        dependencyContainer.getDatabaseService().dropDatabase();
                        dependencyContainer.getDatabaseService().createDatabase();
                        flushTemp();
                        break;
                    }
                    case 2:{
                        saveAndLoadService.dropAllAmountInStockInDynamoDB();
                        flushTemp();
                        break;
                    }
                    case 3:{
                        flushTemp();
                        menuLoop = false;
                        break;
                    }
                    default:{
                        flushTemp();
                        System.out.println("Wrong choice. Try again.");
                    }
                }
            }
        }
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
