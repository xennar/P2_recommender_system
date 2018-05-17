package Managers;

import Framework.FileReader;
import RatingsWatcher.RatingsWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Movie.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class User_ManagerTest {

    private String userPath;
    private User_Manager user_manager;
    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;

    @BeforeEach
    void SetUp(){
        userPath = "test/UsersTest.csv";
        FileReader fileReader = new FileReader();
        user_manager = new User_Manager(fileReader, userPath);
        ListOfUsers = user_manager.GetListOfUsers();
    }
    @Test
    void getListOfUsers() {
        assertTrue(671 <= ListOfUsers.size());
    }

    @Test
    //adds a user and tests if the ID of the newly added user matches the created user
    void addNewUser01() {
        int newUserID = user_manager.GetListOfUsers().size() + 1;
        user_manager.AddNewUser(newUserID, "password");
        assertEquals(newUserID, ListOfUsers.get(ListOfUsers.size()-1).GetID());
    }

    @Test
    void addNewUser02(){
        assertThrows(RuntimeException.class, () -> user_manager.AddNewUser(11, "1"));
    }
    @Test
    void logIn01() {
        assertEquals(ListOfUsers.get(10),user_manager.LogIn(11, "11"));
    }

    @Test
    void logIn02() {
        assertThrows(RuntimeException.class, () -> user_manager.LogIn(2, "5"));
    }
}