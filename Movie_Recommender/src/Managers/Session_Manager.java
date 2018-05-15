package Managers;

import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;

public class Session_Manager {
    static private ArrayList<String> changedRatings = new ArrayList<>();
    static private ArrayList<Movie> newlyAddedProducts = new ArrayList<>();

    static public void addNewSessionProducts(Movie newProduct) {
        newlyAddedProducts.add(newProduct);
    }

    static public void addNewSessionRatings(String rating){
        changedRatings.add(rating);
    }

    public ArrayList<String> getChangedRatings() {
        return changedRatings;
    }

    public ArrayList<Movie> getNewlyAddedProducts() {
        return newlyAddedProducts;
    }
}
