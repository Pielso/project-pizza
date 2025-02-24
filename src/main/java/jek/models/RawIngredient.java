package jek.models;

public class RawIngredient {

    private int rawIngredientId;
    private String rawIngredientName;
    private int amountInStock;

    public RawIngredient(String rawIngredientName, int amount_in_stock){
        this.rawIngredientName = rawIngredientName;
        this.amountInStock = amount_in_stock;
    }

    public RawIngredient(){

    }

    public int getRawIngredientId() {
        return rawIngredientId;
    }

    public void setRawIngredientId(int rawIngredientId) {
        this.rawIngredientId = rawIngredientId;
    }

    public String getRawIngredientName() {
        return rawIngredientName;
    }

    public void setRawIngredientName(String rawIngredientName) {
        this.rawIngredientName = rawIngredientName;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

}
