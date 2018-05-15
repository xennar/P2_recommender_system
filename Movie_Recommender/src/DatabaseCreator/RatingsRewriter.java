package DatabaseCreator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//This class removes the timestamp from the csv file ratings.csv.
class RatingsRewriter {

    private int NumberOfUsers;

    RatingsRewriter(){
        NumberOfUsers = 0;
    }


    void RewriteRatings() throws IOException {
        Path src = Paths.get("src\\DatabaseCreator\\ratings.csv");
        Path dest = Paths.get("src\\Database\\adjratings.csv");

        //The method also tracks the number of users that are in the system from the start.
        NumberOfUsers = 0;
        if(Files.exists(src) && Files.exists(dest)) {
            try{
                BufferedReader reader = Files.newBufferedReader(src);
                BufferedWriter fileWriter = Files.newBufferedWriter(dest);

                //The first line is read, split into pieces and has the piece with timestamp removed.
                String line = reader.readLine();
                String[] line_parts = line.split(",");
                fileWriter.write(line_parts[0] + ',' + line_parts[1] + ',' + line_parts[2] + '\n');

                //Each line in the file is read, split and written into the new file without the timestamp.
                while((line = reader.readLine()) != null){
                    line_parts = line.split(",");
                    fileWriter.write(line_parts[0] + ',' + line_parts[1] + ',' + line_parts[2] + '\n');

                    //The number of users are assigned each iteration until the last line has been read .
                    NumberOfUsers = Integer.valueOf(line_parts[0]);
                }

                //Since it is a buffered reader and writer, the files need to be closed, because it might not be finished
                //before the program terminates.
                reader.close();
                fileWriter.close();

            }catch(IOException e){System.out.println("Error: Files not found: " + e.getMessage());
            throw e;}
        }
    }

    //Straightforward getter.
    int getNumberOfUsers() {
        return NumberOfUsers;
    }
}

