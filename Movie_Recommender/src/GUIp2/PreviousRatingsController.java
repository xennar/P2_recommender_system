package GUIp2;

import Managers.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviousRatingsController implements Initializable {


    @FXML
    TableColumn MovieColumm;
    @FXML
    TableColumn RatingColumm;
    @FXML
    TableColumn ChangeRatingColumm;
    @FXML
    Button GetRecommendation;
    @FXML
    Button AddMovies;
    @FXML
    Button PreviousRatings;
    @FXML
    Button ListOfMovies;
    @FXML
    Button Back;
    @FXML
    AnchorPane PreviousRatingsScreen;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public PreviousRatingsController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager){
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }



    public void initialize(URL location, ResourceBundle resources) {
        PreviousRatings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousRatings.fxml"));
                fxmlLoader.setController(new PreviousRatingsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    PreviousRatingsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        GetRecommendation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GetRecommendation.fxml"));
                fxmlLoader.setController(new GetRecommendationController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    PreviousRatingsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        AddMovies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNewMovie.fxml"));
                fxmlLoader.setController(new AddNewMovieController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    PreviousRatingsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ListOfMovies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SeeListOfMovies.fxml"));
                fxmlLoader.setController(new SeeListOfMoviesController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    PreviousRatingsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                fxmlLoader.setController(new MenuOptionsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    PreviousRatingsScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

