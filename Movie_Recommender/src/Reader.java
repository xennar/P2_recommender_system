import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public void ReadToMovies() throws Exception {

        File movies = new File("Movie_Recommender\\src\\movies.csv");
        Scanner scanMovies = new Scanner(movies);

        while (scanMovies.hasNextLine()) {
            String tempLine = scanMovies.nextLine();
            String[] temp = tempLine.split(",");
            if (temp.length > 3) {
                String combineMovie = temp[1] + "," + temp[2];
                String[] newtemp = new String[]{temp[0], combineMovie, temp[3]};
                //add newtemp[0] to productID, add newtemp[1] to name, add newtemp[2] to list<strings> genre
            } //else
            //add temp[0] to productID, add temp[1] to name, add temp[2] to list<strings> genre

        }
    }
}

