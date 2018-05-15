package RatingsWatcher;

import Framework.FileReader;
import Managers.Product_Manager;
import Managers.Ratings_Manager;
import Managers.User_Manager;
import Movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RatingsWatcherTest {

    User_Manager user_manager;
    Product_Manager product_manager;
    Ratings_Manager ratings_manager;
    String userPath;
    String moviePath;

    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp(){
        userPath = "test/UsersTest.csv";
        moviePath = "test/moviesTest.csv";
        FileReader filereader = new FileReader();
        user_manager = new User_Manager(filereader, userPath);
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();

        ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, "src/Database/adjratings.csv");
    }

    @Test
    void MakeRatingsWatcher01(){
        RatingsWatcher<Movie> watcher = new RatingsWatcher<Movie>(672, "672", "1|2|3|4");
        assertEquals(4, watcher.GetNeighborIDs().size());
    }

    @Test
    void MakeRatingsWatcher02(){
        RatingsWatcher<Movie> watcher = new RatingsWatcher<Movie>(672, "672", "1|2|3|4", "3|4|6");
        assertEquals(3, watcher.GetIgnoreIDs().size());
    }

    @Test
    void getRatedProducts() {

    }

    @Test
    void getProductRating() {
        assertEquals(2.5, ListOfUsers.get(0).GetProductRating(product_manager.getProductFromID(31)));
    }

    @Test
    void getIgnoreIDs() {
    }

    @Test
    void addNewRatedProduct() {
    }

    @Test
    void getNeighborIDs() {
    }

    @Test
    void getUsersAverageScore() {
        assertEquals(2.55, ListOfUsers.get(0).GetUsersAverageScore());
    }
}