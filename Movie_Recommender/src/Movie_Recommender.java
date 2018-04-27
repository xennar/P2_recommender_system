import Framework.*;
import Managers.*;
import Movie.*;
import RatingsWatcher.*;

import java.io.IOException;
import java.util.ArrayList;


public class Movie_Recommender {
    public static void main(String[] args){

        //TODO Create Managers
        ArrayList<Movie> test = new ArrayList<>();
        RatingsReader reader = new RatingsReader(); //TODO Rename either this or the method
        try{
        reader.ProductReader(test);}catch (IOException e){e.getMessage();}

        ArrayList<RatingsWatcher> test2 = new ArrayList<>();
        reader.UserReader(test2);

    }
}
