package RatingsWatcher;

import Framework.Basic_Characteristics;
import Framework.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class RatingsWatcher<T extends Basic_Characteristics> extends User {
    private ArrayList<T> RatedProducts;
    private Map<T, Double> UserRatings;
    private ArrayList<Integer> NeighborsIDs;
    private ArrayList<Integer> IgnoreIDs;

    public RatingsWatcher(int UserID, String password, String Neighbors, String Ignores) {
        super(UserID, password);


        this.NeighborsIDs = new ArrayList<>();
        ArrayList<String> NeighborsString = (ArrayList<String>) Arrays.stream(Neighbors.split("\\|")).collect(Collectors.toList());
        for (String n : NeighborsString)
            NeighborsIDs.add(Integer.valueOf(n));


        this.IgnoreIDs = new ArrayList<>();
        ArrayList<String> IgnoresString = (ArrayList<String>) Arrays.stream(Ignores.split("\\|")).collect(Collectors.toList());
        for (String n : IgnoresString)
            IgnoreIDs.add(Integer.valueOf(n));
        System.out.println(UserID + "," + password + "," +  NeighborsIDs.size() + "," + IgnoreIDs.size());
    }

    public RatingsWatcher(int UserID, String password, String Neighbors) {
        super(UserID, password);

        NeighborsIDs = new ArrayList<>();
        ArrayList<String> NeighborsString = (ArrayList<String>) Arrays.stream(Neighbors.split("\\|")).collect(Collectors.toList());
        for (String n : NeighborsString)
            NeighborsIDs.add(Integer.valueOf(n));

        System.out.println(UserID + "," + password + "," +  NeighborsIDs.size());
    }

    public RatingsWatcher(int UserID, String password) {
        super(UserID, password);
        System.out.println(UserID + "," + password);
    }

    public ArrayList<T> GetRatedProducts() {
        return RatedProducts;
    }

    public double GetProductRating(T ProductID) {
        return UserRatings.get(ProductID);
    }

    public void AddNewRatedProduct(T NewProduct, double rating) {
        RatedProducts.add(NewProduct);
        UserRatings.put(NewProduct, rating);
    }


/*
    public ArrayList<User> GetNeighbors(){
        return Neighbors;
    }


    public void UpdateNeighbors(){

    }*/
}
