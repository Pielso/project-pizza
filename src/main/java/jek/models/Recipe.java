package jek.models;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private int userId;

    public Recipe(String recipeName, int userId) {
        this.recipeName = recipeName;
        this.userId = userId;
    }

    public Recipe() {

    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
