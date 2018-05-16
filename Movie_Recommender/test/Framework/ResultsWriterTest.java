package Framework;

import Managers.Product_Manager;
import Managers.Ratings_Manager;
import Managers.Session_Manager;
import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultsWriterTest {
    private User_Manager user_manager;
    private Product_Manager product_manager;
    private FileReader filereader;
    private String moviePath;
    private String ratingsPath;
    private String userPath;
    private Session_Manager currentSession;

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp() {
        moviePath = "test/moviesTest.csv";
        ratingsPath = "test/adjratingsTest.csv";
        userPath = "test/UsersTest.csv";
        currentSession = new Session_Manager();
        filereader = new FileReader();
        user_manager = new User_Manager(filereader, userPath);
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        filereader.ReadRatings(ListOfUsers, ListOfMovies, ratingsPath);
    }

    @Test
    void writeProductData() {
        int initialSize = product_manager.GetProductList().size();
//        Takes a snapshot of the initial size of the list for comparison later
        product_manager.AddNewProduct(164980, "test movie for ResultsWriter", "Great|New|Exciting|drama"); //TODO add security so movie can't be written if it already exists in file
//        adds the test movie to the current product_manager list of movies, to simulate runtime changes
        ResultsWriter.WriteProductData(currentSession.getNewlyAddedProducts(), moviePath);
//        asks the results writer to write the list of new movies from the testMovies list
        int newSize = new Product_Manager(new FileReader(), moviePath).GetProductList().size();
//        reReads the movies.csv file and snapshots the size of products
        assertEquals(initialSize + 1, newSize); // if everything went well the new size should be 1 larger as there has been added 1 movie to the csv file
    }

    @Test
    void writeRatingsData01() {
        int initialSize = user_manager.GetListOfUsers().get(0).GetRatedProducts().size();
//        Takes a snapshot of the initial size of the list for comparison later
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(10), 4.5);
//        adds the movie rating
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), ratingsPath);
//        calls the resultsWriter to write in the new ratings, using the changed users and new rated movies lists

        FileReader testReader = new FileReader(); //creates a new file reader
        User_Manager testUserManager = new User_Manager(testReader, userPath); // creates a new user_manager with the new file reader to reread the data
        Product_Manager testProductManager = new Product_Manager(testReader, moviePath); //creates a new Product_manager to reread data
        testReader.ReadRatings(testUserManager.GetListOfUsers(), testProductManager.GetProductList(), ratingsPath);
//        uses the newly read data and reads the ratings in order to load all the ratings post changes
        int newSize = testUserManager.GetListOfUsers().get(0).GetRatedProducts().size();
//        snapshots takes the size of rated products from the first user in the user list
        assertEquals(initialSize + 1, newSize);
        // if everything went well the new size should be 1 larger as there has been added 1 new movie rating for user 1 to the csv file
    }
    @Test
    void writeRatingsData02() {
        int initialSize = user_manager.GetListOfUsers().get(0).GetRatedProducts().size();
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(31), 4.5);
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), ratingsPath);

        FileReader testReader = new FileReader();
        User_Manager testUserManager = new User_Manager(testReader, userPath);
        Product_Manager testProductManager = new Product_Manager(testReader, moviePath);
        testReader.ReadRatings(testUserManager.GetListOfUsers(), testProductManager.GetProductList(), ratingsPath);
        int newSize = testUserManager.GetListOfUsers().get(0).GetRatedProducts().size();
        assertEquals(initialSize, newSize);
    }

    @Test
    void writeUserData() {
        Ratings_Manager ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, ratingsPath);
        ratings_manager.AddIgnoreToUser(ListOfUsers.get(0), product_manager.getProductFromID(20));
        Session_Manager session_manager = new Session_Manager();
        ResultsWriter.WriteUserData(session_manager.getChangedUserData(), userPath);

    }

   // @AfterAll
}