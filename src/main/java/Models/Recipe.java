package Models;

import Resources.LinkedList;

public class Recipe {

    public LinkedList<Ingredients> ingredients = new LinkedList<>();

    String recipeName = "";

    //constructor
    public Recipe(String recipeName) {
        this.recipeName = recipeName;

    }

    //setters and getters
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }



    @Override
    public String toString() {
        return recipeName;
    }

}
