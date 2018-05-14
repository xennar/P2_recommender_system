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

    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp(){
        FileReader filereader = new FileReader();
        user_manager = new User_Manager(filereader);
        product_manager = new Product_Manager(filereader);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();

        ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies);
    }

    @Test
    void getRatedProducts() {

    }

    @Test
    void getProductRating() {
        assertEquals(2.5, ListOfUsers.get(0).GetProductRating(ListOfMovies.get(31)));
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
    }
}