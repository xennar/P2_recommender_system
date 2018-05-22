package GUIp2;

import Managers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewMovieController implements Initializable {


    @FXML
    TextField NameMovie;
    @FXML
    TextField YearRelease;
    @FXML
    TextField TagsMovie;
    @FXML
    TextField RatingPersonal;
    @FXML
    Button Back;
    @FXML
    Button PreviousRatings;
    @FXML
    Button ListOfMovies;
    @FXML
    Button AddMovies;
    @FXML
    Button GetRecommendation;
    @FXML
    AnchorPane AddNewMovieScreen;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public AddNewMovieController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void PreviousRatingsButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("PreviousRatings.fxml"));
        this.AddNewMovieScreen.getChildren().add(PreviousRatingsParent);
    }

    public void goToRecommendation(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("GetRecommendation.fxml"));
        this.AddNewMovieScreen.getChildren().add(PreviousRatingsParent);
    }

    public void addMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("AddNewMovie.fxml"));
        this.AddNewMovieScreen.getChildren().add(PreviousRatingsParent);
    }

    public void ListOfMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("SeeListOfMovies.fxml"));
        this.AddNewMovieScreen.getChildren().add(PreviousRatingsParent);
    }
    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
        this.AddNewMovieScreen.getChildren().add(LoginParent);
    }

    public void AddNewMovie(ActionEvent actionEvent) {

        product_manager.AddNewProduct(product_manager.GetProductList().size(), NameMovie.getText() +YearRelease.getText(),
                TagsMovie.getText());

    }
}
