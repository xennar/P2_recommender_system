package Managers;

import Framework.FileReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Product_ManagerTest {
    static User_Manager user_manager;
    static Product_Manager product_manager;

    static ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    static ArrayList<Movie> ListOfMovies;

    @BeforeEach
    void SetUp(){
        FileReader filereader = new FileReader();
        User_Manager user_manager = new User_Manager(filereader);
        product_manager = new Product_Manager(filereader);


        ListOfUsers = user_manager.GetListOfUsers();
        ListOfMovies = product_manager.GetProductList();
        RatingsWatcher<Movie> watcher;
        for(int counter = 672; counter <686; counter++) {
            watcher = new RatingsWatcher<Movie>(counter, "Test");
            ListOfUsers.add(watcher);
        }
        for(int counter = 0; counter < 42; counter++){
            ListOfMovies.add(product_manager.GetProductList().get(counter));
        }

        Ratings_Manager ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, "stc/Database/adjratings.csv");
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