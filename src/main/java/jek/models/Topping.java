package jek.models;

public class Topping {
    private int toppingId;
    private String toppingName;
    private int amountInStock;

    public Topping(String toppingName, int amountInStock) {
        this.toppingName = toppingName;
        this.amountInStock = amountInStock;
    }

    public Topping() {

    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

}
