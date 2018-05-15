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
        assertEquals(671, ListOfUsers.size());
    }

    @Test
    void addNewUser01() {
        user_manager.AddNewUser(672, "password");
        assertEquals(672, ListOfUsers.get(ListOfUsers.size()-1).GetID());
    }

    @Test
    void addNewUser02(){
        assertThrows(RuntimeException.class, () -> user_manager.AddNewUser(1, "1"));
    }
    @Test
    void logIn01() {
        assertEquals(ListOfUsers.get(0),user_manager.LogIn(1, "1"));
    }

    @Test
    void logIn02() {
        assertThrows(RuntimeException.class, () -> user_manager.LogIn(2, "5"));
    }
}