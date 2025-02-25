package jek.controllers;

import jek.services.BasicIngredientService;
import jek.services.ProgressService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;
import jek.services.system.TextService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import static jek.controllers.LoginController.activeProgress;

public class PentryController {
    private TextService textService;
    private ProgressService progressService;
    private RawIngredientService rawIngredientService;
    private BasicIngredientService basicIngredientService;
    private ToppingService toppingService;
    private final Scanner scan = new Scanner(System.in);

    public PentryController(TextService textService, ProgressService progressService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.textService = textService;
        this.progressService = progressService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
    }

    public void goToTheBank(){
        boolean exitFromBank = false;
        BigDecimal amount;


        do {
            textService.bankScreen(activeProgress.getCash(), activeProgress.getLoan(), activeProgress.getInterestRate());
            System.out.println("""
                    1: PAY OFF LOAN
                    2: INCREASE YOUR LOAN WITH $50'000 MORE (Max $100'000)
                    3: EXIT FROM BANK""");

            int menuChoice = scan.nextInt();
            try {
                switch (menuChoice){

                    case 1: {
                        System.out.println("How much do you want to pay back to the bank?");
                        System.out.println("AMOUNT: ");
                        try {
                            amount = scan.nextBigDecimal();
                            if (amount.compareTo(BigDecimal.valueOf(0)) < 1){
                                System.out.println("Negative values are cheating, which is of course is illegal. The authorities are informed and on their way. Pack a bag and leave while you can.");
                                break;
                            }
                            else if (activeProgress.getCash().compareTo(amount) < 0){
                                System.out.println("You dont have enough money.");
                                break;
                            }
                            else if (activeProgress.getLoan().compareTo(amount) < 0){
                                System.out.println("This is more than you owe, so here is your change.");
                                activeProgress.setCash(activeProgress.getCash().subtract(activeProgress.getLoan()));
                                activeProgress.setLoan(activeProgress.getLoan().subtract(activeProgress.getLoan()));
                                progressService.updateProgress(activeProgress);
                                break;
                            }
                            else {
                                activeProgress.setCash(activeProgress.getCash().subtract(amount));
                                activeProgress.setLoan(activeProgress.getLoan().subtract(amount));
                                progressService.updateProgress(activeProgress);
                                break;
                            }

                        }
                        // Denna funkar inte
                        catch (InputMismatchException e) {
                            System.out.println("Not a valid sum");
                        }
                        break;
                    }
                    case 2: {

                        // INGEN FELHANTERING ALLS!!!

                        if (activeProgress.getLoan().compareTo(BigDecimal.valueOf(50000)) > 0){
                            System.out.println("Your loan need to be less than $50'000 for this to be approved.");
                            break;
                        }
                        else {
                            activeProgress.setCash(activeProgress.getCash().add(BigDecimal.valueOf(50000)));
                            activeProgress.setLoan(activeProgress.getLoan().add(BigDecimal.valueOf(50000)));
                            progressService.updateProgress(activeProgress);
                        }

                        break;
                    }
                    case 3: {
                        exitFromBank = true;
                        break;
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        while (!exitFromBank);

    }
}
