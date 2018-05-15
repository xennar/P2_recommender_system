package Managers;

import Framework.FileReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.HashMap;

//This class controls the users of the system on the macro level, and calls the reader which initializes the objects.
public class User_Manager {

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private HashMap<Integer, RatingsWatcher<Movie>> UserIDMap;

<<<<<<< HEAD
<<<<<<< HEAD
    //The constructor makes a map that takes IDs and returns the RatingsWatcher with that id
    public User_Manager(FileReader reader) {
        ListOfUsers = reader.ReadUsers();
=======
    public User_Manager(FileReader reader, String path) {
        ListOfUsers = reader.ReadUsers(path);
>>>>>>> 9e4fc02ab75514945a16198651f8811731d8ff1a
=======
    public User_Manager(FileReader reader, String path) {
        ListOfUsers = reader.ReadUsers(path);
>>>>>>> 9e4fc02ab75514945a16198651f8811731d8ff1a
        UserIDMap = new HashMap<>();
        for (RatingsWatcher<Movie> u : ListOfUsers) {
            UserIDMap.put(u.GetID(), u);
        }
    }

    //Basic Getter
    public ArrayList<RatingsWatcher<Movie>> GetListOfUsers() {
        return ListOfUsers;
    }

    //Makes a new user and adds the user to the system. No Neighbor or ignore are used due to non-existence.
    public void AddNewUser(int ID, String Password) throws RuntimeException {
        if (UserIDMap.keySet().contains(ID))
            throw new RuntimeException("ID already exists");
        RatingsWatcher<Movie> newUser = new RatingsWatcher<Movie>(ID, Password);
        ListOfUsers.add(newUser);
    }

    //This method is used to set the current active user my logging the user in. If ID and password fits, the user is set,
    // if not, then an exception is throw.
    public RatingsWatcher<Movie> LogIn(int ID, String Password) {
        if (UserIDMap.containsKey(ID)) {
            if (UserIDMap.get(ID).GetString().equals(Password))
                return UserIDMap.get(ID);
        }
        throw new RuntimeException("UserID or Password is incorrect");
    }
}