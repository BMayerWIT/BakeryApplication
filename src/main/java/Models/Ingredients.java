package Models;

public class Ingredients {

    private String bakedGoodName;

    private String description;

    private int calories = 0;

    public Ingredients(String bakedGoodName, String description, int calories) {
        this.bakedGoodName = bakedGoodName;
        this.description = description;
        this.calories = calories;
    }

    public String getBakedGoodName() {
        return bakedGoodName;
    }

    public void setBakedGoodName(String bakedGoodName) {
        this.bakedGoodName = bakedGoodName;
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

    @Override
    public String
    toString() {
        return "Ingredients{" +
                "bakedGoodName='" + bakedGoodName + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
