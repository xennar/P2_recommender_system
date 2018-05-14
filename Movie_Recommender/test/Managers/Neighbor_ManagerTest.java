package Managers;

import Framework.FileReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Neighbor_ManagerTest {

    @BeforeEach
    void SetUp(){
        FileReader filereader = new FileReader();
        User_Manager user_manager = new User_Manager(filereader);
        Product_Manager product_manager = new Product_Manager(filereader);

        ArrayList<RatingsWatcher<Movie>> ListOfUsers = user_manager.GetListOfUsers();
        ArrayList<Movie> ListOfMovies = product_manager.GetProductList();

        Ratings_Manager ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies);
    }

    @Test
    void getNewNeighbors() {

    }

    @Test
    void getNeighborList() {
    }
}