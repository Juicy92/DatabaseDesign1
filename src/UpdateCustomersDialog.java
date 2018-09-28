import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.*;

 class UpdateCustomersDialog extends Dialog {
    private ListView<String> list = new ListView<>();
    private ResultSet resultSet;
    private Connection con;
    private Statement statement;

     UpdateCustomersDialog() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
        statement = con.createStatement();

        Label labelCustomer = new Label("Customer Number");
        Label labelFName = new Label("First Name");
        Label labelLName = new Label("Last Name");
        Label labelAddress = new Label("Address");
        Label labelPhone = new Label("Phone Number");

        TextField textFieldCustomer = new TextField();
        TextField textFieldFName = new TextField();
        TextField textFieldLName = new TextField();
        TextField textFieldAddress = new TextField();
        TextField textFieldPhone = new TextField();

        GridPane grid = new GridPane();
        grid.add(labelCustomer, 1, 1);
        grid.add(textFieldCustomer, 2, 1);
        grid.add(labelFName, 1, 2);
        grid.add(textFieldFName, 2, 2);
        grid.add(labelLName, 1, 3);
        grid.add(textFieldLName, 2, 3);
        grid.add(labelAddress, 1, 4);
        grid.add(textFieldAddress, 2, 4);
        grid.add(labelPhone, 1, 5);
        grid.add(textFieldPhone, 2, 5);


        updateData();

        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button UpdateButton = new Button("Update!");
        UpdateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (list.getSelectionModel().getSelectedItem() != null) {

                    String strCustomer = textFieldCustomer.getText();
                    String strFName = textFieldFName.getText();
                    String strLName = textFieldLName.getText();
                    String strAddress = textFieldAddress.getText();
                    String strPhone = textFieldPhone.getText();

                    try {
                        System.out.println(list.getSelectionModel().getSelectedIndex());
                        resultSet.absolute(list.getSelectionModel().getSelectedIndex() + 1); // Move result set cursor to the item we have selected


                        String updateQuery = "UPDATE customers SET CustomerNo='" + strCustomer + "',fName='" + strFName + "',lName='" + strLName + "',Address='" + strAddress + "',PhoneNo='" + strPhone + "' WHERE CustomerNo = '" + resultSet.getString("CustomerNo") + "';";
                        System.out.println(updateQuery);

                        statement.executeUpdate(updateQuery);

                        updateData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }
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

        String query = "select * from Customers";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            list.getItems().add(resultSet.getString("CustomerNo") + "\t" + resultSet.getString("fname") + " " + resultSet.getString("lname")+" "+resultSet.getString("Address")+" "+resultSet.getString("PhoneNo"));

        }
    }
}