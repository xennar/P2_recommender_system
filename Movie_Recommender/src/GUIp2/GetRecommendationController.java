package GUIp2;

import Managers.*;
import Movie.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//This controls GetRecommendation.fxml, and allows the user to get recommendations.
public class GetRecommendationController implements Initializable {

    @FXML
    Button Back;
    @FXML
    Button ListOfMovies;
    @FXML
    Button PreviousRatings;
    @FXML
    Button AddMovies;
    @FXML
    Button GetRecommendation;
    @FXML
    Button WhoLikesMovie;
    @FXML
    Button IgnoreThisMovie;
    @FXML
    Button GetNextRecommendation;
    @FXML
    Label NameOfMovie;
    @FXML
    Label MovieTags;
    @FXML
    AnchorPane RecommendationPane;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;
    private Movie Recommended_Movie;

    public GetRecommendationController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    public void initialize(URL location, ResourceBundle resources) {

        //Gets the user their recommendation. Can only get a new one when either the current is added to ignore or rated.
        GetNextRecommendation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //The recommendation is gotten, and the labels are filled with the information of the movie.
                try {
                    Recommended_Movie = ratings_manager.GetRecommendation(user_manager.getCurrent_user(), neighbor_manager, 20);
                    NameOfMovie.setText(Recommended_Movie.GetString());
                    String tags = String.join(" | ", Recommended_Movie.GetTags());
                    MovieTags.setText(tags);
                    //If not recommendations can be gotten, a pop-up window will be used to inform the user of this.
                } catch (RuntimeException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Can't Open menu");
                    alert.setContentText("You cannot get any more recommendations currently, because of your ignores");
                }
            }
        });

        //Adds the movie's ID to the user's ignorelist
        IgnoreThisMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ratings_manager.AddIgnoreToUser(user_manager.getCurrent_user(), Recommended_Movie);
            }
        });

        //Changes the screen to Neighbors.fxml, which allows the user to study their neighbors and their relationship
        WhoLikesMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Neighbors.fxml"));
                fxmlLoader.setController(new NeighborController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    RecommendationPane.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This changes the screen into the Tableview, which shows watched movies and their scores
        PreviousRatings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousRatings.fxml"));
                fxmlLoader.setController(new PreviousRatingsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    RecommendationPane.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This changes the screen into GetRecommendation.fxml, which allows the user to get recommendations
        GetRecommendation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GetRecommendation.fxml"));
                fxmlLoader.setController(new GetRecommendationController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    RecommendationPane.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //This changes the screen into AddNewMovie, which allows the user to add new movies to the database
        AddMovies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNewMovie.fxml"));
                fxmlLoader.setController(new AddNewMovieController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    RecommendationPane.getChildren().add(fxmlLoader.load());
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
                    RecommendationPane.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //Changes the screen back into the MenuOptions.fxml screen.
        Back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                fxmlLoader.setController(new MenuOptionsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    RecommendationPane.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
