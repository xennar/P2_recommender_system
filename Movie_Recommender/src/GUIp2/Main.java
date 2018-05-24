package GUIp2;

import Framework.DatabaseReader;
import Framework.ResultsWriter;
import Managers.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        DatabaseReader databaseReader = new DatabaseReader();
        User_Manager user_manager = new User_Manager(databaseReader, "src/Database/Users.csv");
        Product_Manager product_manager = new Product_Manager(databaseReader, "src/Database/movies.csv");
        Ratings_Manager ratings_manager = new Ratings_Manager(databaseReader, user_manager.GetListOfUsers(), product_manager.GetProductList(), "src/Database/adjratings.csv");
        Neighbor_Manager neighbor_manager = new Neighbor_Manager(user_manager.GetListOfUsers());
        Session_Manager session_manager = new Session_Manager();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        fxmlLoader.setController(new LoginController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
        try {
            Scene firstScene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Movie Recommender");
            primaryStage.setScene(firstScene);
            primaryStage.show();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    ResultsWriter.WriteUserData(session_manager.getChangedUserData(), "src/Database/Users.csv");
                    ResultsWriter.WriteProductData(session_manager.getNewlyAddedProducts(), "src/Database/movies.csv");
                    ResultsWriter.WriteRatingsData(session_manager.getChangedRatings(), "src/Database/adjratings.csv");
                    primaryStage.close();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
