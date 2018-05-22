package Framework;

import Managers.Product_Manager;
import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseReaderTest {
    private User_Manager user_manager;
    private Product_Manager product_manager;
    private DatabaseReader databaseReader;
    String moviePath;
    String ratingsPath;
    String userPath;

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp() {
        /*sets up the paths, managers and filereader objects
        * then uses the managers fill list of users and list of movies*/
        moviePath = "src/Database/movies.csv";
        ratingsPath = "src/Database/adjratings.csv";
        userPath = "src/Database/Users.csv";
        databaseReader = new DatabaseReader();
        user_manager = new User_Manager(databaseReader, userPath);
        product_manager = new Product_Manager(databaseReader, moviePath);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
    }
    @Test
    //Tests that the size of the product list is larger than or equals to the original copied movie.csv size
    void readProducts() {
        assertTrue(9125 <= product_manager.GetProductList().size());
    }

    @Test
    //Tests that the size of the user list is larger than equals to the original copied Users.csv user size
    void readUsers() {
        assertTrue(671 <= user_manager.GetListOfUsers().size());
    }

    @Test
    void getUserFromID() {
        assertEquals(ListOfUsers.get(0), databaseReader.GetUserFromID(1, ListOfUsers));
    }

    @Test
    void getProductFromID() {
        assertEquals(ListOfMovies.get(0), databaseReader.GetProductFromID(1, ListOfMovies));
    }
}