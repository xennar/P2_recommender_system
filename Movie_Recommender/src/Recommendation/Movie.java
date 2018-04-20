package Recommendation;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

public class Movie extends Recommendation {
    private Set<String> Tags;
    private ArrayList<Double> Ratings;
    private double Average_Rating;

    public Movie(int ProductID, String ProductName) {
        super(ProductID, ProductName);
    }

    Set<String> GetTags(){
        return Tags;
    }

    ArrayList<Double> GetRatings(){
        return Ratings;
    }

    void AddRating(double rating){
        Ratings.add(rating);
    }

    double GetAverage_Rating(){
        for(Double d : Ratings)
            Average_Rating += d;
        Average_Rating /= Ratings.size();
        return Average_Rating;
    }
}
