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

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    User_Manager user_manager;
    Product_Manager product_manager;

    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;


    @BeforeEach
    void SetUp() {
        FileReader filereader = new FileReader();
        user_manager = new User_Manager(filereader);
        product_manager = new Product_Manager(filereader);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        filereader.ReadRatings(ListOfUsers, ListOfMovies, "src/Database/adjratings.csv");
    }

    @Test
    void getTags() {
        assertTrue(ListOfMovies.get(1).GetTags().contains("Adventure"));
        assertTrue(ListOfMovies.get(1).GetTags().contains("Children"));
        assertTrue(ListOfMovies.get(1).GetTags().contains("Fantasy"));
    }

    @Test
    void getRatings() {
        System.out.println(product_manager.getProductFromID(31));
        assertFalse(product_manager.getProductFromID(31).GetRatings().isEmpty());
    }

    @Test
    void getAverage_Rating() {
//        actual value is 3.17857... but with a delta of 0.01 3.17 or 3.18 will be accepted
        assertEquals(3.18, product_manager.getProductFromID(31).GetAverage_Rating(), 0.01);
    }

    @Test
    void addRating() {
        int startSize = product_manager.getProductFromID(31).GetRatings().size();
        product_manager.getProductFromID(31).AddRating(2.5);
        assertEquals(startSize + 1, product_manager.getProductFromID(31).GetRatings().size());
    }


}