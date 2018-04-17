package User;
import Movie.*;

import java.util.ArrayList;
import java.util.Map;

public class User {
    private int UserID;
    private String password;
    ArrayList<Movie> WatchedMovies;
    private Map<Integer, Double> UserRatings;
    ArrayList<Movie> IgnoredMovies;
    ArrayList<User> neighbors;


    int GetUserID(){
        return UserID;
    }

    String GetUserPassword(){
        return password;
    }

    Map<Integer, Double> ViewPreviousRatings(){
        return UserRatings;
    }
}
