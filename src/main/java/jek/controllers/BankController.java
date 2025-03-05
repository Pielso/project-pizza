package jek.controllers;

import jek.services.ProgressService;
import jek.services.system.TextService;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankController {
    public final Scanner scan = new Scanner(System.in);
    private final TextService textService;
    private final ProgressService progressService;

    public BankController(TextService textService, ProgressService progressService) {
        this.textService = textService;
        this.progressService = progressService;
    }

    public void goToBank(){
        boolean exitFromBank = false;
        BigDecimal amount;

        do {
            textService.bankScreen(progressService.getActiveProgress().getCash(), progressService.getActiveProgress().getLoan(), progressService.getActiveProgress().getInterestRate());
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
                            else if (progressService.getActiveProgress().getCash().compareTo(amount) < 0){
                                System.out.println("You dont have enough money.");
                                break;
                            }
                            else if (progressService.getActiveProgress().getLoan().compareTo(amount) < 0){
                                System.out.println("This is more than you owe, so here is your change.");
                                progressService.getActiveProgress().setCash(progressService.getActiveProgress().getCash().subtract(progressService.getActiveProgress().getLoan()));
                                progressService.getActiveProgress().setLoan(progressService.getActiveProgress().getLoan().subtract(progressService.getActiveProgress().getLoan()));
                                progressService.updateProgressCashById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getCash());
                                progressService.updateProgressLoanById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getLoan());
                                break;
                            }
                            else {
                                progressService.getActiveProgress().setCash(progressService.getActiveProgress().getCash().subtract(amount));
                                progressService.getActiveProgress().setLoan(progressService.getActiveProgress().getLoan().subtract(amount));
                                progressService.updateProgressCashById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getCash());
                                progressService.updateProgressLoanById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getLoan());
                                break;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Not a valid sum");
                        }
                        break;
                    }
                    case 2: {

                        if (progressService.getActiveProgress().getLoan().compareTo(BigDecimal.valueOf(50000)) > 0){
                            System.out.println("Your loan need to be less than $50'000 for this to be approved.");
                            break;
                        }
                        else {
                            progressService.getActiveProgress().setCash(progressService.getActiveProgress().getCash().add(BigDecimal.valueOf(50000)));
                            progressService.getActiveProgress().setLoan(progressService.getActiveProgress().getLoan().add(BigDecimal.valueOf(50000)));
                            progressService.updateProgressCashById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getCash());
                            progressService.updateProgressLoanById(progressService.getActiveProgress().getUserId(), progressService.getActiveProgress().getLoan());
                        }
                        break;
                    }
                    case 3: {
                        exitFromBank = true;
                        break;
                    }
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (!exitFromBank);
    }
}
