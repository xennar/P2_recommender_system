import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class RatingsRewriter {

    private int NumberOfUsers;

    RatingsRewriter(){
        NumberOfUsers = 0;
    }

    void RewriteRatings() throws IOException {
        Path src = Paths.get("ratings.csv");
        Path dest = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\src\\Database\\adjratings.csv");

        NumberOfUsers = 0;
        if(Files.exists(src) && Files.exists(dest)) {
            try{
                BufferedReader reader = Files.newBufferedReader(src);
                BufferedWriter fileWriter = Files.newBufferedWriter(dest);
                String line;
                String[] line_parts;
                while((line = reader.readLine()) != null){
                    line_parts = line.split(",");
                    fileWriter.write(line_parts[0] + ',' + line_parts[1] + ',' + line_parts[2] + '\n');
                    NumberOfUsers = Integer.valueOf(line_parts[0]);
                }

                reader.close();
                fileWriter.close();

            }catch(IOException e){System.out.println("Error: Files not found: " + e.getMessage());}
        }
    }

    int getNumberOfUsers() {
        return NumberOfUsers;
    }
}

