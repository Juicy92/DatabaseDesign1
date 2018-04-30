import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertFuelDialog extends Dialog {

    public InsertFuelDialog() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");


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

        Button confirm = new Button("Confirm");
        VBox vBox = new VBox(grid, confirm);

        getDialogPane().setContent(vBox);

        confirm.setOnAction(event1 -> {
            String strFuelType = textFieldFuelTypeId.getText();
            String strFuelName = textFieldFuelName.getText();
            String strFuelDescription = textFieldFuelDescription.getText();
            String strFuelBuyPrice = textFieldBuyPricePerLitre.getText();
            String strFuelSellPrice = textFieldSellPricePerLitre.getText();

            try {
                Statement st = con.createStatement();

                String query = "Insert into Fuel_Type values('" + strFuelType + "','" + strFuelName + "','" + strFuelDescription + "','" + strFuelBuyPrice + "','" + strFuelSellPrice + "')";
                System.out.println(query);
                st.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            getDialogPane().getScene().getWindow().hide(); // Hide window after pressing the button
        });

        showAndWait();
    }
}
