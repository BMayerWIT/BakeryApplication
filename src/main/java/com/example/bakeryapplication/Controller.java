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
    private int printIngredientIndex = 0;
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
    private ListView<Ingredients> ingredientListView;

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
    private ListView<Ingredients> recipeIngredientListView;
    @FXML
    private ListView<Ingredients> ingredientListView2;
    @FXML
    private TextField recipeName;
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


        String igName = ingredientName.getText();
        String description = ingredientDescription.getText();
        int cals = Integer.parseInt(calories.getText());
        Ingredients ingredient = new Ingredients(igName, description, cals);
        if (ingredientNameList.contains(igName)) {
            System.out.println(("Ingredient with this name already exists."));
        } else {

            ingredientsList.add(ingredient);
            ingredientListView2.getItems().add(ingredient);
            ingredientNameList += igName;
            populateIngredientList();

            System.out.println(ingredientsList.get(printIngredientIndex));
            printIngredientIndex++;
            System.out.println("No of cases : " + ingredientsList.numberOfNodes());

        }
    }

    public void populateIngredientList() {
        ingredientListView.getItems().clear();
        for (int i = 0; i < ingredientsList.numberOfNodes(); i++) {
            Ingredients ig = (Ingredients) ingredientsList.get(i+1);
            ingredientListView.getItems().add(ig);
            ingredientNameList += ig.getIngredientName();
        }
    }

    public void deleteIngredient(ActionEvent event) {
        ingredientsList.deleteNode(ingredientListView.getSelectionModel().getSelectedIndex());
        populateIngredientList();
        ingredientNameList = "";
    }


    @FXML
    void addRecipe(ActionEvent event) {
        String rName = recipeName.getText();

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
        BakedGoods selectedGood = bakedGoodChoice.getSelectionModel().getSelectedItem();
        selectedGood.recipes.deleteNode(recipeListView.getSelectionModel().getSelectedIndex());
        populateRecipeList();
        recipeNameList = "";
    }

    @FXML
    void populateRecipeIngredientListView() {
        recipeIngredientListView.getItems().clear();
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem();
        for (int i = 0; i < selectedRecipe.ingredients.numberOfNodes(); i++) {
            Ingredients ingredient = (Ingredients) selectedRecipe.ingredients.get(i+1);
            recipeIngredientListView.getItems().add(ingredient);
        }
    }

    @FXML
    void addIngredientToRecipe() {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem();
        Ingredients ingredient = ingredientListView2.getSelectionModel().getSelectedItem();
        float quantity = Float.parseFloat(grams.getText());
        ingredient.setQuantity(quantity);
        selectedRecipe.ingredients.add(ingredient);
        populateRecipeIngredientListView();
    }


    @FXML
    void editBakedGood(ActionEvent event) {

    }

    @FXML
    void editIngredient(ActionEvent event) {

    }

    @FXML
    void editRecipe(ActionEvent event) {

    }


    @FXML
    void updateBakedGood(ActionEvent event) {

    }

    @FXML
    void updateIngredient(ActionEvent event) {

    }

    @FXML
    void updateRecipe(ActionEvent event) {

    }

    @FXML
    void viewAllBakedGoods(ActionEvent event) {

    }


}