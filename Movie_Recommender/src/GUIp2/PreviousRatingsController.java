package GUIp2;


import Managers.*;
import Movie.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PreviousRatingsController implements Initializable {


    @FXML
    TableView<PreviousRatingPresent> ListWithPreviousRatings;
    @FXML
    TableColumn<PreviousRatingPresent, Number> MovieID;
    @FXML
    TableColumn<PreviousRatingPresent, String> MovieColumm;
    @FXML
    TableColumn<PreviousRatingPresent, String> RatingColumm;
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
    Button ChangeRating;
    @FXML
    AnchorPane PreviousRatingsScreen;


    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;
    private ArrayList<PreviousRatingPresent> RatedProducts;

    public PreviousRatingsController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
        RatedProducts = new ArrayList<>();
        PreviousRatingPresent Rating;
        for (Movie m : user_manager.getCurrent_user().GetRatedProducts()) {
            Rating = new PreviousRatingPresent(m.GetID(), m.GetString(), user_manager.getCurrent_user().GetProductRating(m));
            RatedProducts.add(Rating);
        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        for (PreviousRatingPresent p : RatedProducts) {
            ListWithPreviousRatings.getItems().add(p);
        }
        MovieColumm.setCellValueFactory(cellData -> cellData.getValue().getPropTitle());

        MovieID.setCellValueFactory(cellData -> cellData.getValue().getPropID());

        RatingColumm.setCellValueFactory(cellData -> cellData.getValue().getPropRating());


        ChangeRating.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = ListWithPreviousRatings.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

                PreviousRatingPresent rating = ListWithPreviousRatings.getItems().get(row);
                TextInputDialog dialog = new TextInputDialog("New Rating");
                dialog.setTitle("Change Rating");
                dialog.setContentText("Please input your new rating");
                Optional<String> change = dialog.showAndWait();
                double change_as_double;
                if (change.isPresent()) {
                    change_as_double = Double.valueOf(change.get());
                    if (change_as_double > 0 && change_as_double <= 5) {
                        user_manager.getCurrent_user().AddNewRatedProductDuringSession(product_manager.getProductFromID(rating.getID()), change_as_double);
                        rating.SetRating(change_as_double);
                        rating.SetRatingpropToRating();
                        ListWithPreviousRatings.refresh();
                    }
                }
            }
        });


        RatingColumm.setCellFactory(TextFieldTableCell.forTableColumn());
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
        /*public void onEditChanged(TableColumn.CellEditEvent<PreviousRatingPresent, Double>){
            ListWithPreviousRatings.getSelectionModel().getSelectedItem();
        }*/
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

