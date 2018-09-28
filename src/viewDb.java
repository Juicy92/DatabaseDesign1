import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

 class viewDb extends Tab {
     viewDb()throws SQLException {

        StackPane View = new StackPane();
        setText("View Records");

        Label label = new Label("Please choose what Database you want to view records From");
        setContent(View);
        VBox vb = new VBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Invoice");
        rb1.setToggleGroup(group);
        rb1.setSelected(false);

        RadioButton rb2 = new RadioButton("Delivery's");
        rb2.setToggleGroup(group);
        rb2.setSelected(false);

        RadioButton rb3= new RadioButton("Stock");
        rb3.setToggleGroup(group);
        rb3.setSelected(false);
        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (rb1.isSelected()) {
                    // if customers radiobutton is selected
                    try {
                        new ViewInvoiceDialog();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                 else if (rb2.isSelected()){
                    try {
                        new ViewDeliveryDialog();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                else if(rb3.isSelected()){
                    try {
                        new ViewStockDialog();
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
        vb.getChildren().add(rb3);
        vb.getChildren().add(okButton);

        View.getChildren().add(hb);
        View.getChildren().add(vb);
    }


}

