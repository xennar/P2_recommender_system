package RatingsWatcher;

import Framework.Basic_Characteristics;
import Framework.User;
import Managers.Session_Manager;
import Managers.User_Manager;
import Movie.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;


public class RatingsWatcher<T extends Basic_Characteristics> extends User {
    private HashMap<T, Double> UserRatings;
    private ArrayList<Integer> NeighborsIDs;
    private ArrayList<Integer> IgnoreIDs;

    public RatingsWatcher(int UserID, String password, String Neighbors, String Ignores) {
        this(UserID, password, Neighbors);

        this.IgnoreIDs = new ArrayList<>();
        ArrayList<String> IgnoresString = (ArrayList<String>) Arrays.stream(Ignores.split("\\|")).collect(Collectors.toList());
        for (String n : IgnoresString)
            IgnoreIDs.add(Integer.valueOf(n));
    }

    public RatingsWatcher(int UserID, String password, String Neighbors) {
        this(UserID, password);

        NeighborsIDs = new ArrayList<>();
        ArrayList<String> NeighborsString = (ArrayList<String>) Arrays.stream(Neighbors.split("\\|")).collect(Collectors.toList());
        for (String n : NeighborsString)
            NeighborsIDs.add(Integer.valueOf(n));
    }

    public RatingsWatcher(int UserID, String password) {
        super(UserID, password);

        UserRatings = new HashMap<>();
    }

    public ArrayList<T> GetRatedProducts() {
        return new ArrayList<>(UserRatings.keySet());
    }

    public double GetProductRating(T Product) {
        return UserRatings.get(Product);
    }

    public ArrayList<Integer> GetIgnoreIDs() {
        return IgnoreIDs;
    }

    public void AddNewRatedProductDuringFileReader(T NewProduct, double rating) {
        UserRatings.put(NewProduct, rating);
    }
    public void AddNewRatedProductDuringSession(T NewProduct, double rating){
        UserRatings.put(NewProduct, rating);
        Session_Manager.addNewSessionRatings(this.GetID() + "," + NewProduct.GetID() + "," + rating);
    }



    public ArrayList<Integer> GetNeighborIDs() {
        return NeighborsIDs;
    }

    public double GetUsersAverageScore() {
        double Average = 0;
        for (T t : GetRatedProducts()) {
            Average += GetProductRating(t);
        }
        Average = Average / GetRatedProducts().size();
        return Average;
    }
}
