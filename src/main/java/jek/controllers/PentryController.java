package jek.controllers;

import jek.services.BasicIngredientService;
import jek.services.ProgressService;
import jek.services.RawIngredientService;
import jek.services.ToppingService;
import jek.services.system.TextService;

public class PentryController {
    private TextService textService;
    private ProgressService progressService;
    private RawIngredientService rawIngredientService;
    private BasicIngredientService basicIngredientService;
    private ToppingService toppingService;

    public PentryController(TextService textService, ProgressService progressService, RawIngredientService rawIngredientService, BasicIngredientService basicIngredientService, ToppingService toppingService) {
        this.textService = textService;
        this.progressService = progressService;
        this.rawIngredientService = rawIngredientService;
        this.basicIngredientService = basicIngredientService;
        this.toppingService = toppingService;
    }
}
