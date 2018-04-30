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
                    try {
                        new InsertFuelDialog();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
