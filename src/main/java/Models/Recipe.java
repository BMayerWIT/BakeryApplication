package Models;

import Resources.LinkedList;

public class Recipe {

    public LinkedList<Ingredients> ingredients = new LinkedList<>();

    String recipeName = "";
    int quantity = 0;

    public Recipe(String recipeName, int quantity) {
        this.recipeName = recipeName;
        this.quantity = quantity;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeName='" + recipeName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
