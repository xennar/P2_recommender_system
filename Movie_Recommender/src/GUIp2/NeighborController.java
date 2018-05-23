package GUIp2;

import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NeighborController implements Initializable {

    @FXML
    AnchorPane NeighborView;
    @FXML
    TabPane NeighborViewTabs;
    @FXML
    Tab MoviesInCommonTab;
    @FXML
    TableView MoviesInCommonTable;
    @FXML
    TableColumn MoviesInCommonMovie;
    @FXML
    TableColumn CurrentUserMovieRating;
    @FXML
    TableColumn NeighborMovieRatings;
    @FXML
    Tab CorrelationGraphTab;
    @FXML
    AnchorPane CorrelationGraphAnchorPane;
    @FXML
    LineChart CorrelationGraphLineView;
    @FXML
    ScatterChart CorrelationGraphScatterChart;
    @FXML
    NumberAxis CurrentUserScoresLine;
    @FXML
    NumberAxis NeighborScoresLine;
    @FXML
    NumberAxis CurrentUserScoresScatter;
    @FXML
    NumberAxis NeighborScoresScatter;

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public NeighborController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
