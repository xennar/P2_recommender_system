import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InfoGatherer {
    public static void main(String[] argc) throws IOException {

        Path src = Paths.get("ratings.csv");
        Path dest = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\Database\\adjratings.csv");
        if(Files.exists(src) && Files.exists(dest)) {
            System.out.println("Got here");
            try{
                BufferedReader reader = Files.newBufferedReader(src);
                BufferedWriter fileWriter = Files.newBufferedWriter(dest);
                String line;
                String[] line_parts;
                line = reader.readLine();
                fileWriter.write(line + '\n');
                while((line = reader.readLine()) != null){
                    line_parts = line.split(",");
                    fileWriter.write(line_parts[0] + ',' + line_parts[1] + ',' + line_parts[2] + '\n');
                }
                reader.close();
                fileWriter.close();
            }catch(IOException e){System.out.println("Error: Files not found: " + e.getMessage());}
        }
    }
}

