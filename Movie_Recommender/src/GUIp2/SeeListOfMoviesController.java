package GUIp2;

import Managers.*;
import Movie.Movie;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SeeListOfMoviesController implements Initializable {

    @FXML
    TableView<Movie> listWithMovies;
    @FXML
    TableColumn<Movie, String> MovieColumn;
    @FXML
    Button AddMovies;
    @FXML
    Button PreviousRatings;
    @FXML
    Button BackButton;
    @FXML
    Button GetRecommendation1;
    @FXML
    Button AddRating;
    @FXML
    AnchorPane SeeListMovies;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public SeeListOfMoviesController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }


    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Movie> ListOfProducts = FXCollections.observableArrayList(product_manager.GetProductList());
        for (Movie s : ListOfProducts) {
            if(!user_manager.getCurrent_user().GetRatedProducts().contains(s))
            listWithMovies.getItems().add(s);
        }

        MovieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().GetString()));

        AddRating.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = listWithMovies.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

                Movie NewlyWatched = listWithMovies.getItems().get(row);
                TextInputDialog dialog = new TextInputDialog("Rating");
                dialog.setTitle("New Watched Movie");
                dialog.setContentText("Please input your rating for the movie");
                Optional<String> change = dialog.showAndWait();
                double change_as_double;
                if (change.isPresent()) {
                    change_as_double = Double.valueOf(change.get());
                    if (change_as_double > 0 && change_as_double <= 5) {
                        user_manager.getCurrent_user().AddNewRatedProductDuringSession(product_manager.getProductFromID(NewlyWatched.GetID()), change_as_double);
                        listWithMovies.getItems().remove(NewlyWatched);
                        listWithMovies.refresh();
                    }
                }
            }
        });

        PreviousRatings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousRatings.fxml"));
                fxmlLoader.setController(new PreviousRatingsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    SeeListMovies.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        GetRecommendation1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GetRecommendation.fxml"));
                fxmlLoader.setController(new GetRecommendationController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    SeeListMovies.getChildren().add(fxmlLoader.load());
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
                    SeeListMovies.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        BackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                fxmlLoader.setController(new MenuOptionsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    SeeListMovies.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
