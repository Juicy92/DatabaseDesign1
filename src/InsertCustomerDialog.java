import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class InsertCustomerDialog extends Dialog {

    public InsertCustomerDialog() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");

        setTitle("Customer DB");
        setHeaderText("Please fill the relevant fields");

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


        Button confirm = new Button("Confirm");

        VBox vBox = new VBox(grid, confirm);
        getDialogPane().setContent(vBox);

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String strCustomer = textFieldCustomer.getText();
                String strFName = textFieldFName.getText();
                String strLName = textFieldLName.getText();
                String strAddress = textFieldAddress.getText();
                String strPhone = textFieldPhone.getText();

                try {

                    Statement st = con.createStatement();

                    String query = "Insert into Customers(CustomerNo,fname,lname,Address,PhoneNo) values('" + strCustomer + "','" + strFName + "','" + strLName + "','" + strAddress + "','" + strPhone + "')";
                    System.out.println(query);
                    st.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                getDialogPane().getScene().getWindow().hide(); // Hide window after pressing the button
            }
        });

        showAndWait();

    }
}
