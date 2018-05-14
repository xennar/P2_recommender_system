package Managers;

import Framework.FileReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Product_ManagerTest {
    static User_Manager user_manager;
    static Product_Manager product_manager;

    static ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    static ArrayList<Movie> ListOfMovies;

    @BeforeAll
    static void SetUp(){
        FileReader filereader = new FileReader();
        user_manager = new User_Manager(filereader);
        product_manager = new Product_Manager(filereader);

        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        filereader.ReadRatings(ListOfUsers, ListOfMovies);
    }

    @Test
    void getProductList() {
    }

    @Test
    void addNewProduct() {
        int startSize = product_manager.GetProductList().size();
        product_manager.AddNewProduct(164980,"test the Movie", "fun|great|sad|drama");
        assertEquals(startSize + 1, product_manager.GetProductList().size());
    }

    @Test
    void getProductFromID() {
        assertEquals("Dangerous Minds (1995)", product_manager.getProductFromID(31).GetString());
    }
}