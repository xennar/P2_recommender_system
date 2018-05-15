package Framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Managers.Product_Manager;
import Managers.User_Manager;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

public class ResultsWriter {

    public void WriteProductData(ArrayList<Movie> currentSessionProducts, String path) {
        if (!currentSessionProducts.isEmpty()) {
            Path dest = Paths.get(path);
            int count = 0;

            if (Files.exists(dest)) {
                try {
                    FileWriter MovieWriter = new FileWriter(path, true);
                    for (Movie m : currentSessionProducts) {
                        String combinedtags = "";
                        for (String s: m.GetTags()){
                            count++;
                            combinedtags += s;
                            if (count < m.GetTags().size()){
                                combinedtags += "|";
                            }
                        }
//                        loops through the products added in the current session and writes them at the end of the movies.csv file
                            MovieWriter.append(String.valueOf(m.GetID())).append(",").append(m.GetString()).append(",").append(combinedtags).append("\n");
                    }
                    MovieWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }

    public void WriteRatingsData(ArrayList<RatingsWatcher<Movie>> currentSessionRatingsData, ArrayList<Integer> newRatedProductID, String  path) {
        if (!currentSessionRatingsData.isEmpty()) {
            Path dest = Paths.get(path);

            if (Files.exists(dest)) {
                try {
                    FileWriter ratingsWriter = new FileWriter(path, true);
                    for (RatingsWatcher<Movie> u : currentSessionRatingsData) {
                        for (Movie m : u.GetRatedProducts())
                            for (Integer IDtoAdd : newRatedProductID) {
                            /*Loops through each of the changed users movies, if the ID of the currently looped
                            * Movie matches the ID of the movies that are to be added then it writes the Rating
                            * at the end of adjratings.csv*/
                                if (m.GetID() == IDtoAdd) {
                                    ratingsWriter.append(String.valueOf(u.GetID())).append(",").append(String.valueOf(m.GetID())).append(",").append(String.valueOf(u.GetProductRating(m))).append("\n");
                                }
                            }
                    }
                    ratingsWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }

    public void WriteUserData(ArrayList<RatingsWatcher<Movie>> currentSessionUserData, String path) {
        if (!currentSessionUserData.isEmpty()) {
            Path dest = Paths.get(path);
            ArrayList<RatingsWatcher<Movie>> currentUsers = new FileReader().ReadUsers(path);
//            Reads the entire list of users into an arraylist for editing
            if (Files.exists(dest)) {
                try {
                    FileWriter userWriter = new FileWriter(path, false);
                    for (RatingsWatcher<Movie> newData : currentSessionUserData) {
                        for (RatingsWatcher<Movie> oldData : currentUsers)
                            if (newData.GetID() == oldData.GetID()) {
                            /*Loops through each of the changed users and compares it to every user before any changes
                            * if the IDs match then the line of the ID is replaced with the new User Data*/
                                currentUsers.set(oldData.GetID(), newData);
                            }
                    }
                    for (RatingsWatcher<Movie> newUserData : currentUsers) {
//                        overwrites Users.csv with the new edited information
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
