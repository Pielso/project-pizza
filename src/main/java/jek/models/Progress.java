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

    public Progress(int userId) {
        this.userId = userId;
        this.cash = BigDecimal.valueOf(10000);
        this.loan = BigDecimal.valueOf(50000);
        this.interestRate = 10;
        this.customersPerDay = 7;
        this.restaurantSize = 1;
        this.daysPlayed = 0;
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
