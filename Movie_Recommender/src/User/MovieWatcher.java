package User;

import Recommendation.Movie;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovieWatcher extends User {
    private ArrayList<Movie> RatedProducts;
    private Map<Integer, Double> UserRatings;
    private ArrayList<User> Neighbors;
    private ArrayList<Movie> IgnoreList;
    //map for movieID


    public ArrayList<Movie> GetRatedProducts(){
        return RatedProducts;
    }

    public double GetProductRating(Integer ProductID){
        return UserRatings.get(ProductID);
    }

    public void AddNewRatedProduct(Movie NewMovie, double rating){
        RatedProducts.add(NewMovie);
        UserRatings.put(NewMovie.GetProductID(), rating);
    }



    public ArrayList<User> GetNeighbors(){
        return Neighbors;
    }


    public void UpdateNeighbors(){

    }
}
