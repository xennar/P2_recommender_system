package Framework;

import Managers.*;
import Movie.*;
import RatingsWatcher.*;
import Framework.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class RatingsReader {

    public void ProductReader(ArrayList<Movie> ListOfMovies) throws IOException {

        Path moviepath = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\Database\\movies.csv");
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

    }

    public void UserReader(ArrayList<RatingsWatcher> ListOfUsers){
        Path userpath = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\Database\\Users.csv");
        if(Files.exists(userpath)){
            try {
                BufferedReader MovieReader = Files.newBufferedReader(userpath);
                MovieReader.readLine();

                String line;
                while ((line = MovieReader.readLine()) != null) {
                    String[] line_parts = line.split(",");
                    int ID = Integer.valueOf(line_parts[0]);
                    RatingsWatcher<Movie> On_Line;
                    if(line_parts.length == 4)
                        On_Line = new RatingsWatcher<Movie>(ID, line_parts[1], line_parts[2], line_parts[3]);
                    else if(line_parts.length == 3)
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
    }

    public void ReadRatings(ArrayList<Movie> ListOfMovies, ArrayList<RatingsWatcher> ListOfUsers){
        Path ratingsPath = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\Database\\adjratings.csv");
        if(Files.exists(ratingsPath)){
            try {
                BufferedReader ratingsreader = Files.newBufferedReader(ratingsPath);

                String line = ratingsreader.readLine();
                int userID, productID;
                double rating;
                while((line = ratingsreader.readLine()) != null){
                    String[] line_part = line.split(",");
                    userID = Integer.valueOf(line_part[0]);
                    productID = Integer.valueOf(line_part[1]);
                    rating = Double.valueOf(line_part[2]);

                    //TODO ADD CALLS TO MANAGERS WHEN THEY ARE DONE SO THAT INFORMATION CAN BE PROCESSED
                }
                ratingsreader.close();

            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}