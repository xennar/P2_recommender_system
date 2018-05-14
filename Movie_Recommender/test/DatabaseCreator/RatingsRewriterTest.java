package DatabaseCreator;

import Managers.User_Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RatingsRewriterTest {

    private UserMaker userMaker;
    private RatingsRewriter ratingsRewriter;

    @BeforeEach
    void SetUp(){
        userMaker = new UserMaker();
        ratingsRewriter = new RatingsRewriter();
        try{ratingsRewriter.RewriteRatings();}catch(IOException e){e.getMessage();}
    }

    @Test
    void testgetNumberOfUsers() {
        assertEquals(671, ratingsRewriter.getNumberOfUsers());
    }
}