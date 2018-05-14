package Framework;

import Managers.Product_Manager;
import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    private User_Manager user_manager;
    private Product_Manager product_manager;
    private FileReader fileReader;

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp() {
        fileReader = new FileReader();
        user_manager = new User_Manager(fileReader);
        product_manager = new Product_Manager(fileReader);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
    }

    @Test
    void readProducts() {
        assertEquals(9125, product_manager.GetProductList().size());
    }

    @Test
    void readUsers() {
        assertEquals(671, user_manager.GetListOfUsers().size());
    }

    @Test
    void readRatings() {
    }

    @Test
    void getUserFromID() {
        assertEquals(ListOfUsers.get(0), fileReader.GetUserFromID(1, ListOfUsers));
    }

    @Test
    void getProductFromID() {
        assertEquals(ListOfMovies.get(0), fileReader.GetProductFromID(1, ListOfMovies));
    }
}