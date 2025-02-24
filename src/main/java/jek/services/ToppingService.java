package jek.services;

import jek.models.Topping;
import jek.repositories.ToppingRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToppingService {
    ToppingRepository toppingRepository;

    public ToppingService(ToppingRepository toppingRepository) {

    }

    public static List<Topping> createInventoryOfToppings(){
        Topping ham = new Topping("Ham", 0);
        Topping mushroom = new Topping("Mushroom", 0);
        Topping kebab = new Topping("Kebab", 0);
        Topping tuna = new Topping("Tuna", 0);
        Topping beef = new Topping("Beef", 0);
        Topping chicken = new Topping("Chicken", 0);
        Topping pepperoni = new Topping("Pepperoni", 0);
        Topping olives = new Topping("Olives", 0);
        Topping paprika = new Topping("Paprika", 0);
        Topping onion = new Topping("Onion", 0);
        Topping pineapple = new Topping("Pineapple", 0);
        Topping shrimps = new Topping("Shrimps", 0);
        Topping bacon = new Topping("Bacon", 0);
        Topping jalapenos = new Topping("Jalapeños", 0);
        Topping sauce = new Topping("Sauce", 0);
        return new ArrayList<>(Arrays.asList(ham, mushroom, kebab, tuna, beef, chicken, pepperoni, olives, paprika, onion, pineapple, shrimps, bacon, jalapenos, sauce));
    }
}
