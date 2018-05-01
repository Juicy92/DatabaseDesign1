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
        updateDb up=new updateDb();
        insertDb woo = new insertDb();
        deleteDb deleteDb = new deleteDb();
        tabPane.getTabs().add(woo);
        tabPane.getTabs().add(deleteDb);
        tabPane.getTabs().add(up);

        PrimaryStage.setScene(scene);

        PrimaryStage.show();


        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/databasedesign?serverTimezone=GMT", "user", "pass");
            Statement statement = con.createStatement();
            // statement.executeUpdate("CREATE TABLE test (id int(10) PRIMARY  KEY ,name VARCHAR(12))");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customers");
            ResultSetMetaData rsmd = rs.getMetaData();
            DatabaseMetaData meta = con.getMetaData();


            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}