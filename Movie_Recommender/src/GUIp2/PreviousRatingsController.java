package GUIp2;

import Managers.Product_Manager;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviousRatingsController implements Initializable {



    @FXML
    TableView<Movie> ListWithPreviousRatings;
    @FXML
    TableColumn<Movie, Integer> MovieID;
    @FXML
    TableColumn<Movie, String> MovieColumm;
    @FXML
    TableColumn<Movie, Double> RatingColumm;
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

    User_Manager user_manager;
    Product_Manager product_manager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MovieColumm.setCellValueFactory(new PropertyValueFactory<Movie, String>("Name"));
        MovieID.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("ID"));
        RatingColumm.setCellValueFactory(new PropertyValueFactory<Movie, Double>("Ratings"));

        ListWithPreviousRatings.getItems().setAll(product_manager.GetProductList());
    }

    public void PreviousRatingsButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("PreviousRatings.fxml"));
        this.PreviousRatingsScreen.getChildren().add(PreviousRatingsParent);
    }

    public void goToRecommendation(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("GetRecommendation.fxml"));
        this.PreviousRatingsScreen.getChildren().add(PreviousRatingsParent);
    }

    public void addMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("AddNewMovie.fxml"));
        this.PreviousRatingsScreen.getChildren().add(PreviousRatingsParent);
    }

    public void ListOfMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("SeeListOfMovies.fxml"));
        this.PreviousRatingsScreen.getChildren().add(PreviousRatingsParent);
    }
    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
        this.PreviousRatingsScreen.getChildren().add(LoginParent);
    }

    public void ChangeRating(ActionEvent actionEvent) {
    }
}
