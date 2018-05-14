package Framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User tester;

    @BeforeEach
    void SetUp(){
        tester = new User(0, "0");
    }

    @Test
    void getID() {
        assertEquals(0, tester.GetID());
    }

    @Test
    void getString() {
        assertEquals("0", tester.GetString());
    }
}