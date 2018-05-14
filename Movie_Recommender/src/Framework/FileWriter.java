package Framework;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

public class FileWriter {

    public void WriteProductData(ArrayList<Movie> currentSessionProducts) {
        if (!currentSessionProducts.isEmpty()){
            Path dest = Paths.get("src/Database/movies.csv");

            if (Files.exists(dest)) {
                try {
                    BufferedWriter movieWriter = Files.newBufferedWriter(dest);
                    for (Movie m : currentSessionProducts) {
                        movieWriter.append(m.GetID() + "," + m.GetString() + "," + m.GetTags());
                    }
                    movieWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }

    public void WriteRatingsData(ArrayList<RatingsWatcher<Movie>> currentSessionRatingsData) {
        if (!currentSessionRatingsData.isEmpty()) {
            Path dest = Paths.get("src/Database/adjratings.csv");

            if (Files.exists(dest)) {
                try {
                    BufferedWriter ratingsWriter = Files.newBufferedWriter(dest);
                    for (RatingsWatcher<Movie> u : currentSessionRatingsData) {
                        for (Movie m : u.GetRatedProducts())
                            ratingsWriter.append(u.GetID() + "," + m.GetID() + "," + u.GetProductRating(m));
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
            Path dest = Paths.get("src/Database/Users.csv");
            ArrayList<RatingsWatcher<Movie>> currentUsers = new FileReader().ReadUsers();
            if (Files.exists(dest)) {
                try {
                    BufferedWriter userWriter = Files.newBufferedWriter(dest);
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
