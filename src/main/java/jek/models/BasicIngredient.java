package jek.models;

public class BasicIngredient {
    private int basicIngredientId;
    private String basicIngredientName;
    private int amountInStock;

    public BasicIngredient(String basicIngredientName, int amountInStock) {
        this.basicIngredientName = basicIngredientName;
        this.amountInStock = amountInStock;
    }

    public int getBasicIngredientId() {
        return basicIngredientId;
    }

    public String getBasicIngredientName() {
        return basicIngredientName;
    }

    public void setBasicIngredientName(String basicIngredientName) {
        this.basicIngredientName = basicIngredientName;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

}
