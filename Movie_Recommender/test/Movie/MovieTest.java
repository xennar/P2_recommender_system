package Movie;

import Framework.FileReader;
import Managers.Product_Manager;
import Managers.Ratings_Manager;
import Managers.User_Manager;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    User_Manager user_manager;
    Product_Manager product_manager;
    String moviePath;
    String ratingsPath;
    String userPath;

    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;


    @BeforeEach
    void SetUp() {
        moviePath = "test/moviesTest.csv";
        ratingsPath = "test/adjratingsTest.csv";
        userPath = "test/UsersTest.csv";
        FileReader filereader = new FileReader();
        user_manager = new User_Manager(filereader,userPath);
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        filereader.ReadRatings(ListOfUsers, ListOfMovies, ratingsPath);
    }

    @Test
    void getTags() {
        ArrayList<String> TestListOfStrings = new ArrayList<>(Arrays.asList("Adventure", "Children", "Fantasy"));
        assertTrue(ListOfMovies.get(1).GetTags().containsAll(TestListOfStrings));
    }

    @Test
    void getRatings() {
        System.out.println(product_manager.getProductFromID(31));
        assertFalse(product_manager.getProductFromID(31).GetRatings().isEmpty());
    }

    @Test
    void getAverage_Rating() {
//        actual value is 3.17857... but with a delta of 0.01 3.17 or 3.18 will be accepted
        assertEquals(131.5/42, product_manager.getProductFromID(31).GetAverage_Rating(), 0.1);
    }

    @Test
    void addRating() {
        int startSize = product_manager.getProductFromID(31).GetRatings().size();
        product_manager.getProductFromID(31).AddRating(2.5);
        assertEquals(startSize + 1, product_manager.getProductFromID(31).GetRatings().size());
    }


}