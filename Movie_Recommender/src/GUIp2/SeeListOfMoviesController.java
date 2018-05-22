package GUIp2;

import Managers.Product_Manager;
import Managers.Ratings_Manager;
import Managers.User_Manager;
import Movie.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SeeListOfMoviesController implements Initializable {

    @FXML
    ListView listWithMovies;
    @FXML
    Button AddMovies;
    @FXML
    Button PreviousRatings;
    @FXML
    Button BackButton;
    @FXML
    Button GetRecommendation1;
    @FXML
    AnchorPane SeeListMovies;

    User_Manager user_manager;
    Product_Manager product_manager;

    public SeeListOfMoviesController(User_Manager user_manager, Product_Manager product_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
    }

    public void PreviousRatingsButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("PreviousRatings.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }

    public void goToRecommendation(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("GetRecommendation.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }

    public void addMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("AddNewMovie.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }


    public void ListOfMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("SeeListOfMovies.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Movie> ListOfProducts = FXCollections.observableArrayList(product_manager.GetProductList());
        for (Movie s : ListOfProducts) {
            listWithMovies.getItems().add(s.GetString());
        }
    }

    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
        this.SeeListMovies.getChildren().add(LoginParent);
    }
}
