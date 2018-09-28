import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.*;
 class ViewDeliveryDialog extends Dialog {
    private ListView<String> list = new ListView<>();
    private ResultSet resultSet;
    private Connection con;
    private Statement statement;

    ViewDeliveryDialog() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
        statement = con.createStatement();

        updateData();

        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button CloseButton = new Button("Close");
        CloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getDialogPane().getScene().getWindow().hide();


            }
        });



        VBox vBox = new VBox();
        vBox.getChildren().addAll( list, CloseButton); // add the items to the vbox

        getDialogPane().setContent(vBox); // add the items to the view
        showAndWait();
    }

    private void updateData() throws SQLException {
        list.getItems().clear();

        String query = "select * from Delivery";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.getItems().add(resultSet.getString("DeliveryId") + "\t" + resultSet.getString("CustomerNo") + "\t" + resultSet.getString("FuelTypeId") + "\t"+ resultSet.getString("DeliveryDate"));

        }
    }
}

