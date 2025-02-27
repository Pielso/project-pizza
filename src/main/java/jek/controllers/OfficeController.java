package jek.controllers;

import jek.services.ProgressService;
import jek.services.system.TextService;

public class OfficeController {
    private final TextService textService;
    private final ProgressService progressService;

    public OfficeController(TextService textService, ProgressService progressService) {
        this.textService = textService;
        this.progressService = progressService;
    }

    public void goToOffice(){
        textService.officeStats();

    }
}
