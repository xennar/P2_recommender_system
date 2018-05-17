package Framework;

import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResultsWriter {

    static public void WriteProductData(ArrayList<Movie> currentSessionProducts, String path) {
        if (!currentSessionProducts.isEmpty()) {
            Path dest = Paths.get(path);
            int count = 0;

            if (Files.exists(dest)) {
                try {
                    FileWriter MovieWriter = new FileWriter(path, true);
                    for (Movie m : currentSessionProducts) {
                        StringBuilder combinedtags = new StringBuilder();
                        //Goes through all the tags and appends them with a | between
                        for (String s : m.GetTags()) {
                            count++;
                            combinedtags.append(s);
                            if (count < m.GetTags().size()) {
                                combinedtags.append("|");
                            }
                        }
//                        loops through the products added in the current session and writes them at the end of the movies.csv file
                        MovieWriter.append(String.valueOf(m.GetID())).append(",").append(m.GetString()).append(",").append(combinedtags.toString()).append("\n");
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
            ArrayList<String> toRemove = new ArrayList<>();
            int location = 0;


            if (Files.exists(dest)) {
                try {
                    String line;
                    BufferedReader ratingsReader = Files.newBufferedReader(dest);
                    //Reads the entire ratings file into a list to be modified
                    while ((line = ratingsReader.readLine()) != null) {
                        ratings.add(line);
                    }
                    ratingsReader.close();

                    for (String s : currentSessionRatingsData) {
                        String[] splitNewData = s.split(",");
                        for (String oldData : ratings) {
                            String[] splitOldData = oldData.split(",");
                            if (s.equals(oldData)){
                                //if the Two strings are equal that means the rating is to be removed
                                //this is only for testing as the the GUI the user cannot give the same
                                //rating to an already rated product only change it
                                toRemove.add(oldData);
                            }else if (splitOldData[0].equals(splitNewData[0]) && splitOldData[1].equals(splitNewData[1])) {
                                //If the UserID and MovieID from the new rating matches that of an existing rating
                                //Then that rating is changed to the new rating
                                location = ratings.indexOf(oldData);
                                ratings.set(location, s);
                            }
                        }
                        if (!ratings.contains(s)) {
                            //if the new rating does not exist it is added to the list to be written to file
                            ratings.add(s);
                        }
                    }
                    FileWriter ratingsWriter = new FileWriter(path, false);
                    ratings.removeAll(toRemove);
                    //Removes all the ratings in the toRemove arraylist and the writes the ratings arraylist to file
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
            //Reads the entire list of users into an arraylist for editing
            if (Files.exists(dest)) {
                try {
                    FileWriter userWriter = new FileWriter(path, false);
                    for (RatingsWatcher<Movie> newData : currentSessionUserData) {
                        for (RatingsWatcher<Movie> oldData : currentUsers) {
                            if (newData.GetID() == oldData.GetID()) {
                                /*Loops through each of the changed users and compares it to every user before any changes
                                 * if the IDs match then the line of the ID is replaced with the new User Data*/
                                currentUsers.set(oldData.GetID() - 1, newData);
                            }
                        }
                        if (!currentUsers.contains(newData)){
                            currentUsers.add(newData);
                        }
                    }
                    userWriter.write("userID,password,neighborID,ignoreID\n");
                    //appends all of the NeighborID together separated by a |
                    for (RatingsWatcher<Movie> newUserData : currentUsers) {
                        StringBuilder neighborID = new StringBuilder();
                        StringBuilder ignoreID = new StringBuilder();
                        int count = 0;
                        for (Integer n : newUserData.GetNeighborIDs()) {
                            count++;
                            neighborID.append(n);
                            if (count < newUserData.GetNeighborIDs().size()) {
                                neighborID.append("|");
                            }
                        }
                        count = 0;
                        //appends all of the NeighborID together separated by a |
                        for (Integer ignore : newUserData.GetIgnoreIDs()) {
                            count++;
                            ignoreID.append(ignore);
                            if (count < newUserData.GetIgnoreIDs().size()) {
                                ignoreID.append("|");
                            }

                        }
                        //Writes the new information to file
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
