package DatabaseCreator;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMakerTest {

    @Test
    //Tests if the last written user in the file is equals to the ID 671
    void makeUsersFile() {
        UserMaker userMaker = new UserMaker();
        try{
        userMaker.MakeUsersFile(671);}catch(IOException e){e.printStackTrace();}
        Path src = Paths.get("src\\DatabaseCreator\\ratings.csv");
        int userID = 0;
        if (Files.exists(src)) {
            try {
                BufferedReader reader = Files.newBufferedReader(src);
                String line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] line_parts = line.split(",");
                    userID = Integer.valueOf(line_parts[0]);
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
        assertEquals(671, userID);
    }
}