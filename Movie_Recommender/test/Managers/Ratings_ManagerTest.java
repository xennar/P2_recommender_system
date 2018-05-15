package Managers;

import Framework.FileReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ratings_ManagerTest {

    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;
    String moviePath;
    Product_Manager product_manager;
    Neighbor_Manager neighbor_manager;
    Ratings_Manager ratings_manager;


    @BeforeEach
    void SetUp() {
        moviePath = "test/moviesTest.csv";
        FileReader filereader = new FileReader();
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = new ArrayList<>();
        ListOfMovies = product_manager.GetProductList();
        RatingsWatcher<Movie> watcher;
        for (int counter = 672; counter < 687; counter++) {
            watcher = new RatingsWatcher<Movie>(counter, "password");
            ListOfUsers.add(watcher);
        }
        for (int counter = 0; counter < 43; counter++) {
            ListOfMovies.add(product_manager.GetProductList().get(counter));
        }
        neighbor_manager = new Neighbor_Manager(ListOfUsers);
        ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, "test/Managers/testRatings.csv");
    }

    @Test
    void getListOfMovies() {
        assertTrue(product_manager.GetProductList().containsAll(ratings_manager.GetListOfMovies()));
    }

    @Test
    void getListOfUsers() {
        assertEquals(15, ratings_manager.GetListOfUsers().size());
    }

    @Test
    void getRecommendation01() {
        Movie Recommended_Movie = ratings_manager.GetRecommendation(ListOfUsers.get(0), neighbor_manager, 6);
        assertEquals(ListOfMovies.get(29), Recommended_Movie);
    }

    @Test
    void getRecommendation02() {
        Movie Recommended_Movie = ratings_manager.GetRecommendation(ListOfUsers.get(1), neighbor_manager, 6);
        assertEquals(ListOfMovies.get(24), Recommended_Movie);
    }
}