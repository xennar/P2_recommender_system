package Movie;

import Framework.Recommendation;

import java.util.ArrayList;

public class Movie extends Recommendation {
    private ArrayList<String> Tags;
    private ArrayList<Double> Ratings;
    private double Average_Rating;

    public Movie(int ProductID, String ProductName) {
        super(ProductID, ProductName);
    }

    ArrayList<String> GetTags(){
        return Tags;
    }

    ArrayList<Double> GetRatings(){
        return Ratings;
    }

    void AddRating(double rating){
        Ratings.add(rating);
    }

    double GetAverage_Rating(){
        Average_Rating = 0;
        for(Double d : Ratings)
            Average_Rating += d;
        Average_Rating /= Ratings.size();
        return Average_Rating;
    }
}
