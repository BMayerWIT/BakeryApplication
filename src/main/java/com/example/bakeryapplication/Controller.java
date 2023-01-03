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
    public LinkedList<Ingredients> ingredient = new LinkedList<>();

    private int printGoodIndex = 0;
    private String bakedGoodsList = "";


    //BakedGood

    @FXML
    private TextField goodName;
    public TextField origin;
    @FXML
    private ComboBox<String> originCountry;
    @FXML
    private TextField bakedGoodDescription;
    @FXML
    private ComboBox<BakedGoods> bakedGoodChoice;
    public ListView<String> Bgood; //lists and displays added goods

    //Ingredients

    public TextField nameIng;
    public TextField textualdescription;
    public TextField cals;
    public ListView<String> ingredients;


    //Recipe

    public TextField RecipeName;
    public ChoiceBox<BakedGoods> pickbgood;
    public ChoiceBox<Ingredient> chooseIngredient;
    public TextField grmls; //grams & mls

    public ListView<String> recipe;


    @FXML
    public TextField search;



    @FXML
    void addBakedGood(MouseEvent event) {
        String bakedGoodName = goodName.getText();
        String countryOfOrigin = originCountry.getSelectionModel().getSelectedItem();
        String description = bakedGoodDescription.getText();
        BakedGoods bg = new BakedGoods(bakedGoodName, countryOfOrigin, description);
        if (bakedGoodsList.contains(goodName.getText())) {
            System.out.println(("Baked Good with this name already exists."));
        } else {
            goods.add(bg);
            bakedGoodsList += goodName.getText();
            printGoodIndex++;
            bakedGoodChoice.getItems().add(bg);
            System.out.println(goods.get(printGoodIndex));
            System.out.println("No of cases : " + goods.numberOfNodes());
            //populateGoodList();
        }
    }

    @FXML
    public void removeBakedGood(MouseEvent event) {
        // BakedGoods selectedGood = bakedGoodListView.getSelectionModel().getSelectedItem();
        //  goods.deleteNode(selectedGood.getIngredientIndex());

    }





    public void addIngr (ActionEvent actionEvent) {
        Ingredient ingredient = new Ingredient(nameIng.getText(), textualdescription.getText(), Float.parseFloat(cals.getText()));
        ingredient.addNode(ingredient);
        popListBoxingredient();
        chooseIngredient.getItems().add(ingredient);
    }

    public void popListBoxingredient(){
        ingredients.getItems().clear();
        Node temp = ingredient.getHead();
        while (temp !=null) {
            ingredient.getItems().add(temp.getData().toString());
            temp = temp.getNext();
        }
    }



    void Search() {
        String searchedPhrase = searchField.getText();
        for (int i = 0; i < goods.numberOfNodes() + 1; i++) {
            BakedGoods selectedGood = (BakedGoods) goods.get(i);
            //  if (goods.search(searchedPhrase))
            //  searchList.getItems().add(selectedGood);
            //
        }
    }
}




    public LinkedList<Ingredients> ingredientsList = new LinkedList<>();

    private int printGoodIndex = 0;
    private String bakedGoodsList = "";

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
    private ChoiceBox<?> chooseIngredient;

    @FXML
    private TextField goodDescription;

    @FXML
    private TextField grams;

    @FXML
    private TextField ingredientDescription;

    @FXML
    private ListView<?> ingredientList;

    @FXML
    private TextField ingredientName;

    @FXML
    private TextField mL;

    @FXML
    private TextField originCountry;

    @FXML
    private TextField recipeDescription;

    @FXML
    private ListView<?> recipeListView;

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
            System.out.println(("Baked Good with this name already exists."));
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
    void addDisplayTray(MouseEvent event) {

    }

    @FXML
    void addRecipe(ActionEvent event) {

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