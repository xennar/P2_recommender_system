package DatabaseCreator;

import java.io.IOException;

public class DatabaseCreator {
    public static void main(String[] argc) {
        RatingsRewriter ratingsRewriter = new RatingsRewriter();
        UserMaker usermaker = new UserMaker();

        try {
            ratingsRewriter.RewriteRatings();
        } catch (IOException e) {
            e.getMessage();
        }

        int NumberOfUsers = ratingsRewriter.getNumberOfUsers();
        try{usermaker.MakeUsersFile(NumberOfUsers);}catch(IOException e){
            e.printStackTrace();
        }
    }
}
