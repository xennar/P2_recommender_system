package GUIp2;

import Managers.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//This controller allows the user to access the various features of the program
public class MenuOptionsController implements Initializable {

    @FXML
    Button BackButton;
    @FXML
    Button ListOfMovies;
    @FXML
    Button GetRecommendationButton;
    @FXML
    Button AddMovies;
    @FXML
    Button PreviousRatings;
    @FXML
    AnchorPane MenuOptionsScreen;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public MenuOptionsController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager){
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    public void initialize(URL location, ResourceBundle resources) {
        //This changes the screen into the Tableview, which shows watched movies and their scores
        PreviousRatings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousRatings.fxml"));
                fxmlLoader.setController(new PreviousRatingsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    MenuOptionsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This changes the screen into GetRecommendation.fxml, which allows the user to get recommendations
        GetRecommendationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GetRecommendation.fxml"));
                fxmlLoader.setController(new GetRecommendationController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    MenuOptionsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This changes the screen into AddNewMovie.fxml, which allows the user to add new movies to the database
        AddMovies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNewMovie.fxml"));
                fxmlLoader.setController(new AddNewMovieController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    MenuOptionsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This changes the screen into SeeListOfMovies.fxml, which allows the user to see the TableView of all movies
        ListOfMovies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeeListOfMovies.fxml"));
                fxmlLoader.setController(new SeeListOfMoviesController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    MenuOptionsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This button logs the user out of the system, by returning to the Login screen.
        BackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                fxmlLoader.setController(new LoginController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    MenuOptionsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
