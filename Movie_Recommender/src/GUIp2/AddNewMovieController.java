package GUIp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewMovieController implements Initializable {


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
    }
}
