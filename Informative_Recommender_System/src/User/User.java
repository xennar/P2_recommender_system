package User;
import Movie.*;

import java.util.ArrayList;
import java.util.Map;

public class User {
    int UserID;
    String passowrd;
    ArrayList<Movie> WatchedMovies;
    Map<Integer, Double> UserRatings;
    ArrayList<Movie> IgnoredMovies;
    ArrayList<User> neighbors;

}
