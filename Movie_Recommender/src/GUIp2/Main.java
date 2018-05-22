package GUIp2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene firstScene = new Scene((Parent) FXMLLoader.load(getClass().getResource("Login.fxml")));
        primaryStage.setTitle("Movie Recommender");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
