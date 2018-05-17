package DatabaseCreator;

import Managers.User_Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RatingsRewriterTest {

    private RatingsRewriter ratingsRewriter;

    @BeforeEach
    void SetUp(){
        ratingsRewriter = new RatingsRewriter();
        try{ratingsRewriter.RewriteRatings();}catch(IOException e){e.getMessage();}
    }

    @Test
    //Tests to make sure that there has been written ratings for 671 users
    void getNumberOfUsers() {
        assertEquals(671, ratingsRewriter.getNumberOfUsers());
    }
}