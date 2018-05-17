package Framework;

import Managers.Product_Manager;
import Managers.Ratings_Manager;
import Managers.Session_Manager;
import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultsWriterTest {
    private User_Manager user_manager;
    private Product_Manager product_manager;
    private FileReader filereader;
    private String ratingsPath;
    private String testMoviePath;
    private String testAdjratingsPath;
    private String testUserPath;
    private Session_Manager currentSession;

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp() {
        testMoviePath = "test/moviesTest.csv";
        testAdjratingsPath = "test/adjratingsTest.csv";
        testUserPath = "test/UsersTest.csv";
        String moviePath = "src/Database/movies.csv";
        ratingsPath = "src/Database/adjratings.csv";
        String userPath = "src/Database/Users.csv";

        currentSession = new Session_Manager();
        filereader = new FileReader();
        user_manager = new User_Manager(filereader, userPath);
        product_manager = new Product_Manager(filereader, moviePath);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        filereader.ReadRatings(ListOfUsers, ListOfMovies, ratingsPath);
    }

    @Test
    // Tests if the written product matches the product read from the file
    void writeProductData() {
        product_manager.AddNewProduct(164980, "test movie for ResultsWriter", "Great|New|Exciting|drama");
        ResultsWriter.WriteProductData(currentSession.getNewlyAddedProducts(), testMoviePath);

        //creates a new manager to reRead the file, that has the new product in
        Product_Manager testmanager = new Product_Manager(new FileReader(), testMoviePath);
        assertEquals("test movie for ResultsWriter",
                testmanager.GetProductList().get(testmanager.GetProductList().size()-1).GetString());
    }

    @Test
    // Tests if the rating given to a product that user 1 has not seen is added and that the rating equals 4.5
    void writeRatingsData01() {
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(10), 4.5);
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testAdjratingsPath);

        //Creates new objects to read the new modified file
        FileReader testReader = new FileReader();
        User_Manager testUserManager = new User_Manager(testReader, testUserPath);
        Product_Manager testProductManager = new Product_Manager(testReader, testMoviePath);
        testReader.ReadRatings(testUserManager.GetListOfUsers(), testProductManager.GetProductList(), testAdjratingsPath);
        assertEquals(testUserManager.GetListOfUsers().get(0).GetProductRating(testProductManager.getProductFromID(10))
                , 4.5);
        //cleans up the newly added rating for future tests
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testAdjratingsPath);

    }
    @Test
    //Tests if user ones rating of movie 31 changes from 2.5 to 4.5
    void writeRatingsData02() {
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(31), 4.5);
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testAdjratingsPath);

        //Creates new objects to read the altered file
        FileReader testReader = new FileReader();
        User_Manager testUserManager = new User_Manager(testReader, testUserPath);
        Product_Manager testProductManager = new Product_Manager(testReader, testMoviePath);
        testReader.ReadRatings(testUserManager.GetListOfUsers(), testProductManager.GetProductList(), testAdjratingsPath);
        assertEquals(testUserManager.GetListOfUsers().get(0).GetProductRating(testProductManager.getProductFromID(31)),4.5);
        //Returns the rating to its original state for future testing
        user_manager.GetListOfUsers().get(0).AddNewRatedProductDuringSession(product_manager.getProductFromID(31), 2.5);
        ResultsWriter.WriteRatingsData(currentSession.getChangedRatings(), testAdjratingsPath);
    }

    @Test
    //Writes new neighbor ID and ignore ID to a user and tests if the file contains the alterations
    void writeUserData() {
        Session_Manager session_manager = new Session_Manager();
        Ratings_Manager ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, ratingsPath);
        ratings_manager.AddIgnoreToUser(ListOfUsers.get(0), product_manager.getProductFromID(20));
        ListOfUsers.get(0).GetNeighborIDs().add(15);


        ResultsWriter.WriteUserData(session_manager.getChangedUserData(), testUserPath);
        User_Manager testUserManager = new User_Manager(filereader, testUserPath);
        assertTrue(testUserManager.GetListOfUsers().get(0).GetIgnoreIDs().contains(20) &&
            testUserManager.GetListOfUsers().get(0).GetNeighborIDs().contains(15));
        //Returns the file to it's original state before adding ignoreID 20 and neighborID 15, for future testing
        ListOfUsers.get(0).GetNeighborIDs().remove(0);
        ratings_manager.RemoveIgnoreFromUser(ListOfUsers.get(0), product_manager.getProductFromID(20));
        ResultsWriter.WriteUserData(session_manager.getChangedUserData(), testUserPath);
    }

    @Test
    //creates a new user then writes that user to file, and tests if the password is right
    void writeNewUser(){
        Session_Manager session_manager = new Session_Manager();
        User_Manager newUserManager = new User_Manager(filereader, testUserPath);
        int newUserID = newUserManager.GetListOfUsers().size() + 1;
        String newUserPassword = String.valueOf(newUserManager.GetListOfUsers().size() +1);
        user_manager.AddNewUser(newUserID,newUserPassword );
        ResultsWriter.WriteUserData(session_manager.getChangedUserData(), testUserPath);
        User_Manager testUserManager = new User_Manager(filereader, testUserPath);
        assertEquals(newUserPassword, testUserManager.GetUserFromID(newUserID).GetString());
    }


}