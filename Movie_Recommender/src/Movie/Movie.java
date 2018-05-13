package Movie;

import Framework.Recommendation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Movie extends Recommendation {
    private ArrayList<String> Tags;
    private ArrayList<Double> Ratings;

    public Movie(int ProductID, String ProductName, String Tags) {
        super(ProductID, ProductName);
        Ratings = new ArrayList<>();
        this.Tags = (ArrayList<String>) Arrays.stream(Tags.split("\\|")).collect(Collectors.toList());
    }

    public ArrayList<String> GetTags() {
        return Tags;
    }

    public ArrayList<Double> GetRatings() {
        return Ratings;
    }

    public void AddRating(double rating) {
        Ratings.add(rating);
    }

    public double GetAverage_Rating() {
        double Average;
        Average = 0;
        for (Double d : Ratings)
            Average += d;
        Average /= Ratings.size();
        return Average;
    }
}
