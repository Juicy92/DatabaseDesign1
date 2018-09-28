import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.*;

 class UpdateFuelTypeDialog extends Dialog {
    private ListView<String> list = new ListView<>();
    private ResultSet resultSet;
    private Connection con;
    private Statement statement;

     UpdateFuelTypeDialog() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
        statement = con.createStatement();

        setTitle("Fuel Type DB");
        setHeaderText("Please fill the relavant fields");
        Label labelFuelTypeId = new Label("Fuel Type ID");
        Label labelFuelName = new Label("Fuel Name");
        Label labelFuelDescription = new Label("Fuel Description");
        Label labelBuyPricePerLitre = new Label("Buy Price Per Litre");
        Label labelSellPricePerLitre = new Label("Sell Price Per Litre");

        TextField textFieldFuelTypeId = new TextField();
        TextField textFieldFuelName = new TextField();
        TextField textFieldFuelDescription = new TextField();
        TextField textFieldBuyPricePerLitre = new TextField();
        TextField textFieldSellPricePerLitre = new TextField();


        GridPane grid = new GridPane();
        grid.add(labelFuelTypeId, 1, 1);
        grid.add(textFieldFuelTypeId, 2, 1);
        grid.add(labelFuelName, 1, 2);
        grid.add(textFieldFuelName, 2, 2);
        grid.add(labelFuelDescription, 1, 3);
        grid.add(textFieldFuelDescription, 2, 3);
        grid.add(labelBuyPricePerLitre, 1, 4);
        grid.add(textFieldBuyPricePerLitre, 2, 4);
        grid.add(labelSellPricePerLitre, 1, 5);
        grid.add(textFieldSellPricePerLitre, 2, 5);


        updateData();

        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button UpdateButton = new Button("Update!");
        UpdateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (list.getSelectionModel().getSelectedItem() != null) {

                    String strFuelType = textFieldFuelTypeId.getText();
                    String strFuelName = textFieldFuelName.getText();
                    String strFuelDescription = textFieldFuelDescription.getText();
                    String strFuelBuyPrice = textFieldBuyPricePerLitre.getText();
                    String strFuelSellPrice = textFieldSellPricePerLitre.getText();

                    try {
                        System.out.println(list.getSelectionModel().getSelectedIndex());
                        resultSet.absolute(list.getSelectionModel().getSelectedIndex() + 1); // Move result set cursor to the item we have selected


                        String updateQuery = "UPDATE Fuel_Type SET FuelTypeId='" + strFuelType + "',FuelName='" + strFuelName + "',FDescription='" + strFuelDescription + "',BuyPerLitre='" + strFuelBuyPrice + "',SellPerLitre='" + strFuelSellPrice + "' WHERE CustomerNo = '" + resultSet.getString("FuelTypeId") + "';";
                        System.out.println(updateQuery);

                        statement.executeUpdate(updateQuery);

                        updateData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }
                getDialogPane().getScene().getWindow().hide();
            }
        });

        Label label = new Label("Please choose a row to Update");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, list, UpdateButton, grid); // add the items to the vbox

        getDialogPane().setContent(vBox); // add the items to the view
        showAndWait();
    }

    private void updateData() throws SQLException {
        list.getItems().clear();

        String query = "select * from Fuel_Type";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.getItems().add(resultSet.getString("FuelTypeId") + "\t" + resultSet.getString("FuelName") + " " + resultSet.getString("FDescription")+" "+resultSet.getString("BuyPerLitre")+" "+resultSet.getString("SellPerLitre"));

        }
    }
}
