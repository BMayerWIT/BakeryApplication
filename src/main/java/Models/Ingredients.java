package Models;

public class Ingredients {

    private String ingredientName;

    private String description;

    private int calories = 0;

    private float quantity = 0;

    public Ingredients(String ingredientName, String description, int calories) {
        this.ingredientName = ingredientName;
        this.description = description;
        this.calories = calories;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String
    toString() {
        return  ingredientName + '\'' +
                ", '" + calories + " Calories" + '\'' +
                ", " + description + " " + quantity;
    }
}
