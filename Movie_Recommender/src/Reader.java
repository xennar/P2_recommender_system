import Recommendation.Movie;
import Recommendation.Recommendation;
import User.MovieWatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//package user;
//package recommendation;

public class Reader {
    MovieWatcher Movielist = new MovieWatcher();
    Recommendation MovieInfo = new Recommendation(7, "hej mor");

    public void ReadToMovies() throws Exception {

        File movies = new File("Movie_Recommender\\src\\movies.csv");
        Scanner scanMovies = new Scanner(movies);


        while (scanMovies.hasNextLine()) {
            String tempLine = scanMovies.nextLine();
            String[] temp = tempLine.split(",");

            if (!"movieId".equals(temp[0]) && temp.length > 3) {
                String combineMovie = temp[1] + "," + temp[2];
                String[] newtemp = new String[]{temp[0], combineMovie, temp[3]};
                //add newtemp[0] to productID, add newtemp[1] to name, add newtemp[2] to list<strings> genre
                String b;
                b = newtemp[0];
                //System.out.println(b);
                int a = Integer.parseInt(b);
                Movie MovieInfo = new Movie(a, newtemp[1]);
                //Rember Genre need to placed once the class and contructor has been made
                //Rember Genre need to placed once the class and contructor has been made
                //Rember Genre need to placed once the class and contructor has been made

            } else if (!"movieId".equals(temp[0]) && temp.length <= 3) {
                String b;
                b = temp[0];
                //System.out.println(temp[0]);
                int a = Integer.parseInt(b);
                Movie MovieInfo = new Movie(a, temp[1]);
                //Rember Genre need to placed once the class and contructor has been made
                //Rember Genre need to placed once the class and contructor has been made
                //Rember Genre need to placed once the class and contructor has been made
            }

        }
    }

    public void ReadToRatings() {

        File adjustedRatings = new File("Movie_Recommender\\src\\adjratings.csv");
        Scanner scanRatings = new Scanner("adjustedRatings.csv");

        while (scanRatings.hasNextLine()) {
            String tempLine = scanRatings.nextLine();
            String[] temp = tempLine.split(",");

            if (!"movieId".equals(temp[0]) && temp.length > 3) {
                String combineMovie = temp[1] + "," + temp[2];
                String[] newtemp = new String[]{temp[0], combineMovie, temp[3]};
                //add newtemp[0] to productID, add newtemp[1] to name, add newtemp[2] to list<strings> genre
                String b;
                b = newtemp[0];
                //System.out.println(b);
                int a = Integer.parseInt(b);
                Movie MovieInfo = new Movie(a, newtemp[1]);
                //Rember Genre need to placed once the class and contructor has been made
                //Rember Genre need to placed once the class and contructor has been made
                //Rember Genre need to placed once the class and contructor has been made

            } else if (!"movieId".equals(temp[0]) && temp.length <= 3) {
                String b;
                b = temp[0];
                //System.out.println(temp[0]);
                int a = Integer.parseInt(b);
                Movie MovieInfo = new Movie(a, temp[1]);
            }
        }


    }
}


