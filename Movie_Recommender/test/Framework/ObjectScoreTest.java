package Framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectScoreTest {

    User user;
    Recommendation recommendation;

    ObjectScore tester;
    ObjectScore tester2;

    @BeforeEach
    void SetUp(){
        user = new User(0, "test");
        tester = new ObjectScore<>(user, 1);
        recommendation = new Recommendation(32, "hell's bells");
        tester2 = new ObjectScore<>(recommendation, 0);
    }

    @Test
    void getObject01() {
        assertEquals(user, tester.GetObject());
    }

    @Test
    void getObject02() {
        assertEquals(recommendation, tester2.GetObject());
    }

    @Test
    void getScore1() {
        assertEquals(1, tester.GetScore());
    }

    @Test
    void getScore2() {
        assertEquals(0, tester2.GetScore());
    }

    @Test
    void getScore3() {
        assertNotEquals(tester, tester2);
    }
}