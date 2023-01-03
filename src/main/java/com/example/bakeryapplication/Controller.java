package com.example.bakeryapplication;

import Resources.Node;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import Models.BakedGoods;
import Models.Ingredients;
import Models.Recipe;
import Resources.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private ListView<Object> searchList;
    @FXML
    private ListView<Object> searchedIngredientList;
    @FXML
    private ChoiceBox<String> searchFilter;



    @FXML
    void addBakedGood(ActionEvent event) {
        String goodName = bakedGoodName.getText();
        String countryOfOrigin = originCountry.getText();
        String description = goodDescription.getText();
        BakedGoods bg = new BakedGoods(goodName, countryOfOrigin, description);
        if (bakedGoodsList.contains(goodName)) {
            System.out.println(("Baked Good  with this name already exists."));
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
        searchedIngredientList.getItems().clear();
        String searchedPhrase = searchField.getText();

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Baked Good")) {
            for (int i = 0; i < goods.numberOfNodes(); i++) {
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                if (selectedGood.getBakedGoodName().toLowerCase().contains(searchedPhrase.toLowerCase())
                        || selectedGood.getDescription().toLowerCase().contains(searchedPhrase.toLowerCase())
                        || selectedGood.getCountryOfOrigin().toLowerCase().contains(searchedPhrase.toLowerCase())) {
                    searchList.getItems().add(selectedGood);
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Recipe")) {
            for (int i = 0; i < goods.numberOfNodes(); i++) {
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int k = 0; k < selectedGood.recipes.numberOfNodes(); k++) {
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    if (selectedRecipe.getRecipeName().toLowerCase().contains(searchedPhrase.toLowerCase())) {
                        searchList.getItems().add(selectedRecipe);
                    }
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Ingredients")) {
            for (int i = 0; i < goods.numberOfNodes(); i++) {
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int k = 0; k < selectedGood.recipes.numberOfNodes(); k++) {
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    for (int j = 0; j < selectedRecipe.ingredients.numberOfNodes(); j++) {
                        Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i);
                        if (selectedIngredient.getIngredientName().toLowerCase().contains(searchedPhrase.toLowerCase())
                                || selectedIngredient.getDescription().toLowerCase().contains(searchedPhrase.toLowerCase())) {
                            searchList.getItems().add(selectedIngredient);
                        }
                    }
                }
            }
        }

    }

    @FXML
    void populateIngredientSearchList() {
        searchedIngredientList.getItems().clear();
        Object selectedObject = searchList.getSelectionModel().getSelectedItem();

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Baked Good")) {
            for (int i = 0; i < goods.numberOfNodes(); i++) {
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                if (selectedObject.equals(selectedGood)) {
                    for (int j = 0; j < selectedGood.recipes.numberOfNodes(); j++) {
                        Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i + 1);
                        for (int k = 0; k < selectedRecipe.ingredients.numberOfNodes(); k++) {
                            Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i + 1);
                            searchedIngredientList.getItems().add(selectedIngredient);
                        }
                    }
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Ingredients")) {
            for (int i = 0; i < goods.numberOfNodes(); i++) {
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int j = 0; j < selectedGood.recipes.numberOfNodes(); j++) {
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    for (int k = 0; k < selectedRecipe.ingredients.numberOfNodes(); k++) {
                        Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i + 1);
                        if (selectedObject.equals(selectedIngredient)) {
                            searchedIngredientList.getItems().add(selectedGood);
                        }
                    }
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Recipe")) {
            for (int i = 0; i < goods.numberOfNodes(); i++) {
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int j = 0; j < selectedGood.recipes.numberOfNodes(); j++) {
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    if (selectedObject.equals(selectedRecipe)) {
                        for (int k = 0; k < selectedRecipe.ingredients.numberOfNodes(); k++) {
                            Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i + 1);
                            searchedIngredientList.getItems().add(selectedIngredient);

                        }
                    }
                }
            }
        }

    }


    public void Load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{BakedGoods.class, Recipe.class, Ingredients.class, LinkedList.class, Node.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("baking.xml"));
        goods = (LinkedList<BakedGoods>) in.readObject();
        ingredientsList = (LinkedList<Ingredients>) in.readObject();
        in.close();
        populateBakedGoodList();
        populateIngredientList();
        populateRecipeList();

    }


    @FXML
    private void loadApp(ActionEvent action) {
        try {
            Load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    @FXML
    //deletes all data and clears all listviews
    public void Reset(ActionEvent event) {
        goods.remove();
        ingredientsList.remove();
        bakedGoodListView.getItems().clear();
        ingredientListView.getItems().clear();
        recipeIngredientListView.getItems().clear();
        ingredientListView2.getItems().clear();
        searchedIngredientList.getItems().clear();
        recipeListView.getItems().clear();
        bakedGoodsList = "";
        ingredientNameList = "";
        recipeNameList = "";
    }

    @FXML
    public void Save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("baking.xml"));
        LinkedList<BakedGoods> list = goods;
        LinkedList<Ingredients> list2 = ingredientsList;
        out.writeObject(list);
        out.writeObject(list2);
        out.close();
    }

    public void saveApp(ActionEvent action) {
        try {
            Save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
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
            System.out.println(("Recipe with this name already exists."));
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
    void updateBakedGood(ActionEvent event) {
        BakedGoods selectedGood = bakedGoodListView.getSelectionModel().getSelectedItem();
        String goodName = bakedGoodName.getText();
        String countryOfOrigin = originCountry.getText();
        String description = goodDescription.getText();

        if (bakedGoodsList.contains(goodName)) {
            System.out.println(("Baked Good with this name already exists."));
        } else {
            selectedGood.setBakedGoodName(goodName);
            selectedGood.setCountryOfOrigin(countryOfOrigin);
            selectedGood.setDescription(description);
            populateBakedGoodList();
        }

    }

    @FXML
    void updateIngredient(ActionEvent event) {
        Ingredients selectedIngredient = ingredientListView.getSelectionModel().getSelectedItem();
        String igName = ingredientName.getText();
        String description = ingredientDescription.getText();
        int cals = Integer.parseInt(calories.getText());

        if (ingredientNameList.contains(igName)) {
            System.out.println(("Ingredient with this name already exists."));
        } else {
            selectedIngredient.setIngredientName(igName);
            selectedIngredient.setDescription(description);
            selectedIngredient.setCalories(cals);
            populateIngredientList();
        }
    }

    @FXML
    void updateRecipe(ActionEvent event) {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem();
        String rName = recipeName.getText();
        if (recipeNameList.contains(rName)) {
            System.out.println(("Recipe with this name already exists."));
        } else {
            if (rName != null) {
                selectedRecipe.setRecipeName(rName);
                populateRecipeList();
            }
            else {
                System.out.println("Couldn't Update Name");
            }
        }
    }

    @FXML
    void updateIngredientQuantity() {
        Ingredients selectedIngredient = recipeIngredientListView.getSelectionModel().getSelectedItem();
        float quantity = Float.parseFloat(grams.getText());
        if (quantity != 0) {
            selectedIngredient.setQuantity(quantity);
            populateRecipeIngredientListView();
        }
        else {
            System.out.println("Couldn't Update Quantity");
        }
    }

    @FXML
    void removeIngredient(ActionEvent event) {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem();
        int ingredientIndex = recipeIngredientListView.getSelectionModel().getSelectedIndex();
        selectedRecipe.ingredients.deleteNode(ingredientIndex);
        populateRecipeIngredientListView();
    }


    public void initialize() {
        searchFilter.getItems().addAll("Baked Good", "Ingredients", "Recipe");
    }

}