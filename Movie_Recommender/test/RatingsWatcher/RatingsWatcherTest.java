package RatingsWatcher;

import Framework.FileReader;
import Managers.Product_Manager;
import Managers.Ratings_Manager;
import Managers.User_Manager;
import Movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RatingsWatcherTest {

    User_Manager user_manager;
    Product_Manager product_manager;
    Ratings_Manager ratings_manager;
    String userPath;
    String moviePath;

    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp() {
        userPath = "src/Database/Users.csv";
        moviePath = "src/Database/movies.csv";
        FileReader filereader = new FileReader();
        user_manager = new User_Manager(filereader, userPath);
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();

        ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, "src/Database/adjratings.csv");
    }

    @Test
    void MakeRatingsWatcher01() {
        RatingsWatcher<Movie> watcher = new RatingsWatcher<Movie>(672, "672", "1|2|3|4");
        assertEquals(4, watcher.GetNeighborIDs().size());
    }

    @Test
    void MakeRatingsWatcher02() {
        RatingsWatcher<Movie> watcher = new RatingsWatcher<Movie>(672, "672", "1|2|3|4", "3|4|6");
        assertEquals(3, watcher.GetIgnoreIDs().size());
    }

    //This test tests whether the method returns the correct movies.
    @Test
    void getRatedProducts() {
        //A list of all rated products for the user with ID = 1 are made by reading off movieIDs form adjratings.csv,
        //and then getting the movies, which are added to a list.
        ArrayList<Integer> MovieIDs = new ArrayList<>(Arrays.asList(31, 1029, 1061, 1129, 1172, 1263, 1287, 1293, 1339, 1343, 1371, 1405, 1953, 2105, 2150, 2193, 2294, 2455, 2968, 3671));
        ArrayList<Movie> TestList = new ArrayList<Movie>();
        for (Integer i : MovieIDs)
            TestList.add(product_manager.getProductFromID(i));
        assertTrue(user_manager.GetListOfUsers().get(0).GetRatedProducts().containsAll(TestList));
    }

    @Test
    void getProductRating() {
        assertEquals(2.5, ListOfUsers.get(0).GetProductRating(product_manager.getProductFromID(31)));
    }

    @Test
    void addNewRatedProduct01() {
        ListOfUsers.get(0).AddNewRatedProductDuringSession(ListOfMovies.get(0), 4);
        assertTrue(ListOfUsers.get(0).GetRatedProducts().contains(ListOfMovies.get(0)));
    }

    @Test
    void addNewRatedProduct02(){
        ListOfUsers.get(0).AddNewRatedProductDuringSession(ListOfMovies.get(0), 4);
        assertEquals(4 ,ListOfUsers.get(0).GetProductRating(ListOfMovies.get(0)));
    }

    @Test
    void addNewRatedProduct03(){
        ListOfUsers.get(0).AddNewRatedProductDuringSession(ListOfMovies.get(0), 0);
        assertEquals(20 ,ListOfUsers.get(0).GetRatedProducts().size());
    }

    @Test
    void addNewRatedProduct04(){
        ListOfUsers.get(0).AddNewRatedProductDuringSession(ListOfMovies.get(0), 6);
        assertEquals(20 ,ListOfUsers.get(0).GetRatedProducts().size());
    }

    @Test
    void getUsersAverageScore() {
        assertEquals((double) 51 / 20, ListOfUsers.get(0).GetUsersAverageScore());
    }
}