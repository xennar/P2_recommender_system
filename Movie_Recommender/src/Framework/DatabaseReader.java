package Framework;

import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//This class is the Readers which read the data in the three .csv files in the Database directory.
//Each method reads one specific file.
public class DatabaseReader {
    public DatabaseReader() {
    }

    //This method reads movies.csv, and adds the information to Movie objects which are stored on the ArrayList that is returned.
    public ArrayList<Movie> ReadProducts(String path) {

        ArrayList<Movie> ListOfMovies = new ArrayList<Movie>();

        Path moviepath = Paths.get(path);

        //The method checks if it can open the specific path, and then opens it.
        if (Files.exists(moviepath)) {
            try {
                BufferedReader MovieReader = Files.newBufferedReader(moviepath);

                //The first line is read beforehand because the column description is not needed.
                MovieReader.readLine();
                String line;

                //Each line is read, as long as there are lines, split up, made into a Movie object, and added to the List.
                //The first part is the movieID, the second part is the Movie title and the last part are the movie's tags.
                while ((line = MovieReader.readLine()) != null) {
                    String[] line_parts = line.split(",");
                    try {
                        Movie On_Line;
                        int ID = Integer.valueOf(line_parts[0]);
                        if (line_parts.length > 4){
                            String combinedName = "";
                            for (int i = 1; i < line_parts.length - 1; i++){
                                combinedName += line_parts[i];
                            }
                            On_Line = new Movie(ID, combinedName, line_parts[line_parts.length - 1]);
                            ListOfMovies.add(On_Line);
                        }
                        else if (line_parts.length == 4){
                            String[] splitLine = line_parts[2].split(" ");
                            String combinedName = splitLine[1] + " " + line_parts[1] + " " +  line_parts[2].substring(splitLine[1].length() + 1);
                            On_Line = new Movie(ID, combinedName, line_parts[3]);
                            ListOfMovies.add(On_Line);
                        }
                        else if (line_parts.length == 3){
                            On_Line = new Movie(ID, line_parts[1], line_parts[2]);
                            ListOfMovies.add(On_Line);
                        }

                    } catch (Exception ignore) {

                    }
                }

                MovieReader.close();
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        }
        return ListOfMovies;
    }

    //This method reads the file Users.csv, and translates the info into user profiles.
    public ArrayList<RatingsWatcher<Movie>> ReadUsers(String path) {
        ArrayList<RatingsWatcher<Movie>> ListOfUsers = new ArrayList<>();
        Path userpath = Paths.get(path);

        //As in Readproducts, the method checks if it can open the file, and reads the first line, since it is unnecessary.
        if (Files.exists(userpath)) {
            try {
                BufferedReader MovieReader = Files.newBufferedReader(userpath);
                MovieReader.readLine();
                String line;

                //The reader reads as long as there are lines to read.
                //Each line is split into UserID, password, NeighborIDs and MovieIDs for movies to ignore.
                //Not all movies have NeighborIDs or IgnoreIDs, so the construction of RatingsWatcher objects are done
                //with three different constructors. It is, however, not possible to have ignoreIDs, without having neighbors.
                while ((line = MovieReader.readLine()) != null) {
                    try {
                        String[] line_parts = line.split(",");
                        int ID = Integer.valueOf(line_parts[0]);
                        RatingsWatcher<Movie> On_Line;
                        if (line_parts.length == 4)
                            On_Line = new RatingsWatcher<Movie>(ID, line_parts[1], line_parts[2], line_parts[3]);
                        else if (line_parts.length == 3)
                            On_Line = new RatingsWatcher<Movie>(ID, line_parts[1], line_parts[2]);
                        else
                            On_Line = new RatingsWatcher<Movie>(ID, line_parts[1]);

                        ListOfUsers.add(On_Line);
                    }catch(Exception ignore){

                    }

                }
                MovieReader.close();
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        }
        return ListOfUsers;
    }

    //The last method reads the ratings found in adjratings.csv, and adds the information to both the RatingsWatchers and the Movie objects.
    //As in the previous two methods, the first line is read beforehand.
    public void ReadRatings(ArrayList<RatingsWatcher<Movie>> ListOfUsers, ArrayList<Movie> ListOfMovies, String path) {
        Path ratingsPath = Paths.get(path);
        if (Files.exists(ratingsPath)) {
            try {
                BufferedReader ratingsreader = Files.newBufferedReader(ratingsPath);
                String line = ratingsreader.readLine();

                //Each line contains the users ID, the Movies ID and the score.
                //The "current"-user is set to the first from the list, because the ratings are sorted by user.
                int userID = 0, productID = 0;
                double rating = 0;
                RatingsWatcher<Movie> current = ListOfUsers.get(0);

                //Each part of the split string are converted into integers or doubles.
                while ((line = ratingsreader.readLine()) != null) {

                    try {
                        String[] line_part = line.split(",");
                        userID = Integer.valueOf(line_part[0]);
                        productID = Integer.valueOf(line_part[1]);
                        rating = Double.valueOf(line_part[2]);

                        //Because all of one users ratings come at once, it is more economical to keep the current variable
                        //between lines, until the next user is reached. The GetUserFromID and GetProductFromID returns the
                        //object of the given class via the ID.
                        if (userID != current.GetID())
                            current = GetUserFromID(userID, ListOfUsers);
                        Movie rated = GetProductFromID(productID, ListOfMovies);
                        rated.AddRating(rating);
                        current.AddNewRatedProductDuringFileReader(rated, rating);
                    } catch (Exception ignore) {


                    }
                }
                ratingsreader.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //This method returns the User who has the ID by going through the list 'til the index is found.
    RatingsWatcher<Movie> GetUserFromID(int ID, ArrayList<RatingsWatcher<Movie>> ListOfUsers) {
        int i = 0;
        while (i < ListOfUsers.size() && ID != ListOfUsers.get(i).GetID())
            i++;
        return ListOfUsers.get(i);
    }

    //Works the same way as GetUserFromID
    Movie GetProductFromID(int ID, ArrayList<Movie> ListOfObject) {
        int i = 0;
        while (ID != ListOfObject.get(i).GetID())
            i++;
        return ListOfObject.get(i);
    }
}