package jek.models;

import java.math.BigDecimal;

public class Progress {

    private int userId;
    private BigDecimal cash;
    private BigDecimal loan;
    private int interestRate;
    private int customersPerDay;
    private int restaurantSize;
    private int daysPlayed;

    public Progress(int userId, BigDecimal cash, BigDecimal loan, int interestRate, int customersPerDay, int restaurantSize, int daysPlayed) {
        this.userId = userId;
        this.cash = cash;
        this.loan = loan;
        this.interestRate = interestRate;
        this.customersPerDay = customersPerDay;
        this.restaurantSize = restaurantSize;
        this.daysPlayed = daysPlayed;
    }

    public Progress(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getLoan() {
        return loan;
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public int getCustomersPerDay() {
        return customersPerDay;
    }

    public void setCustomersPerDay(int customersPerDay) {
        this.customersPerDay = customersPerDay;
    }

    public int getRestaurantSize() {
        return restaurantSize;
    }

    public void setRestaurantSize(int restaurantSize) {
        this.restaurantSize = restaurantSize;
    }

    public int getDaysPlayed() {
        return daysPlayed;
    }

    public void setDaysPlayed(int daysPlayed) {
        this.daysPlayed = daysPlayed;
    }
}
