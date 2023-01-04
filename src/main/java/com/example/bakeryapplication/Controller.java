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
    private TextField calories;

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
    private TextField originCountry;

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
    private ListView<Object> searchList;

    @FXML
    private ListView<Object> searchedIngredientList;

    @FXML
    private ChoiceBox<String> searchFilter;


    //Add baked good object to baked goods linked list
    @FXML
    void addBakedGood(ActionEvent event) {
        String goodName = bakedGoodName.getText(); //creates variables with inputted data
        String countryOfOrigin = originCountry.getText(); //creates variables with inputted data
        String description = goodDescription.getText(); //creates variables with inputted data

        BakedGoods bg = new BakedGoods(goodName, countryOfOrigin, description);

        if (bakedGoodsList.contains(goodName)) { //checks if baked good with same name is already in linked list
            System.out.println(("Baked Good  with this name already exists."));
        } else {
            goods.add(bg); //adds object to baked good linked list
            bakedGoodsList += goodName; //adds inputted name to global string (for checking if object is already in list)
            bakedGoodChoice.getItems().add(bg); //adds object to choicebox for selecting which baked good to add recipe to
            populateBakedGoodList(); //adds baked good to listview to display all baked goods in the linked list

            //prints added good to console as well as index
            System.out.println(goods.get(printGoodIndex));
            printGoodIndex++;
            System.out.println("No of baked goods : " + goods.numberOfNodes());

        }
    }

    //removes baked good object at selected index in linked list
    @FXML
    void removeBakedGood(ActionEvent event) {
        goods.deleteNode(bakedGoodListView.getSelectionModel().getSelectedIndex());
        populateBakedGoodList();
        bakedGoodsList = ""; //resets global string
    }

    //adds all baked good to listview to display baked goods in the linked list
    void populateBakedGoodList() {
        bakedGoodListView.getItems().clear(); //clears listview for any updates
        for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through each baked good
            BakedGoods bg = (BakedGoods) goods.get(i+1);
            bakedGoodListView.getItems().add(bg); //adds good to listview
            bakedGoodsList += bg.getBakedGoodName(); //repopulates global string with all baked goods currently in list
        }
    }

    //adds ingredient object to global ingredients linked list
    @FXML
    public void addIngredient (ActionEvent actionEvent) {
        String igName = ingredientName.getText(); //creates variables with inputted data
        String description = ingredientDescription.getText(); //creates variables with inputted data
        int cals = Integer.parseInt(calories.getText()); //creates variables with inputted data

        Ingredients ingredient = new Ingredients(igName, description, cals);

        if (ingredientNameList.contains(igName)) { //checks if ingredient with same name is already in linked list
            System.out.println(("Ingredient with this name already exists."));
        } else {

            ingredientsList.add(ingredient); //adds object to ingredient linked list
            ingredientListView2.getItems().add(ingredient); //adds ingredient to listview
            ingredientNameList += igName; //adds inputted name to global string (for checking if object is already in list)
            populateIngredientList(); //adds ingredient to listview to display all ingredients in the linked list

            //prints added ingredient to console as well as index
            System.out.println(ingredientsList.get(printIngredientIndex));
            printIngredientIndex++;
            System.out.println("No of ingredients : " + ingredientsList.numberOfNodes());

        }
    }

    //adds all ingredients to listview to display ingredients in the linked list
    public void populateIngredientList() {
        ingredientListView.getItems().clear(); //clears listview for any updates
        for (int i = 0; i < ingredientsList.numberOfNodes(); i++) { //cycles through each ingredient
            Ingredients ig = (Ingredients) ingredientsList.get(i+1);
            ingredientListView.getItems().add(ig); //adds ingredient to listview
            ingredientNameList += ig.getIngredientName(); //repopulates global string with all ingredients currently in list
        }
    }

    //removes ingredient object at selected index in linked list
    public void deleteIngredient(ActionEvent event) {
        ingredientsList.deleteNode(ingredientListView.getSelectionModel().getSelectedIndex());
        populateIngredientList(); //updates linked list
        ingredientNameList = ""; //resets global string
    }


    @FXML
    void addRecipe(ActionEvent event) {
        String rName = recipeName.getText(); //creates variables with inputted data

        Recipe recipe = new Recipe(rName);

        if (recipeNameList.contains(rName)) { //checks if recipe with same name is already in linked list
            System.out.println(("Recipe with this name already exists."));
        } else {
            BakedGoods selectedGood = bakedGoodChoice.getSelectionModel().getSelectedItem(); //gets selected baked good to add recipe to
            selectedGood.recipes.add(recipe); //adds recipe to selected baked good
            recipeNameList += rName; //adds inputted name to global string (for checking if object is already in list)
            populateRecipeList(); //adds recipe to listview

            //prints added recipe to console as well as index
            System.out.println(selectedGood.recipes.get(printRecipeIndex));
            printRecipeIndex++;
            System.out.println("No of recipes : " + selectedGood.recipes.numberOfNodes());

        }
    }

    //adds all recipes to listview to display ingredients in the linked list
    void populateRecipeList() {
        recipeListView.getItems().clear(); //clears listview for any updates
        BakedGoods selectedGood = bakedGoodChoice.getSelectionModel().getSelectedItem(); //gets selected baked good recipe is being added to
        for (int i = 0; i < selectedGood.recipes.numberOfNodes(); i++) { //cycles through each recipe
            Recipe recipe = (Recipe) selectedGood.recipes.get(i+1);
            recipeListView.getItems().add(recipe); //adds recipe to listview
            recipeNameList += recipe.getRecipeName(); //repopulates global string with all ingredients currently in list
        }
    }

    //removes recipe object at selected index in linked list
    @FXML
    void deleteRecipe(ActionEvent event) {
        BakedGoods selectedGood = bakedGoodChoice.getSelectionModel().getSelectedItem();
        selectedGood.recipes.deleteNode(recipeListView.getSelectionModel().getSelectedIndex());
        populateRecipeList(); //updates list view
        recipeNameList = ""; //resets global string
    }

    //adds all recipe ingredients to separate listview
    @FXML
    void populateRecipeIngredientListView() {
        recipeIngredientListView.getItems().clear(); //clears listview for any updates
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem(); //gets selected recipe that an ingredient is being added to
        for (int i = 0; i < selectedRecipe.ingredients.numberOfNodes(); i++) { //cycles through all ingredients in a recipe
            Ingredients ingredient = (Ingredients) selectedRecipe.ingredients.get(i+1);
            recipeIngredientListView.getItems().add(ingredient); //adds ingredient to listview
        }
    }

    //adds selected ingredient to a recipe and listview
    @FXML
    void addIngredientToRecipe() {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem(); //gets selected recipe that an ingredient is being added to
        Ingredients ingredient = ingredientListView2.getSelectionModel().getSelectedItem(); //gets selected ingredient that will be added to a recipe
        float quantity = Float.parseFloat(grams.getText()); //creates variables with inputted data
        ingredient.setQuantity(quantity); //sets ingredient quantity to inputted data
        selectedRecipe.ingredients.add(ingredient); //adds selected ingredient to selected recipe
        populateRecipeIngredientListView(); //updates listview
    }

    //updates listview and linked list with any changes in inputted data
    @FXML
    void updateBakedGood(ActionEvent event) {
        BakedGoods selectedGood = bakedGoodListView.getSelectionModel().getSelectedItem(); //gets selected good to update
        String goodName = bakedGoodName.getText(); //creates variables with inputted data
        String countryOfOrigin = originCountry.getText(); //creates variables with inputted data
        String description = goodDescription.getText(); //creates variables with inputted data

        if (bakedGoodsList.contains(goodName)) { //checks if baked good with same name is already in linked list
            System.out.println(("Baked Good with this name already exists."));
        } else {
            selectedGood.setBakedGoodName(goodName); //sets new name
            selectedGood.setCountryOfOrigin(countryOfOrigin); //sets new country of origin
            selectedGood.setDescription(description); //sets new description
            populateBakedGoodList(); //repopulates listview with new data
        }

    }

    //updates listview and linked list with any changes in inputted data
    @FXML
    void updateIngredient(ActionEvent event) {
        Ingredients selectedIngredient = ingredientListView.getSelectionModel().getSelectedItem(); //gets selected ingredient to update
        String igName = ingredientName.getText(); //creates variables with inputted data
        String description = ingredientDescription.getText(); //creates variables with inputted data
        int cals = Integer.parseInt(calories.getText()); //creates variables with inputted data

        if (ingredientNameList.contains(igName)) { //checks if ingredient with same name is already in linked list
            System.out.println(("Ingredient with this name already exists."));
        } else {
            selectedIngredient.setIngredientName(igName); //sets new name
            selectedIngredient.setDescription(description); //sets new description
            selectedIngredient.setCalories(cals); //sets new calories
            populateIngredientList(); //repopulates listview with new data
        }
    }

    //updates listview and linked list with any changes in inputted data
    @FXML
    void updateRecipe(ActionEvent event) {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem(); //gets selected recipe to update
        String rName = recipeName.getText(); //creates variables with inputted data

        if (recipeNameList.contains(rName)) {//checks if recipe with same name is already in linked list
            System.out.println(("Recipe with this name already exists."));
        } else {
            if (rName != null) { //if text box is not blank
                selectedRecipe.setRecipeName(rName); //sets new name
                populateRecipeList(); //repopulates listview with new data
            }
            else {
                System.out.println("Couldn't Update Name");
            }
        }
    }

    //updates listview and linked list with any changes in inputted data
    @FXML
    void updateIngredientQuantity() {
        Ingredients selectedIngredient = recipeIngredientListView.getSelectionModel().getSelectedItem(); //gets selected ingredient to update
        float quantity = Float.parseFloat(grams.getText()); //creates variables with inputted data
        if (quantity != 0) { //quantity cannot be 0
            selectedIngredient.setQuantity(quantity); //sets new quantity
            populateRecipeIngredientListView(); //repopulates listview with new data
        }
        else {
            System.out.println("Couldn't Update Quantity");
        }
    }

    //removes selected ingredient from a selected recipe
    @FXML
    void removeIngredient(ActionEvent event) {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem(); //gets selected recipe in which ingredient is to be removed from
        int ingredientIndex = recipeIngredientListView.getSelectionModel().getSelectedIndex(); //gets selected ingredient index that will be removed
        selectedRecipe.ingredients.deleteNode(ingredientIndex); //removes ingredient at selected index
        populateRecipeIngredientListView(); //repopulates listview with new data
    }

    //lists any baked goods/ingredients/recipes that contain the inputted phrase in search bar
    @FXML
    void Search(ActionEvent event) {
        searchList.getItems().clear(); //clears listview
        searchedIngredientList.getItems().clear(); //clears selected object listview
        String searchedPhrase = searchField.getText(); //gets inputted search phrase in search bar

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Baked Good")) { //filters search by baked goods
            for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through baked good linked list
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                if (selectedGood.getBakedGoodName().toLowerCase().contains(searchedPhrase.toLowerCase()) //checks if name contains search phrase
                        || selectedGood.getDescription().toLowerCase().contains(searchedPhrase.toLowerCase()) //checks if descr. contains search phrase
                        || selectedGood.getCountryOfOrigin().toLowerCase().contains(searchedPhrase.toLowerCase())) { //checks if C.O.O contains search phrase
                    searchList.getItems().add(selectedGood); //adds baked good to listview
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Recipe")) { //filters search by recipes
            for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through baked good linked list
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int k = 0; k < selectedGood.recipes.numberOfNodes(); k++) { //cycles recipe linked list
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    if (selectedRecipe.getRecipeName().toLowerCase().contains(searchedPhrase.toLowerCase())) { //checks if name contains search phrase
                        searchList.getItems().add(selectedRecipe); //adds recipe to listview
                    }
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Ingredients")) { //filters search by ingredients
            for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through baked good linked list
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int k = 0; k < selectedGood.recipes.numberOfNodes(); k++) { //cycles through recipe linked list
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    for (int j = 0; j < selectedRecipe.ingredients.numberOfNodes(); j++) { //cycles ingredients linked list
                        Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i);
                        if (selectedIngredient.getIngredientName().toLowerCase().contains(searchedPhrase.toLowerCase()) //checks if name contains search phrase
                                || selectedIngredient.getDescription().toLowerCase().contains(searchedPhrase.toLowerCase())) { //checks if descr. contains search phrase
                            searchList.getItems().add(selectedIngredient);//adds ingredient to listview
                        }
                    }
                }
            }
        }

    }

    @FXML
    void populateIngredientSearchList() {
        searchedIngredientList.getItems().clear(); //clears listview
        Object selectedObject = searchList.getSelectionModel().getSelectedItem(); //gets whatever object is selected from search results

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Baked Good")) { //filters by baked goods
            for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through baked good linked list
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                if (selectedObject.equals(selectedGood)) { //checks if selected object is the same as an object in the baked goods linked list
                    for (int j = 0; j < selectedGood.recipes.numberOfNodes(); j++) { //cycles through recipe linked list
                        Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i + 1);
                        for (int k = 0; k < selectedRecipe.ingredients.numberOfNodes(); k++) { //cycles through ingredients linked list
                            Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i + 1);
                            searchedIngredientList.getItems().add(selectedIngredient); //adds ingredient of selected baked good to listview
                        }
                    }
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Ingredients")) { //filters by ingredients
            for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through baked good linked list
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int j = 0; j < selectedGood.recipes.numberOfNodes(); j++) { //cycles through recipe linked list
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    for (int k = 0; k < selectedRecipe.ingredients.numberOfNodes(); k++) { //cycles through ingredients linked list
                        Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i + 1);
                        if (selectedObject.equals(selectedIngredient)) { //if selected object is the same as an object in the ingredient linked list
                            searchedIngredientList.getItems().add(selectedGood); //adds baked good of selected ingredient to listview
                        }
                    }
                }
            }
        }

        if (searchFilter.getSelectionModel().getSelectedItem().contains("Recipe")) { //filters by recipes
            for (int i = 0; i < goods.numberOfNodes(); i++) { //cycles through baked good linked list
                BakedGoods selectedGood = (BakedGoods) goods.get(i + 1);
                for (int j = 0; j < selectedGood.recipes.numberOfNodes(); j++) { //cycles through recipe linked list
                    Recipe selectedRecipe = (Recipe) selectedGood.recipes.get(i);
                    if (selectedObject.equals(selectedRecipe)) { //if selected object is the same as an object in the recipe linked list
                        for (int k = 0; k < selectedRecipe.ingredients.numberOfNodes(); k++) { //cycles through ingredients linked list
                            Ingredients selectedIngredient = (Ingredients) selectedRecipe.ingredients.get(i + 1);
                            searchedIngredientList.getItems().add(selectedIngredient); //adds ingredient of selected recipe to listview
                        }
                    }
                }
            }
        }

    }

    //loads previously saved objects
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

    //resets system
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

    //saves objects
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



    //sets filter choice box options for searching
    public void initialize() {
        searchFilter.getItems().addAll("Baked Good", "Ingredients", "Recipe");
    }

}