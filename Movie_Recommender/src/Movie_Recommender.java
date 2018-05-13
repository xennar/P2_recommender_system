import Framework.FileReader;
import Managers.Product_Manager;
import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;


public class Movie_Recommender {
    public static void main(String[] args) {

        FileReader filereader = new FileReader();
        User_Manager user_manager = new User_Manager(filereader);
        Product_Manager product_manager = new Product_Manager(filereader);

        ArrayList<RatingsWatcher<Movie>> ListOfUsers = user_manager.GetListOfUsers();
        ArrayList<Movie> ListOfMovies = product_manager.GetProductList();

        filereader.ReadRatings(ListOfUsers, ListOfMovies);


    }
}
