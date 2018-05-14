package Framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

public class ResultsWriter {

    public void WriteProductData(ArrayList<Movie> currentSessionProducts) {
        if (!currentSessionProducts.isEmpty()){
            Path dest = Paths.get("src\\Database\\movies.csv");

            if (Files.exists(dest)) {
                try {
                    FileWriter MovieWriter = new FileWriter("src\\Database\\movies.csv", true);
                    for (Movie m : currentSessionProducts) {
                        MovieWriter.append(String.valueOf(m.GetID())).append(",").append(m.GetString()).append(",").append(String.valueOf(m.GetTags()));
                    }
                    MovieWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }

    public void WriteRatingsData(ArrayList<RatingsWatcher<Movie>> currentSessionRatingsData) {
        if (!currentSessionRatingsData.isEmpty()) {
            Path dest = Paths.get("src\\Database\\adjratings.csv");

            if (Files.exists(dest)) {
                try {
                    FileWriter ratingsWriter = new FileWriter("src\\Database\\adjratings.csv", true);
                    for (RatingsWatcher<Movie> u : currentSessionRatingsData) {
                        for (Movie m : u.GetRatedProducts())
                            ratingsWriter.append(String.valueOf(u.GetID())).append(",").append(String.valueOf(m.GetID())).append(",").append(String.valueOf(u.GetProductRating(m)));
                    }
                    ratingsWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }

    public void WriteUserData(ArrayList<RatingsWatcher<Movie>> currentSessionUserData){
        if (!currentSessionUserData.isEmpty()) {
            Path dest = Paths.get("src\\Database\\Users.csv");
            ArrayList<RatingsWatcher<Movie>> currentUsers = new FileReader().ReadUsers();
            if (Files.exists(dest)) {
                try {
                    FileWriter userWriter = new FileWriter("src\\Database\\Users.csv", true);
                    for (RatingsWatcher<Movie> newData : currentSessionUserData) {
                        for (RatingsWatcher<Movie> oldData : currentUsers)
                            if (newData.GetID() == oldData.GetID()) {
                                currentUsers.set(oldData.GetID(), newData);
                            }
                    }
                    for (RatingsWatcher<Movie> newUserData : currentUsers){
                        userWriter.write(newUserData.GetID() + ',' + newUserData.GetString() + ',' + newUserData.GetNeighborIDs() + ',' + newUserData.GetNeighborIDs() + '\n');
                    }
                    userWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }
}
