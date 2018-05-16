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

    static public void WriteProductData(ArrayList<Movie> currentSessionProducts, String path) {
        if (!currentSessionProducts.isEmpty()) {
            Path dest = Paths.get(path);
            int count = 0;

            if (Files.exists(dest)) {
                try {
                    FileWriter MovieWriter = new FileWriter(path, true);
                    for (Movie m : currentSessionProducts) {
                        String combinedtags = "";
                        for (String s : m.GetTags()) {
                            count++;
                            combinedtags += s;
                            if (count < m.GetTags().size()) {
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

    static public void WriteRatingsData(ArrayList<String> currentSessionRatingsData, String path) {
        if (!currentSessionRatingsData.isEmpty()) {
            Path dest = Paths.get(path);
            ArrayList<String> ratings = new ArrayList<>();


            if (Files.exists(dest)) {
                try {
                    String line;
                    BufferedReader ratingsReader = Files.newBufferedReader(dest);
                    while ((line = ratingsReader.readLine()) != null) {
                        ratings.add(line);
                    }
                    ratingsReader.close();

                    /*Loops through each of the changed users movies, if the ID of the currently looped
                     * Movie matches the ID of the movies that are to be added then it writes the Rating
                     * at the end of adjratings.csv*/
                    for (String s : currentSessionRatingsData) {
                        String[] splitNewData = s.split(",");
                        for (String oldData : ratings) {
                            String[] splitOldData = oldData.split(",");
                            if (splitOldData[0].equals(splitNewData[0]) && splitOldData[1].equals(splitNewData[1])) {
                                int location = ratings.indexOf(oldData);
                                ratings.set(location, s);
                                break;
                            }
                        }
                        if (!ratings.contains(s)) {
                            ratings.add(s);
                        }
                    }
                    FileWriter ratingsWriter = new FileWriter(path, false);

                    for (String newData : ratings) {
                        ratingsWriter.write(newData + "\n");
                    }

                    ratingsWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }

    static public void WriteUserData(ArrayList<RatingsWatcher<Movie>> currentSessionUserData, String path) {
        if (!currentSessionUserData.isEmpty()) {
            Path dest = Paths.get(path);
            ArrayList<RatingsWatcher<Movie>> currentUsers = new FileReader().ReadUsers(path);
            for (RatingsWatcher<Movie> current : currentUsers) {
            }
//            Reads the entire list of users into an arraylist for editing
            if (Files.exists(dest)) {
                try {
                    FileWriter userWriter = new FileWriter(path, false);
                    for (RatingsWatcher<Movie> newData : currentSessionUserData) {
                        for (RatingsWatcher<Movie> oldData : currentUsers) {
                            if (newData.GetID() == oldData.GetID()) {
                                /*Loops through each of the changed users and compares it to every user before any changes
                                 * if the IDs match then the line of the ID is replaced with the new User Data*/
                                currentUsers.set(oldData.GetID() - 1, newData);
                                break;
                            }
                        }
                    }
                    userWriter.write("userID,password,neighborID,ignoreID\n");
                    for (RatingsWatcher<Movie> newUserData : currentUsers) {
                        StringBuilder neighborID = new StringBuilder();
                        StringBuilder ignoreID = new StringBuilder();
                        int count = 0;
//                        overwrites Users.csv with the new edited information
                        for (Integer n : newUserData.GetNeighborIDs()) {
                            count++;
                            neighborID.append(n);
                            if (count < newUserData.GetNeighborIDs().size()) {
                                neighborID.append("|");
                            }
                        }
                        count = 0;
                            for (Integer ignore : newUserData.GetIgnoreIDs()) {
                                count++;
                                ignoreID.append(ignore);
                                if (count < newUserData.GetIgnoreIDs().size()) {
                                    ignoreID.append("|");
                                }

                            }
                        userWriter.write(String.valueOf(newUserData.GetID()) + ',' + newUserData.GetString() + ',' + neighborID + ',' + ignoreID + '\n');
                    }
                    userWriter.close();
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
            }
        }
    }
}
