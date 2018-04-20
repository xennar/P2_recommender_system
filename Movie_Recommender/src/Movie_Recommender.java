import Recommendation.Movie;
import Recommendation.Recommendation;
import User.User;


import java.util.HashSet;
import java.util.Set;

public class Movie_Recommender {
    public static void main(String[] args){
        Set<Movie> tester = new HashSet<>();
        Movie testMovie = new Movie(35, "Hi");
        tester.add(testMovie);
        System.out.println(tester);
    }
}
