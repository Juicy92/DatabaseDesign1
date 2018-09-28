import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    public void start(Stage PrimaryStage) throws Exception {

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Scene scene = new Scene(tabPane, 300, 300);
        viewDb view = new viewDb();
        updateDb up=new updateDb();
        insertDb woo = new insertDb();
        deleteDb deleteDb = new deleteDb();
        tabPane.getTabs().add(woo);
        tabPane.getTabs().add(deleteDb);
        tabPane.getTabs().add(up);
        tabPane.getTabs().add(view);

        PrimaryStage.setScene(scene);

        PrimaryStage.show();


    }
}