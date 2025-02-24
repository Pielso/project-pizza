package jek.controllers;

import jek.services.ProgressService;
import jek.services.system.TextService;

public class OfficeController {
    private TextService textService;
    private ProgressService progressService;

    public OfficeController(TextService textService, ProgressService progressService) {
        this.textService = textService;
        this.progressService = progressService;
    }

    public void goToOffice(){

        textService.officeStats();

    }
}
