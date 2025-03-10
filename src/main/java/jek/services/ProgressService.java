package jek.services;

import jek.controllers.LoginController;
import jek.models.Progress;
import jek.repositories.ProgressRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ProgressService {
    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void createProgress(Progress newProgress){
        progressRepository.createProgress(newProgress);
    }

    public Progress getProgressById(int userId){
        return progressRepository.getProgressById(userId);
    }

    public Progress getActiveProgress(){
        return LoginController.activeProgress;
    }

    public void updateProgressCashById(int userId, BigDecimal cash) {
        progressRepository.updateProgressCashById(userId, cash);
    }

    public void updateProgressLoanById(int userId, BigDecimal loan) {
        progressRepository.updateProgressLoanById(userId, loan);
    }

    public void updateProgressInterestRateById(int userId, int interestRate) {
        progressRepository.updateProgressInterestRateById(userId, interestRate);
    }

    public void updateProgressCustomersPerDayById(int userId, int customersPerDay) {
        progressRepository.updateProgressCustomersPerDayById(userId, customersPerDay);
    }

    public void updateProgressRestaurantSizeById(int userId, int restaurantSize) {
        progressRepository.updateProgressRestaurantSizeById(userId, restaurantSize);
    }

    public void updateProgressDaysPlayedById(int userId, int daysPlayed) {
        progressRepository.updateProgressDaysPlayedById(userId, daysPlayed);
    }


}
