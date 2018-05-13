package Framework;

import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileReader {

    public ArrayList<Movie> ReadProducts(){

        ArrayList<Movie> ListOfMovies = new ArrayList<Movie>();

        Path moviepath = Paths.get("src/Database/movies.csv");
        if (Files.exists(moviepath)) {
            try {
                BufferedReader MovieReader = Files.newBufferedReader(moviepath);
                MovieReader.readLine();

                String line;
                while ((line = MovieReader.readLine()) != null) {
                    String[] line_parts = line.split(",");
                    int ID = Integer.valueOf(line_parts[0]);

                    Movie On_Line = new Movie(ID, line_parts[1], line_parts[2]);
                    ListOfMovies.add(On_Line);
                }
                MovieReader.close();
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        }
        return ListOfMovies;
    }

    public ArrayList<RatingsWatcher<Movie>> ReadUsers() {
        ArrayList<RatingsWatcher<Movie>> ListOfUsers = new ArrayList<>();

        Path userpath = Paths.get("src/Database/Users.csv");
        if (Files.exists(userpath)) {
            try {
                BufferedReader MovieReader = Files.newBufferedReader(userpath);
                MovieReader.readLine();

                String line;
                while ((line = MovieReader.readLine()) != null) {
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

                }
                MovieReader.close();
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        }
        return ListOfUsers;
    }

    public void ReadRatings(ArrayList<RatingsWatcher<Movie>> ListOfUsers, ArrayList<Movie> ListOfMovies) {
        Path ratingsPath = Paths.get("src/Database/adjratings.csv");
        if (Files.exists(ratingsPath)) {
            try {
                BufferedReader ratingsreader = Files.newBufferedReader(ratingsPath);

                String line = ratingsreader.readLine();
                int userID = 0, productID = 0;
                double rating = 0;
                RatingsWatcher<Movie> current = ListOfUsers.get(0);
                while ((line = ratingsreader.readLine()) != null) {
                    String[] line_part = line.split(",");
                    userID = Integer.valueOf(line_part[0]);
                    productID = Integer.valueOf(line_part[1]);
                    rating = Double.valueOf(line_part[2]);

                    if (userID != current.GetID())
                        current = GetUserFromID(userID, ListOfUsers);
                    Movie rated = GetProductFromID(productID, ListOfMovies);
                    rated.AddRating(rating);
                    current.AddNewRatedProduct(rated, rating);

                }
                ratingsreader.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private RatingsWatcher<Movie> GetUserFromID(int ID, ArrayList<RatingsWatcher<Movie>> ListOfUsers) {
        int i = 0;
        while (ID != ListOfUsers.get(i).GetID())
            i++;
        return ListOfUsers.get(i);
    }

    private Movie GetProductFromID(int ID, ArrayList<Movie> ListOfObject) {
        int i = 0;
        while (ID != ListOfObject.get(i).GetID())
            i++;
        return ListOfObject.get(i);
    }
}