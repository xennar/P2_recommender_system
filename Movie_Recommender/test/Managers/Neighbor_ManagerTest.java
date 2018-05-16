package Managers;

import Framework.FileReader;
import Framework.ObjectScore;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Neighbor_ManagerTest {
    String moviePath;
    ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    ArrayList<Movie> ListOfMovies;
    Neighbor_Manager neighbor_manager;
    Ratings_Manager ratings_manager;

    @BeforeEach
    void SetUp() {
        moviePath = "test/moviesTest.csv";
        FileReader filereader = new FileReader();
        Product_Manager product_manager = new Product_Manager(filereader, moviePath);


        ListOfUsers = new ArrayList<>();
        ListOfMovies = product_manager.GetProductList();
        RatingsWatcher<Movie> watcher;
        //Because custom users are used during these tests, they need to be made. There are 15 test users.
        for (int counter = 1; counter < 16; counter++) {
            watcher = new RatingsWatcher<Movie>(counter, "password");
            ListOfUsers.add(watcher);
        }
        //Only the first 43 movies are used during these tests.
        for (int counter = 0; counter < 43; counter++) {
            ListOfMovies.add(product_manager.GetProductList().get(counter));
        }
        neighbor_manager = new Neighbor_Manager(ListOfUsers);
        ratings_manager = new Ratings_Manager(filereader, ListOfUsers, ListOfMovies, "test/Managers/testRatings.csv");
    }

    @Test
    void getNewNeighbors01() {
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(0), 6);
        ArrayList<RatingsWatcher<Movie>> testList = new ArrayList<>();
        for (int counter = 2; counter < 7; counter++)
            testList.add(ListOfUsers.get(counter));
        testList.add(ListOfUsers.get(13));
        ArrayList<RatingsWatcher<Movie>> NeighborsAsList = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> on : neighbors)
            NeighborsAsList.add(on.GetObject());
        assertTrue(testList.containsAll(NeighborsAsList));
    }

    @Test
    void getNewNeighbors02() {
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(1), 6);
        ArrayList<RatingsWatcher<Movie>> testList = new ArrayList<>();
        for (int counter = 7; counter < 13; counter++)
            testList.add(ListOfUsers.get(counter));
        ArrayList<RatingsWatcher<Movie>> NeighborsAsList = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> on : neighbors)
            NeighborsAsList.add(on.GetObject());
        assertTrue(testList.containsAll(NeighborsAsList));
    }

    @Test
    void getNewNeighbors03() {
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(0), 6);
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors1 = neighbor_manager.GetNewNeighbors(ListOfUsers.get(1), 6);


        ArrayList<RatingsWatcher<Movie>> NeighborsAsList = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> on : neighbors)
            NeighborsAsList.add(on.GetObject());
        ArrayList<RatingsWatcher<Movie>> NeighborsAsList1 = new ArrayList<>();
        for (ObjectScore<RatingsWatcher<Movie>> on : neighbors1)
            NeighborsAsList1.add(on.GetObject());

        assertFalse(NeighborsAsList.contains(ListOfUsers.get(14)) && NeighborsAsList1.contains(ListOfUsers.get(14)));
    }

    @Test
    void getNewNeighbors04(){

        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(0), 6);
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> testList = new ArrayList<>(neighbors);
        neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(0), 6);
        ArrayList<RatingsWatcher<Movie>> testlistaslist = new ArrayList<>();
        for(ObjectScore<RatingsWatcher<Movie>> os : testList)
            testlistaslist.add(os.GetObject());

        ArrayList<RatingsWatcher<Movie>> neighborsaslist = new ArrayList<>();
        for(ObjectScore<RatingsWatcher<Movie>> os : testList)
            neighborsaslist.add(os.GetObject());

        assertTrue(testlistaslist.containsAll(neighborsaslist));
    }

    @Test
    void getNewNeighbors05() {
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(0), 6);
        assertEquals(6, ListOfUsers.get(0).GetNeighborIDs().size());
    }

    @Test
    void getNeighborList() {
        ArrayList<ObjectScore<RatingsWatcher<Movie>>> neighbors = neighbor_manager.GetNewNeighbors(ListOfUsers.get(0), 6);

        assertEquals(neighbors.size(), neighbor_manager.GetNeighborList(ListOfUsers.get(0)).size());
    }
}