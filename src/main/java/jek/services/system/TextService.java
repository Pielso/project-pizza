package jek.services.system;

import jek.services.ProgressService;
import java.math.BigDecimal;


public class TextService {
    private final ProgressService progressService;

    public TextService(ProgressService progressService) {
        this.progressService = progressService;
    }

    public void centerText(String text){
        int consoleWidth = 200;
        int padding = (consoleWidth - text.length()) / 2;
        System.out.printf("%" + padding + "s%s%n", "", text);
    }

    public void loginScreen(){
        centerText("--------------------------------------------------------------------------< LOGIN >--------------------------------------------------------------------------");
        centerText("INPUT LOGIN CREDENTIALS TO CONTINUE PLAYING");
        centerText("IF YOU DONT HAVE AN ACCOUNT, INPUT 'REGISTER'");
        centerText("TO EXIT, INPUT 'EXIT'");
        centerText("");
    }

    public void createNewUserScreen(){
        centerText("--------------------------------------------------------------------< CREATE NEW USER >----------------------------------------------------------------------");
        centerText("");
        centerText("CHOOSE A USERNAME & PASSWORD");
        centerText("");
        centerText("YOU CANNOT HAVE 'REGISTER' OR 'EXIT' AS A USERNAME, AND IT CANNOT BE BLANK");
    }

    public void pizzaGameMenu(){
        System.out.println("""
                1: GO TO OFFICE
                2: GO TO PANTRY
                3: GO TO RESTAURANT
                4: GO TO KITCHEN
                5: GO TO THE BANK
                6: GO TO THE NEXT DAY
                7: RULES
                8: LOGOUT
                9: ACCOUNT MANAGEMENT
                10: SAVE & EXIT""");
    }

    public void officeStats(){
        centerText("----------------------------------------------------------------------< WELCOME TO YOUR OFFICE >----------------------------------------------------------------------");
        centerText("");
        centerText("Your cash: " + progressService.getProgress().getCash());
        centerText("Your loan: " + progressService.getProgress().getLoan());
        centerText("Your interest rate: " + progressService.getProgress().getInterestRate());
        centerText("");
        centerText("Your customers per day: " + progressService.getProgress().getCustomersPerDay());
        centerText("Your restaurant size: " + progressService.getProgress().getRestaurantSize());
        centerText("Your days played: " + progressService.getProgress().getDaysPlayed());
        centerText("");
    }

    public void pentryScreen(){
        centerText("----------------------------------------------------------------< YOUR PENTRY FOOD INVESTMENTS >----------------------------------------------------------------");
        centerText("");
        centerText("WELCOME");
        centerText("YOUR CURRENT STATUS IS:");
        centerText("");
        centerText("CASH: $" + cash);
        centerText("LOAN: $" + loan);
        centerText("INTEREST RATE: " + interestRate + "%");
        centerText("");
        centerText("WHAT CAN WE HELP YOU WITH TODAY?");
        centerText("");
    }

    public void bankScreen(BigDecimal cash, BigDecimal loan, int interestRate){
        centerText("----------------------------------------------------------------< BANK OF CIRCULAR FOOD INVESTMENTS >----------------------------------------------------------------");
        centerText("");
        centerText("WELCOME");
        centerText("YOUR CURRENT STATUS IS:");
        centerText("");
        centerText("CASH: $" + cash);
        centerText("LOAN: $" + loan);
        centerText("INTEREST RATE: " + interestRate + "%");
        centerText("");
        centerText("WHAT CAN WE HELP YOU WITH TODAY?");
        centerText("");
    }



}




