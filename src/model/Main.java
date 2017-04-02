package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage scena;

    @Override
    public void start(Stage primaryStage) throws Exception{
        scena = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/gui.fxml"));
        primaryStage.setTitle("Monte Carlo");
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }

    public Stage getStage()
    {
        return scena;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
