import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class updateDb extends Tab {
    public updateDb()throws SQLException {

        StackPane Update = new StackPane();
        setText("Update Record");

        Label label = new Label("Please choose what Table you want to Update a record From");
        setContent(Update);
        VBox vb = new VBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Customers");
        rb1.setToggleGroup(group);
        rb1.setSelected(false);

        RadioButton rb2 = new RadioButton("Fuel Types");
        rb2.setToggleGroup(group);
        rb2.setSelected(false);

        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rb1.isSelected()){

                    try {
                        new UpdateCustomersDialog();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if(rb2.isSelected()){

                }
            }
        });

        HBox hb = new HBox();
        hb.getChildren().add(vb);

        vb.getChildren().add(label);
        vb.getChildren().add(rb1);
        vb.getChildren().add(rb2);
        vb.getChildren().add(okButton);

        Update.getChildren().add(hb);
        Update.getChildren().add(vb);
    }

}
