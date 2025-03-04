package jek.services.system;

import jek.models.Customer;
import jek.services.CustomerService;
import jek.services.ProgressService;

import java.math.BigDecimal;
import java.sql.SQLException;

public class NextDayService {
    private final ProgressService progressService;
    private final CustomerService customerService;

    public NextDayService(ProgressService progressService, CustomerService customerService) {
        this.progressService = progressService;
        this.customerService = customerService;
    }

    /**
     * Should increase loan by interestRate * Loan / 365, increase daysPlayed and delete all customers, and generate (7 * restaurantSize) new customers
     * @throws SQLException
     */
    public void goToNextDay() throws SQLException {

        progressService.updateProgressLoanById(
                progressService.getActiveProgress().getUserId(),
                progressService.getActiveProgress().getLoan().add(
                progressService.getActiveProgress().getLoan().multiply(
                BigDecimal.valueOf(progressService.getActiveProgress().getInterestRate()*((0.1)/(365))))));

        progressService.updateProgressDaysPlayedById(progressService.getActiveProgress().getUserId(),progressService.getActiveProgress().getDaysPlayed()+1);
        customerService.deleteAllCustomers();

        for (int i = 0; i < (7*progressService.getActiveProgress().getRestaurantSize()); i++) {
            customerService.createCustomer(new Customer());
        }

    }

}
