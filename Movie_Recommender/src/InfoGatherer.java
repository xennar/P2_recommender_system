import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//package p2;
public class InfoGatherer {

    public static void MakeRatings() throws Exception {

        File RatingsFile = new File("Movie_Recommender\\src\\ratings.csv");
        Scanner scan = new Scanner(RatingsFile);
        FileWriter fileWriter = new FileWriter("adjratings.csv");
        PrintWriter writer = new PrintWriter(fileWriter);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] adjRatings = line.split(",");
            writer.printf(adjRatings[0] + ", " + adjRatings[1] + ", " + adjRatings[2] + "\n");
        }


    }
}

