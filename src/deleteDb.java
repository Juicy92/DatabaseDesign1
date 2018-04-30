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

import java.sql.*;
import java.util.ArrayList;

public class deleteDb extends Tab {
    ListView<String> list = new ListView<String>();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    PreparedStatement preparedStatement = null;
    int count = 1;
    int delInt = 0;


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
                    try {
                        String query = "select * from Customers";
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
                        Statement al = con.createStatement();
                        ResultSet rs = al.executeQuery(query);

                        while (rs.next()) {

                            list.getItems().add(rs.getString("CustomerNo") + "\t" + rs.getString("fname"));
                            ids.add(rs.getInt("CustomerNo"));


                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                        Button bt1 = new Button("Delete!");
                        Stage dialog = new Stage();
                        GridPane grd_pan = new GridPane();
                        grd_pan.setAlignment(Pos.CENTER);
                        grd_pan.setHgap(10);
                        grd_pan.setVgap(10);
                        Scene scene = new Scene(grd_pan, 300, 300);
                        dialog.setScene(scene);
                        dialog.initModality(Modality.WINDOW_MODAL);
                        Label label = new Label("Please choose a row to delete");

                        grd_pan.add(label, 0, 0);
                        grd_pan.add(list, 0, 1);
                        grd_pan.add(bt1, 0, 2);


                        bt1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (list.getSelectionModel().getSelectedItem() != null) {
                                    int lR = list.getSelectionModel().getSelectedIndex();
                                    String choice = list.getSelectionModel().getSelectedItem();

                                    for (int i = 0; i < ids.size(); i++) {
                                        if (choice.contains(ids.get(i).toString())) {
                                            delInt = ids.get(i);
                                            list.getItems().remove(lR);

                                        }

                                    }

                                    String queryD = "delete from customers where customerNo=?";


                                    try {
                                        preparedStatement = con.prepareStatement(queryD);
                                        preparedStatement.setInt(1, delInt);

                                        // execute delete SQL stetement
                                        preparedStatement.executeUpdate();
                                        System.out.println("delete from test where id="+delInt);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });


                        dialog.showAndWait();
                        rs.close();
                        al.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(rb2.isSelected()){
                    try {
                        String query = "select * from fuel_types";
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/frank?serverTimezone=GMT", "user", "pass");
                        Statement al = con.createStatement();
                        ResultSet rs = al.executeQuery(query);

                        while (rs.next()) {

                            list.getItems().add(rs.getString("id") + "\t" + rs.getString("name"));
                            ids.add(rs.getInt("id"));


                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                        Button bt1 = new Button("Delete!");
                        Stage dialog = new Stage();
                        GridPane grd_pan = new GridPane();
                        grd_pan.setAlignment(Pos.CENTER);
                        grd_pan.setHgap(10);
                        grd_pan.setVgap(10);
                        Scene scene = new Scene(grd_pan, 300, 300);
                        dialog.setScene(scene);
                        dialog.initModality(Modality.WINDOW_MODAL);
                        Label label = new Label("Please choose a row to delete");

                        grd_pan.add(label, 0, 0);
                        grd_pan.add(list, 0, 1);
                        grd_pan.add(bt1, 0, 2);


                        bt1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (list.getSelectionModel().getSelectedItem() != null) {
                                    int lR = list.getSelectionModel().getSelectedIndex();
                                    String choice = list.getSelectionModel().getSelectedItem();

                                    for (int i = 0; i < ids.size(); i++) {
                                        if (choice.contains(ids.get(i).toString())) {
                                            delInt = ids.get(i);
                                            list.getItems().remove(lR);

                                        }

                                    }

                                    String queryD = "delete from fuel_types where id=?";


                                    try {
                                        preparedStatement = con.prepareStatement(queryD);
                                        preparedStatement.setInt(1, delInt);

                                        // execute delete SQL stetement
                                        preparedStatement.executeUpdate();
                                        System.out.println("delete from test where id="+delInt);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });


                        dialog.showAndWait();
                        rs.close();
                        al.close();
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

}
