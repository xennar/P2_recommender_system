package Movie;

import Framework.Recommendation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

//This class symbolizes a Move, and extends Recommendation. Since a movie has descriptors, tags are included,
//documentary, horror, comedy etc.
public class Movie extends Recommendation {
    private ArrayList<String> Tags;
    private ArrayList<Double> Ratings;

    //Since the tags are input as a single string with a separator, they need to be split.
    public Movie(int ProductID, String ProductName, String Tags) {
        super(ProductID, ProductName);
        Ratings = new ArrayList<>();
        this.Tags = (ArrayList<String>) Arrays.stream(Tags.split("\\|")).collect(Collectors.toList());
    }

    //The next two methods are basic getters.
    public ArrayList<String> GetTags() {
        return Tags;
    }

    public ArrayList<Double> GetRatings() {
        return Ratings;
    }

    //This method adds a user's rating of the movie to the list.
    public void AddRating(double rating) {
        Ratings.add(rating);
    }

    //The average score of the movie is calculated from the sum of all ratings given.
    public double GetAverage_Rating() {
        double Average;
        Average = 0;
        for (Double d : Ratings)
            Average += d;
        Average /= Ratings.size();
        return Average;
    }
}
