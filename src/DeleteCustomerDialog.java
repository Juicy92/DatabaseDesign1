import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.*;

class DeleteCustomerDialog extends Dialog {
    private ListView<String> list = new ListView<>();
    private ResultSet resultSet;
    private Connection con;
    private Statement statement;

    DeleteCustomerDialog() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
        statement = con.createStatement();

        updateData();

        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button deleteButton = new Button("Delete!");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (list.getSelectionModel().getSelectedItem() != null) {

                    try {
                        System.out.println(list.getSelectionModel().getSelectedIndex());
                        resultSet.absolute(list.getSelectionModel().getSelectedIndex() + 1); // Move result set cursor to the item we have selected

                        String deleteQuery = "DELETE FROM customers WHERE CustomerNo = '" + resultSet.getString("CustomerNo") + "';";
                        System.out.println(deleteQuery);

                        statement.executeUpdate(deleteQuery);

                        updateData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    getDialogPane().getScene().getWindow().hide();
                }
            }
        });

        Label label = new Label("Please choose a row to delete");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, list, deleteButton); // add the items to the vbox

        getDialogPane().setContent(vBox); // add the items to the view
        showAndWait();
    }

    private void updateData() throws SQLException {
        list.getItems().clear();

        String query = "select * from Customers";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.getItems().add(resultSet.getString("CustomerNo") + "\t" + resultSet.getString("fname") + " " + resultSet.getString("lname")+" "+resultSet.getString("Address")+" "+resultSet.getString("PhoneNo"));

        }
    }
}
