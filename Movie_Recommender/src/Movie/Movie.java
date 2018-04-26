package Movie;

import Framework.Recommendation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Movie extends Recommendation {
    private ArrayList<String> Tags;
    private ArrayList<Double> Ratings;
    private double Average_Rating;

    //TODO FINALIZE AFTER MANAGERS HAS BEEN MADE

    public Movie(int ProductID, String ProductName, String Tags) {
        super(ProductID, ProductName);

        this.Tags = (ArrayList<String>)Arrays.stream(Tags.split("\\|")).collect(Collectors.toList());
    }

    public ArrayList<String> GetTags(){
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
