package Managers;

import Framework.DatabaseReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Ratings_ManagerTest {

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private ArrayList<Movie> ListOfMovies;
    private Product_Manager product_manager;
    private Neighbor_Manager neighbor_manager;
    private Ratings_Manager ratings_manager;


    @BeforeEach
    void SetUp() {
        String moviePath = "test/moviesTest.csv";
        DatabaseReader filereader = new DatabaseReader();
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = new ArrayList<>();
        ListOfMovies = product_manager.GetProductList();
        RatingsWatcher<Movie> watcher;

        //Because custom users are used during these tests, they need to be made. There are 15 test users.
        for (int counter = 1; counter < 16; counter++) {
            watcher = new RatingsWatcher<>(counter, "password");
            ListOfUsers.add(watcher);
        }
        //Only the first 43 movies are used during these tests.
        for (int counter = 0; counter < 43; counter++) {
            ListOfMovies.add(product_manager.GetProductList().get(counter));
        }
        neighbor_manager = new Neighbor_Manager(ListOfUsers);
        ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, "test/Managers/testRatings.csv");
    }

    @Test
    //Tests that all the movies from the special test case is in the product list of all movies
    void getListOfMovies() {
        assertTrue(product_manager.GetProductList().containsAll(ratings_manager.GetListOfMovies()));
    }

    @Test
    //tests that there are only 15 users that were specifically made for testing recommendations
    void getListOfUsers() {
        assertEquals(15, ratings_manager.GetListOfUsers().size());
    }

    @Test
    //Tests if the recommended movie equals the movie 30 (index 29) that has been manually calculated to get recommended to user 1
    void getRecommendation01() {
        Movie Recommended_Movie = ratings_manager.GetRecommendation(ListOfUsers.get(0), neighbor_manager, 6);
        assertEquals(ListOfMovies.get(29), Recommended_Movie);
    }

    @Test
    //Tests if the recommended movie equals the movie 25 (index 24) that has been manually calculated to get recommended to user 2
    void getRecommendation02() {
        Movie Recommended_Movie = ratings_manager.GetRecommendation(ListOfUsers.get(1), neighbor_manager, 6);
        assertEquals(ListOfMovies.get(24), Recommended_Movie);
    }

    @Test
    //Tests that the ratings manager throws an exception once the only movie
    //capable of being recommended is added to the ignore list
    void getRecommendation03() {
        ratings_manager.AddIgnoreToUser(ListOfUsers.get(0), ratings_manager.GetListOfMovies().get(29));
        assertThrows(RuntimeException.class, () -> ratings_manager.GetRecommendation(ListOfUsers.get(0), neighbor_manager, 6));
    }
}