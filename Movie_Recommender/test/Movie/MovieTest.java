package Movie;

import Framework.DatabaseReader;
import Managers.Product_Manager;
import Managers.User_Manager;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//This class tests the Movie Class.
class MovieTest {

    private Product_Manager product_manager;
    private ArrayList<Movie> ListOfMovies;


    //Before each test, User profiles are made, movies are made, and the ratings are added to them.
    @BeforeEach
    void SetUp() {
        String moviePath = "test/moviesTest.csv";
        String ratingsPath = "test/adjratingsTest.csv";
        String userPath = "test/UsersTest.csv";
        DatabaseReader filereader = new DatabaseReader();
        User_Manager user_manager = new User_Manager(filereader, userPath);
        product_manager = new Product_Manager(filereader, moviePath);

        ArrayList<RatingsWatcher<Movie>> listOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        filereader.ReadRatings(listOfUsers, ListOfMovies, ratingsPath);
    }

    //This tests whether tags are separated correctly
    @Test
    void getTags() {
        ArrayList<String> TestListOfStrings = new ArrayList<>(Arrays.asList("Adventure", "Children", "Fantasy"));
        assertTrue(ListOfMovies.get(1).GetTags().containsAll(TestListOfStrings));
    }

    //This tests whether ratings are also added to moves.
    @Test
    void getRatings() {
        assertFalse(product_manager.getProductFromID(31).GetRatings().isEmpty());
    }

    //This tests whether "getAverage_Rating" computes the correct value, or an approximate of that.
    @Test
    void getAverage_Rating() {
        assertEquals(133.5/42, product_manager.getProductFromID(31).GetAverage_Rating(), 0.01);
    }

    //this tests whether new Ratings are added correctly.
    @Test
    void addRating() {
        int startSize = product_manager.getProductFromID(31).GetRatings().size();
        product_manager.getProductFromID(31).AddRating(2.5);
        assertEquals(startSize + 1, product_manager.getProductFromID(31).GetRatings().size());
    }


}