package com.example.bakeryapplication;

import Models.BakedGoods;
import Models.Ingredients;
import Resources.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    public LinkedList<BakedGoods> goods = new LinkedList<>();

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