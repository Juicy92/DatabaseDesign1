import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class deleteDb extends Tab {
    ListView<String> list = new ListView<String>();
    int count = 1;


    public deleteDb() {
        StackPane Delete = new StackPane();
        setText("Delete Record");

        Label label = new Label("Please choose what Database you want to Delete a record From");
        setContent(Delete);
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
                    // if customers radiobutton is selected

                    try {
                        new DeleteCustomerDialog();
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

        Delete.getChildren().add(hb);
        Delete.getChildren().add(vb);
    }

    public void addList(String table, Connection con) throws SQLException {


    }
}
