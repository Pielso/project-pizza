package jek.models;

public class RecipeTopping {
    private int recipeToppingId;
    private int recipeId;
    private int toppingId;

    public RecipeTopping(int recipeId, int toppingId) {
        this.recipeId = recipeId;
        this.toppingId = toppingId;
    }

    public RecipeTopping(){

    }

    public int getRecipeToppingId() {
        return recipeToppingId;
    }

    public void setRecipeToppingId(int recipeToppingId) {
        this.recipeToppingId = recipeToppingId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }
}

