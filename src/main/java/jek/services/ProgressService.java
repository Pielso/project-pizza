package jek.services;

import jek.controllers.LoginController;
import jek.models.Progress;
import jek.repositories.ProgressRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ProgressService {
    ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void createProgress(int userId){
        progressRepository.createProgress(userId, new BigDecimal(10000), new BigDecimal(50000), 10, 7, 1, 0);
    }

    public void updateProgress(Progress progress){
        progressRepository.updateProgressById(progress.getUserId(), progress.getCash(), progress.getLoan(), progress.getInterestRate(), progress.getCustomersPerDay(), progress.getRestaurantSize(), progress.getDaysPlayed());
    }

    public Progress getProgressById(int userId){
        return progressRepository.getProgressById(userId);
    }

    public Progress getProgress(){
        return LoginController.activeProgress;
    }

    public void updateProgressCashById(int userId, BigDecimal cash) throws SQLException {
        progressRepository.updateProgressCashById(userId, cash);
    }

    public void updateProgressLoanById(int userId, BigDecimal loan) throws SQLException {
        progressRepository.updateProgressLoanById(userId, loan);
    }

    public void updateProgressInterestRateById(int userId, int interestRate) throws SQLException {
        progressRepository.updateProgressInterestRateById(userId, interestRate);
    }

    public void updateProgressCustomersPerDayById(int userId, int customersPerDay) throws SQLException {
        progressRepository.updateProgressCustomersPerDayById(userId, customersPerDay);
    }

    public void updateProgressRestaurantSizeById(int userId, int restaurantSize) throws SQLException {
        progressRepository.updateProgressRestaurantSizeById(userId, restaurantSize);
    }

    public void updateProgressDaysPlayedById(int userId, int daysPlayed) throws SQLException {
        progressRepository.updateProgressDaysPlayedById(userId, daysPlayed);
    }


}
