package jek.controllers;

import jek.services.system.TextService;

public class OfficeController {
    private final TextService textService;

    public OfficeController(TextService textService) {
        this.textService = textService;
    }

    /**
     * Here, user should be able to view stats and breakdowns, like amount earned/lost last 10 days,
     * and purchase upgrades like:
     * - A bigger restaurant, which changes restaurantSize in Progress, and impacts number of customersPerDay
     * - A bigger kitchen aid to be able to make more than 10 dough at a time.
     * - A bigger cooking vat to be able to make more than 10 tomato sauce at a time.
     */
    public void goToOffice(){
        textService.officeStats();

    }
}
