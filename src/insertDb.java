import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class insertDb extends Tab {


    public insertDb() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
        StackPane Insert = new StackPane();
        setText("Insert record");

        Label label = new Label("Please choose what Database you want to insert a record to");
        setContent(Insert);
        VBox vb = new VBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Customers");
        rb1.setToggleGroup(group);
        rb1.setSelected(false);

        RadioButton rb2 = new RadioButton("Fuel Types");
        rb2.setToggleGroup(group);
        rb2.setSelected(false);

        Button bt1 = new Button("Ok");
        bt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (rb1.isSelected()) {
                    try {
                        new InsertCustomerDialog();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (rb2.isSelected()) {
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    dialog.setTitle("Fuel Type DB");
                    dialog.setHeaderText("Please fill the relavant fields");
                    Label label4 = new Label("Fuel Type ID");
                    Label label1 = new Label("Fuel Name");
                    Label label2 = new Label("Fuel Description");
                    Label label3 = new Label("Buy Price Per Litre");
                    Label label5 = new Label("Sell Price Per Litre");

                    TextField text1 = new TextField();
                    TextField text2 = new TextField();
                    TextField text3 = new TextField();
                    TextField text4 = new TextField();
                    TextField text5 = new TextField();


                    GridPane grid = new GridPane();
                    grid.add(label4, 1, 1);
                    grid.add(text4, 2, 1);
                    grid.add(label1, 1, 2);
                    grid.add(text1, 2, 2);
                    grid.add(label2, 1, 3);
                    grid.add(text2, 2, 3);
                    grid.add(label3, 1, 4);
                    grid.add(text3, 2, 4);
                    grid.add(label5, 1, 5);
                    grid.add(text5, 2, 5);

                    dialog.getDialogPane().setContent(grid);

                    Button confirm = new Button("Confirm");
                    confirm.setOnAction(event1 -> {

                    });
                    dialog.showAndWait();
                }

            }
        });

        HBox hb = new HBox();
        hb.getChildren().add(vb);

        vb.getChildren().add(label);
        vb.getChildren().add(rb1);
        vb.getChildren().add(rb2);
        vb.getChildren().add(bt1);

        Insert.getChildren().add(hb);
        Insert.getChildren().add(vb);
    }
}
