package DatabaseCreator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//The main method of this class makes the basic profiles of all 671 original users.
class UserMaker {

    //The number Of users are counted in RatingsRewriter.
    void MakeUsersFile(int NumberOfUsers) throws IOException {
        Path src = Paths.get("src\\Database\\Users.csv");
        if (Files.exists(src)) {
            try {

                //The first line is written manually
                BufferedWriter Usermaker = Files.newBufferedWriter(src);
                String line = "userID,password,neighborID,ignoreID\n";
                Usermaker.write(line);

                //Afterwards, all lines are composed of an ID and and identical password.
                for (int i = 1; i <= NumberOfUsers; i++) {
                    Usermaker.write(Integer.toString(i));
                    Usermaker.write(',');
                    Usermaker.write(Integer.toString(i));
                    Usermaker.write(",,\n");
                }

                //Since it is a buffered writer, it needs to be closed to ensure that the writing is not terminated prematurely.
                Usermaker.close();
            } catch (IOException e) {
                System.out.println("Error: File not found: " + e.getMessage());
                throw e;
            }
        }
    }
}
