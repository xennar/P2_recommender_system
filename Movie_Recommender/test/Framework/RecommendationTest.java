package Framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationTest {

    Recommendation tester;

    @BeforeEach
    void SetUp(){
        tester = new Recommendation(0, "test");
    }

    @Test
    void getID() {
        assertEquals(0, tester.GetID());
    }

    @Test
    void getString() {
        assertEquals("test", tester.GetString());
    }
}