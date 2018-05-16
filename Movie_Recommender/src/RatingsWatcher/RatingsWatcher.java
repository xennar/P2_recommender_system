package RatingsWatcher;

import Framework.Basic_Characteristics;
import Framework.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

//This class describes an user who rates product by ratings rather than like/dislike.
public class RatingsWatcher<T extends Basic_Characteristics> extends User {
    private HashMap<T, Double> UserRatings;
    private ArrayList<Integer> NeighborsIDs;
    private ArrayList<Integer> IgnoreIDs;

    //There are three constructors for this class: The RatingsWatcher who has both neighbors and ignores
    public RatingsWatcher(int UserID, String password, String Neighbors, String Ignores) {
        this(UserID, password, Neighbors);

        //The Ignores are split into the individual pieces

        ArrayList<String> IgnoresString = (ArrayList<String>) Arrays.stream(Ignores.split("\\|")).collect(Collectors.toList());
        for (String n : IgnoresString)
            IgnoreIDs.add(Integer.valueOf(n));
    }

    //This Constructor is for users with neighbors but not ignores
    public RatingsWatcher(int UserID, String password, String Neighbors) {
        this(UserID, password);

        //The neighbor String is split into parts and converted to ints.

        ArrayList<String> NeighborsString = (ArrayList<String>) Arrays.stream(Neighbors.split("\\|")).collect(Collectors.toList());
        for (String n : NeighborsString)
            NeighborsIDs.add(Integer.valueOf(n));
    }

    //The last constructor are for new users without any neighbors or ignores.
    public RatingsWatcher(int UserID, String password) {
        super(UserID, password);
        NeighborsIDs = new ArrayList<>();
        UserRatings = new HashMap<>();
        this.IgnoreIDs = new ArrayList<>();
    }

    //This method returns a list of all products that the user has rated.
    public ArrayList<T> GetRatedProducts() {
        return new ArrayList<>(UserRatings.keySet());
    }

    //This returns the score associated with a rated product.
    public double GetProductRating(T Product) {
        return UserRatings.get(Product);
    }

    //Basic getter
    public ArrayList<Integer> GetIgnoreIDs() {
        return IgnoreIDs;
    }

    //adds a new product to the List of rated products or changes an existing rating
    public void AddNewRatedProduct(T NewProduct, double rating) {
        if (rating >= 1 && rating <= 5) {
            UserRatings.put(NewProduct, rating);
        }
    }

    //Basic getter.
    public ArrayList<Integer> GetNeighborIDs() {
        return NeighborsIDs;
    }

    //This method calculates the user's average score given, by dividing the sum of all ratings by the number thereof.
    public double GetUsersAverageScore() {
        double Average = 0;
        for (T t : GetRatedProducts()) {
            Average += GetProductRating(t);
        }
        Average = Average / GetRatedProducts().size();
        return Average;
    }
}
