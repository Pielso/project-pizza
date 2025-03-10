package jek.services;

import jek.models.Topping;
import jek.repositories.ToppingRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToppingService {
    private final ToppingRepository toppingRepository;

    public ToppingService(ToppingRepository toppingRepository) {
        this.toppingRepository = toppingRepository;
    }

    /**
     * <h3>Part of creating the empty inventory of Toppings</h3>
     * <h5>Used when player registers or logs in</h5>
     * <p>There are counterparts for loading the amountInStock of RawIngredients and BasicIngredients.</p>
     */
    public void createAllToppings() {
        if (toppingRepository.toppingsIsEmpty()){
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
            Topping jalapenos = new Topping("Jalapenos", 0);
            Topping sauce = new Topping("Sauce", 0);
            List<Topping> toppings = new ArrayList<>(Arrays.asList(ham, mushroom, kebab, tuna, beef, chicken, pepperoni, olives, paprika, onion, pineapple, shrimps, bacon, jalapenos, sauce));

            for (Topping topping: toppings){
                toppingRepository.createTopping(topping);
            }
        }
    }

    public Topping getToppingById(int toppingId){
        return toppingRepository.getToppingById(toppingId);
    }

    public int getToppingAmountInStockById(int toppingId){
        return toppingRepository.getToppingAmountInStockById(toppingId);
    }

    public boolean checkIfToppingAmountInStockExists(List<Integer> toppingsIdsToCheck){
        int counter = 0;
        for (Integer toppingId: toppingsIdsToCheck){
            if (getToppingAmountInStockById(toppingId) > 0){
                counter++;
            }
        }
        return counter == toppingsIdsToCheck.size();
    }

    public List<Topping> getAllToppings(){
        return toppingRepository.getAllToppings();
    }

    public void addToppingAmountInStock(int toppingId, int amountToAdd){
        int before = toppingRepository.getToppingAmountInStockById(toppingId);
        toppingRepository.updateToppingAmountInStockById(toppingId, before + amountToAdd);
    }

    public void servedOnePizza(List <Integer> toppingIdsToSubtract) {
        for (Integer toppingId: toppingIdsToSubtract){
            int before = toppingRepository.getToppingAmountInStockById(toppingId);
            toppingRepository.updateToppingAmountInStockById(toppingId, before - 1);
        }
    }

    /**
     * <h3>Part of loading amountInStock of Toppings from DynamoDB</h3>
     * <h5>Used when already existing player logs in (see LoginController)</h5>
     * <p>There are counterparts for loading the amountInStock of RawIngredients and BasicIngredients.</p>
     * @param amountInStock List of integer values retrieved from DynamoDB.
     */
    public void setAllAmountInStock(List <Integer> amountInStock) {
        int id = 1;
        for (Integer num : amountInStock) {
            toppingRepository.updateToppingAmountInStockById(id, num);
            id++;
        }
    }

    /**
     * <h3>Part of purging all Toppings between application shutdowns.</h3>
     * <h5>Resets the auto_increment in the sql-table</h5>
     * <p>There are counterparts for purging the amountInStock of RawIngredients and BasicIngredients.</p>
     */
    public void deleteAllToppings(){
        toppingRepository.deleteAllToppings();
    }
}
