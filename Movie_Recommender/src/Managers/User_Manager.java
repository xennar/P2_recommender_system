package Managers;

import Framework.DatabaseReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.HashMap;

//This class controls the users of the system on the macro level, and calls the reader which initializes the objects.
public class User_Manager {

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private HashMap<Integer, RatingsWatcher<Movie>> IdToUserMap;
    private RatingsWatcher<Movie> Current_user;


    //The constructor makes a map that takes IDs and returns the RatingsWatcher with that id
    public User_Manager(DatabaseReader reader, String path) {
        ListOfUsers = reader.ReadUsers(path);
        IdToUserMap = new HashMap<>();
        for (RatingsWatcher<Movie> u : ListOfUsers) {
            IdToUserMap.put(u.GetID(), u);
        }
    }

    //Basic Getter
    public ArrayList<RatingsWatcher<Movie>> GetListOfUsers() {
        return ListOfUsers;
    }

    //Makes a new user and adds the user to the system. No Neighbor or ignore are used due to non-existence.
    public void AddNewUser(int ID, String Password) throws RuntimeException {
        if (IdToUserMap.keySet().contains(ID))
            throw new RuntimeException("ID already exists");
        RatingsWatcher<Movie> newUser = new RatingsWatcher<Movie>(ID, Password);
        ListOfUsers.add(newUser);
        IdToUserMap.put(ID, newUser);
        Session_Manager.addNewSessionUserChanges(newUser);
    }


    //This method is used to set the current active user my logging the user in. If ID and password fits, the user is set,
    // if not, then an exception is throw.
    public void LogIn(int ID, String Password) {
        if (IdToUserMap.containsKey(ID)) {
            if (IdToUserMap.get(ID).GetString().equals(Password))
                Current_user = IdToUserMap.get(ID);
        }
        else throw new RuntimeException("UserID or Password is incorrect");
    }

    public RatingsWatcher<Movie> getCurrent_user() {
        return Current_user;
    }

    public RatingsWatcher<Movie> GetUserFromID(int ID){
        return IdToUserMap.get(ID);
    }
}