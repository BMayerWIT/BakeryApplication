package com.example.bakeryapplication;

import Models.BakedGoods;
import Models.Ingredients;
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




    private int printGoodIndex = 0;
    private String bakedGoodsList = "";

    @FXML
    private TextField goodName;
    @FXML
    private ComboBox<String> originCountry;
    @FXML
    private TextField bakedGoodDescription;
    @FXML
    private ComboBox<BakedGoods> bakedGoodChoice;
    @FXML
    private TextField searchField;



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
        BakedGoods selectedGood = bakedGoodListView.getSelectionModel().getSelectedItem();
        goods.deleteNode(selectedGood.getIngredientIndex());

    }
    
    void Search() {
        String searchedPhrase = searchField.getText();
        for (int i = 0; i < goods.numberOfNodes() + 1; i++) {
            BakedGoods selectedGood = (BakedGoods) goods.get(i);
            if (goods.search(searchedPhrase))
            searchList.getItems().add(selectedGood);
            //

        }
    }
}