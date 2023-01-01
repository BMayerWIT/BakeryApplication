package Models;

import Resources.LinkedList;

public class BakedGoods {

    public LinkedList<Ingredients> ingredients = new LinkedList<>();
    public LinkedList<Recipe> recipes = new LinkedList<>();

    private String bakedGoodName;
    private String countryOfOrigin;
    private String description;

    private String ingredientNameList = "";

    private int ingredientIndex = 0;

    public BakedGoods(String bakedGoodName, String countryOfOrigin, String description) {
        this.bakedGoodName = bakedGoodName;
        this.countryOfOrigin = countryOfOrigin;
        this.description = description;
    }

    public String getBakedGoodName() {
        return bakedGoodName;
    }

    public void setBakedGoodName(String bakedGoodName) {
        this.bakedGoodName = bakedGoodName;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredientNameList() {
        return ingredientNameList;
    }

    public void setIngredientNameList(String ingredientToAdd) {
        ingredientNameList += ingredientToAdd + " ";
    }

    public int getIngredientIndex() {
        return ingredientIndex;
    }

    public void setIngredientIndex(int ingredientIndexIncrease) {
        ingredientIndex += ingredientIndexIncrease;
    }

    @Override
    public String toString() {
        return "BakedGoods{" +
                "bakedGoodName='" + bakedGoodName + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
