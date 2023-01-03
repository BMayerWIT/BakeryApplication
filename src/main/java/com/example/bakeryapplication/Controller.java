package com.example.bakeryapplication;

import Models.BakedGoods;
import Models.Ingredients;
import Models.Recipe;
import Resources.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller {

    public LinkedList<BakedGoods> goods = new LinkedList<>();
    public LinkedList<Ingredients> ingredientsList = new LinkedList<>();
    private int printGoodIndex = 0;
    private int printRecipeIndex = 0;
    private String bakedGoodsList = "";
    private String ingredientNameList = "";
    private String recipeNameList = "";




    @FXML
    private ChoiceBox<BakedGoods> bakedGoodChoice;

    @FXML
    private ListView<BakedGoods> bakedGoodListView;

    @FXML
    private TextField bakedGoodName;

    @FXML
    private ListView<?> bakedGoodsStock;

    @FXML
    private TextField calories;

    @FXML
    private ChoiceBox<Ingredients> chooseIngredient;

    @FXML
    private TextField goodDescription;

    @FXML
    private TextField grams;

    @FXML
    private TextField ingredientDescription;

    @FXML
    private ListView<Ingredients> ingredientsListView;

    @FXML
    private TextField ingredientName;

    @FXML
    private TextField mL;

    @FXML
    private TextField originCountry;

    @FXML
    private TextField recipeDescription;

    @FXML
    private ListView<Recipe> recipeListView;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<?> value;
    @FXML
    private ListView<BakedGoods> searchList;
    @FXML
    private ListView<Ingredients> searchIngredientList;


    @FXML
    void addBakedGood(ActionEvent event) {
        String goodName = bakedGoodName.getText();
        String countryOfOrigin = originCountry.getText();
        String description = goodDescription.getText();
        BakedGoods bg = new BakedGoods(goodName, countryOfOrigin, description);
        if (bakedGoodsList.contains(goodName)) {
            System.out.println(("Recipe with this name already exists."));
        } else {
            goods.add(bg);
            bakedGoodsList += goodName;
            bakedGoodChoice.getItems().add(bg);
            populateBakedGoodList();
            System.out.println(goods.get(printGoodIndex));
            printGoodIndex++;
            System.out.println("No of cases : " + goods.numberOfNodes());

        }
    }

    @FXML
    void removeBakedGood(ActionEvent event) {
        goods.deleteNode(bakedGoodListView.getSelectionModel().getSelectedIndex());
        populateBakedGoodList();
        bakedGoodsList = "";
    }

    void populateBakedGoodList() {
        bakedGoodListView.getItems().clear();
        for (int i = 0; i < goods.numberOfNodes(); i++) {
            BakedGoods bg = (BakedGoods) goods.get(i+1);
            bakedGoodListView.getItems().add(bg);
            bakedGoodsList += bg.getBakedGoodName();
        }
    }

    @FXML
    void Search(ActionEvent event) {
        searchList.getItems().clear();
        String searchedPhrase = searchField.getText();
        for (int i = 0; i < goods.numberOfNodes(); i++) {
            BakedGoods selectedGood = (BakedGoods) goods.get(i+1);
            if (selectedGood.getBakedGoodName().contains(searchedPhrase)) {
                searchList.getItems().add(selectedGood);
            }
        }
    }

    @FXML
    void populateIngredientSearchList() {
        searchIngredientList.getItems().clear();
        BakedGoods selectedGood = searchList.getSelectionModel().getSelectedItem();
        for (int i = 0; i < selectedGood.recipes.numberOfNodes(); i++) {
            Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
            for (int k = 0; i < selectedRecipe.ingredients.numberOfNodes(); k++) {
                Ingredients recipeIngredients = (Ingredients) selectedRecipe.ingredients.get(i);
                searchIngredientList.getItems().add(recipeIngredients);
            }
        }
    }

    @FXML
    void Load(ActionEvent event) {

    }

    @FXML
    void Reset(ActionEvent event) {

    }

    @FXML
    void Save(ActionEvent event) {

    }

    @FXML
    public void addIngredient (ActionEvent actionEvent) {
        Ingredients ingredient = new Ingredients(ingredientName.getText(), ingredientDescription.getText(), Integer.parseInt(calories.getText()));
        ingredientsList.add(ingredient);
        populateIngredientList();
        chooseIngredient.getItems().add(ingredient);
    }

    public void populateIngredientList() {
        ingredientsListView.getItems().clear();
        for (int i = 0; i < ingredientsList.numberOfNodes(); i++) {
            Ingredients ig = (Ingredients) ingredientsList.get(i+1);
            ingredientsListView.getItems().add(ig);
            ingredientNameList += ig.getIngredientName();
        }
    }

    public void deleteIngredient(ActionEvent event) {
        ingredientsList.deleteNode(ingredientsListView.getSelectionModel().getSelectedIndex());
        populateIngredientList();
        ingredientNameList = "";
    }


    @FXML
    void addRecipe(ActionEvent event) {
        String rName = bakedGoodName.getText();

         Recipe recipe = new Recipe(rName);
        if (recipeNameList.contains(rName)) {
            System.out.println(("Baked Good with this name already exists."));
        } else {
            BakedGoods selectedGood = bakedGoodChoice.getSelectionModel().getSelectedItem();
            selectedGood.recipes.add(recipe);
            recipeNameList += rName;
            populateRecipeList();

            System.out.println(selectedGood.recipes.get(printRecipeIndex));
            printRecipeIndex++;
            System.out.println("No of cases : " + selectedGood.recipes.numberOfNodes());

        }
    }

    void populateRecipeList() {
        recipeListView.getItems().clear();
        BakedGoods selectedGood = bakedGoodChoice.getSelectionModel().getSelectedItem();
        for (int i = 0; i < selectedGood.recipes.numberOfNodes(); i++) {
            Recipe recipe = (Recipe) selectedGood.recipes.get(i+1);
            recipeListView.getItems().add(recipe);
            recipeNameList += recipe.getRecipeName();
        }
    }

    @FXML
    void deleteRecipe(ActionEvent event) {

    }

    @FXML
    void editBakedGood(ActionEvent event) {

    }

    @FXML
    void editRecipe(ActionEvent event) {

    }


    @FXML
    void updateBakedGood(ActionEvent event) {

    }

    @FXML
    void updateRecipe(ActionEvent event) {

    }

    @FXML
    void viewAllBakedGoods(ActionEvent event) {

    }


}