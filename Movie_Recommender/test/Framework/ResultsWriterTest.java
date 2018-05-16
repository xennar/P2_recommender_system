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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultsWriterTest {
    private User_Manager user_manager;
    private Product_Manager product_manager;
    private FileReader filereader;
    private String moviePath, ratingsPath, userPath, testMoviePath, testRatingsPath, testUserPath;
    private Session_Manager currentSession;

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp() {
        testMoviePath = "test/moviesTest.csv";
        testRatingsPath = "test/adjratingsTest.csv";
        testUserPath = "test/UsersTest.csv";
        moviePath = "src/Database/movies.csv";
        ratingsPath = "src/Database/adjratings.csv";
        userPath = "src/Database/Users.csv";

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

        ResultsWriter.WriteProductData(currentSession.getNewlyAddedProducts(), testMoviePath);
//        asks the results writer to write the list of new movies from the testMovies list
        Product_Manager testmanager = new Product_Manager(new FileReader(), testMoviePath);
//        reReads the movies.csv file and snapshots the size of products
        assertEquals("test movie for ResultsWriter",
                testmanager.GetProductList().get(testmanager.GetProductList().size()-1).GetString());
    }

    @Test
    void writeRatingsData01() {
        int initialSize = user_manager.GetListOfUsers().get(0).GetRatedProducts().size();
//        Takes a snapshot of the initial size of the list for comparison later
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(10), 4.5);
//        adds the movie rating

        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testRatingsPath);
//        calls the resultsWriter to write in the new ratings, using the changed users and new rated movies lists

        FileReader testReader = new FileReader(); //creates a new file reader
        User_Manager testUserManager = new User_Manager(testReader, testUserPath); // creates a new user_manager with the new file reader to reread the data
        Product_Manager testProductManager = new Product_Manager(testReader, testMoviePath); //creates a new Product_manager to reread data
        testReader.ReadRatings(testUserManager.GetListOfUsers(), testProductManager.GetProductList(), testRatingsPath);
//        uses the newly read data and reads the ratings in order to load all the ratings post changes
        int newSize = testUserManager.GetListOfUsers().get(0).GetRatedProducts().size();
//        snapshots takes the size of rated products from the first user in the user list
        assertEquals(initialSize + 1, newSize);
        // if everything went well the new size should be 1 larger as there has been added 1 new movie rating for user 1 to the csv file

    }
    @Test
    void writeRatingsData02() {
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(31), 4.5);
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testRatingsPath);


        FileReader testReader = new FileReader();
        User_Manager testUserManager = new User_Manager(testReader, testUserPath);
        Product_Manager testProductManager = new Product_Manager(testReader, testMoviePath);
        testReader.ReadRatings(testUserManager.GetListOfUsers(), testProductManager.GetProductList(), testRatingsPath);
        assertEquals(testUserManager.GetListOfUsers().get(0).GetProductRating(testProductManager.getProductFromID(31)),4.5);

        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(31), 2.5);
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testRatingsPath);
    }

    @Test
    void writeUserData() {
        Session_Manager session_manager = new Session_Manager();
        Ratings_Manager ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, ratingsPath);
        ratings_manager.AddIgnoreToUser(ListOfUsers.get(0), product_manager.getProductFromID(20));
        ListOfUsers.get(0).GetNeighborIDs().add(15);


        ResultsWriter.WriteUserData(session_manager.getChangedUserData(), testUserPath);
        User_Manager testUserManager = new User_Manager(filereader, testUserPath);
        assertTrue(testUserManager.GetListOfUsers().get(0).GetIgnoreIDs().contains(20) &&
            testUserManager.GetListOfUsers().get(0).GetNeighborIDs().contains(15));
        
        ListOfUsers.get(0).GetNeighborIDs().remove(0);
        ratings_manager.RemoveIgnoreFromUser(ListOfUsers.get(0), product_manager.getProductFromID(20));
        ResultsWriter.WriteUserData(session_manager.getChangedUserData(), testUserPath);
    }


}