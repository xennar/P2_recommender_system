import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public void ReadToMovies() throws Exception {

        File movies = new File("Movie_Recommender\\src\\movies.csv");
        Scanner scanMovies = new Scanner(movies);


        //String[] moviesTemp = null;
        ArrayList<String[]> movieTempList = new ArrayList<>();
        while (scanMovies.hasNextLine()) {
            String tempLine = scanMovies.nextLine();
            String[] temp = tempLine.split(",");
            if (temp.length > 3) {
                String combineMovie = temp[1] + "," + temp[2];
                String[] newtemp = new String[]{temp[0], combineMovie, temp[3]};
                System.out.println(newtemp[0] + newtemp[1] + newtemp[2]);
                movieTempList.add(newtemp);
            } else
                movieTempList.add(temp);
            //for(String[] item : movieTempList) {
            //  int i = 0;
            //while (i < item.length){
            //System.out.println(item[i]);
            //i++;
        }
    }

}

