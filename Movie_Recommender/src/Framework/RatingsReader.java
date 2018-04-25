package Framework;

import Managers.*;
import Movie.*;
import RatingsWatcher.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class RatingsReader {

    public static void main(String[] args) throws IOException {

        Path moviepath = Paths.get("Movie_Recommender\\src\\movies.csv");
        if (Files.exists(moviepath)) {
            try{
                BufferedReader MovieReader = Files.newBufferedReader(moviepath);
                String line;
                while((line  = MovieReader.readLine()) != null) {
                    String[] line_parts = line.split(",");
                    System.out.println(line_parts[0] + "," + line_parts[1] + "," + line_parts[2]);
                }
                MovieReader.close();
            }catch (IOException e){System.out.println("Unable to read file");}
        }

            /*while (scanMovies.hasNextLine()) {
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

            }*/
        }
    }