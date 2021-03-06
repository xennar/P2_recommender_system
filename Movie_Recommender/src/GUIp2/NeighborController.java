package GUIp2;

import Managers.*;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

//This controls Neighbors.fxml, which show the user's relationship with their neighbors.
public class NeighborController implements Initializable {

    @FXML
    AnchorPane NeighborView;
    @FXML
    TabPane NeighborViewTabs;
    @FXML
    Tab MoviesInCommonTab;
    @FXML
    TableView<NeighborUserScorePresent> MoviesInCommonTable;
    @FXML
    TableColumn<NeighborUserScorePresent, String> MoviesInCommonMovie;
    @FXML
    TableColumn<NeighborUserScorePresent, String> CurrentUserMovieRating;
    @FXML
    TableColumn<NeighborUserScorePresent, String> NeighborMovieRatings;

    @FXML
    Tab CorrelationGraphTab;
    @FXML
    AnchorPane CorrelationGraphAnchorPane;
    @FXML
    LineChart CorrelationGraphLineView;
    @FXML
    ScatterChart<Double, Double> ScatterRatings;
    @FXML
    NumberAxis UserAxis;
    @FXML
    NumberAxis NeighborAxis;
    @FXML
    Button BackToMenu;
    @FXML
    ChoiceBox<RatingsWatcher<Movie>> DropDownNeighbor;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;
    private HashMap<RatingsWatcher<Movie>, ArrayList<NeighborUserScorePresent>> MapOfneighborsAndScores;
    private RatingsWatcher<Movie> Current_neighbor;

    public NeighborController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;


        //A HashMap is initialized.
        MapOfneighborsAndScores = new HashMap<>();

        for (int i : user_manager.getCurrent_user().GetNeighborIDs()) {
            //A list of all movies the neighbor and user have in common are made
            RatingsWatcher<Movie> neighbor = user_manager.GetUserFromID(i);
            ArrayList<Movie> MoviesInCommon = new ArrayList<>();

            for (Movie m : neighbor.GetRatedProducts())
                if (user_manager.getCurrent_user().GetRatedProducts().contains(m))
                    MoviesInCommon.add(m);
            //A NeighborUserScorePresent object is made for each movie in common, and added to NUSPlist
            ArrayList<NeighborUserScorePresent> NUSPList = new ArrayList<>();
            for(Movie m: MoviesInCommon){
                NeighborUserScorePresent Nusp = new NeighborUserScorePresent(m.GetString(), user_manager.getCurrent_user().GetProductRating(m), neighbor.GetProductRating(m));

                NUSPList.add(Nusp);
            }
            //The list is then added to the HashMap, with the neighbor as key.
            MapOfneighborsAndScores.put(neighbor, NUSPList);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //The choiceBox is filled with the neighbors.
        ArrayList<RatingsWatcher<Movie>> ListOfNeighbors = new ArrayList<>();
        for(int i : user_manager.getCurrent_user().GetNeighborIDs())
            ListOfNeighbors.add(user_manager.GetUserFromID(i));
        DropDownNeighbor.getItems().addAll(ListOfNeighbors);

        //A Pearson Graph is made, with each data point using the user and the neighbors score for a specific movie
        UserAxis.setAutoRanging(false);
        UserAxis.setLowerBound(0);
        UserAxis.setUpperBound(5.5);
        UserAxis.setTickUnit(0.5);

        NeighborAxis.setAutoRanging(false);
        NeighborAxis.setLowerBound(0);
        NeighborAxis.setUpperBound(5.5);
        NeighborAxis.setTickUnit(0.5);

        ScatterChart.Series<Double, Double> series = new ScatterChart.Series<Double, Double>();
        ScatterRatings.getData().addAll(series);
        series.setName("Neighbor-User");
        DropDownNeighbor.setTooltip(new Tooltip("Select neighbor to compare"));

        //When a neighbor is chosen, the graph updates to show that neighbors graph, and tableview
        DropDownNeighbor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //the currently selected user is gotten, and the List of NeighborUserScorePresents are added to the table
                Current_neighbor = DropDownNeighbor.getItems().get(newValue.intValue());
                MoviesInCommonTable.getItems().clear();
                MoviesInCommonTable.getItems().addAll(MapOfneighborsAndScores.get(Current_neighbor));

                //Each cell in each row are filled with a  movie title, a user rating and a neighbor rating
                MoviesInCommonMovie.setCellValueFactory(cellData -> cellData.getValue().getTitle());
                CurrentUserMovieRating.setCellValueFactory(cellData -> cellData.getValue().getUserscore());
                NeighborMovieRatings.setCellValueFactory(cellData -> cellData.getValue().getNeighborscore());

                //The list of NeighborUserScorePresents are added to the graph
                series.getData().clear();
                ScatterRatings.getData().clear();


                for(NeighborUserScorePresent n : MapOfneighborsAndScores.get(Current_neighbor))
                    series.getData().add(new XYChart.Data<>(n.GetUserrating(), n.GetNeighborRating()));
                ScatterRatings.getData().addAll(series);
                ScatterRatings.autosize();
            }
        });

        //The screen returns to MenuOptions.fxml on action.
        BackToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                fxmlLoader.setController(new MenuOptionsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    NeighborView.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
