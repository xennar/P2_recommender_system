package DatabaseCreator;

import java.io.IOException;

//This class and the two it initializes creates the database that the program needs the first time it is started.
//This class is therefore used before first program start.
public class DatabaseCreator {
    public static void main(String[] argc) {
        RatingsRewriter ratingsRewriter = new RatingsRewriter();
        UserMaker usermaker = new UserMaker();

        //The file ratings.csv has the timestamp removed.
        try {
            ratingsRewriter.RewriteRatings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //The empty Users.csv is filled with a basic series of UserIDs and passwords that match.
        int NumberOfUsers = ratingsRewriter.getNumberOfUsers();
        try{usermaker.MakeUsersFile(NumberOfUsers);}catch(IOException e){
            e.printStackTrace();
        }
    }
}
