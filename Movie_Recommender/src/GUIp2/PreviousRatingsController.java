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

//Controls previousRatings.fxml
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
        //Adds all previous ratings to a list in the form of PreviousRatingsPresent objects.
        PreviousRatingPresent Rating;
        for (Movie m : user_manager.getCurrent_user().GetRatedProducts()) {
            Rating = new PreviousRatingPresent(m.GetID(), m.GetString(), user_manager.getCurrent_user().GetProductRating(m));
            RatedProducts.add(Rating);
        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        //The content of Ratedproducts are added to the Tableview
        for (PreviousRatingPresent p : RatedProducts) {
            ListWithPreviousRatings.getItems().add(p);
        }
        //The cells in the first column are filled with the title of the movie in that row.
        MovieColumm.setCellValueFactory(cellData -> cellData.getValue().getPropTitle());

        //The cells in the second column are filled with the ProductID of the movie in that row.
        MovieID.setCellValueFactory(cellData -> cellData.getValue().getPropID());

        //The cells in the last column are filmed with the rating given to the movie in that row.
        RatingColumm.setCellValueFactory(cellData -> cellData.getValue().getPropRating());


        //Changes the rating of a watched product
        ChangeRating.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //The selected row is rotten
                TablePosition pos = ListWithPreviousRatings.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

                PreviousRatingPresent rating = ListWithPreviousRatings.getItems().get(row);
                //A pop-up window prompts the user for the new rating.
                TextInputDialog dialog = new TextInputDialog("New Rating");
                dialog.setTitle("Change Rating");
                dialog.setContentText("Please input your new rating");
                Optional<String> change = dialog.showAndWait();
                //If the rating is valid, then it is changed, and the tableview is updated to reflect that.
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

        //This changes the screen into the Tableview, which shows watched movies and their scores
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



        //This changes the screen into GetRecommendation.fxml, which allows the user to get recommendations
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

        //This changes the screen into AddNewMovie, which allows the user to add new movies to the database
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

        //This changes the screen into SeeListOfMovies.fxml, which allows the user to see the TableView of all movies
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

        //Changes the screen back into the MenuOptions.fxml screen.
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

