package Managers;

import Framework.FileReader;
import Movie.Movie;
import RatingsWatcher.RatingsWatcher;

import java.util.ArrayList;
import java.util.HashMap;

public class User_Manager {

    private ArrayList<RatingsWatcher<Movie>> ListOfUsers;
    private HashMap<Integer, RatingsWatcher<Movie>> UserIDMap;

    public User_Manager(FileReader reader) {
        ListOfUsers = reader.ReadUsers();
        UserIDMap = new HashMap<>();
        for (RatingsWatcher<Movie> u : ListOfUsers) {
            UserIDMap.put(u.GetID(), u);
        }
    }

    public ArrayList<RatingsWatcher<Movie>> GetListOfUsers() {
        return ListOfUsers;
    }

    public void AddNewUser(int ID, String Password) {
        RatingsWatcher<Movie> newUser = new RatingsWatcher<Movie>(ID, Password);
        ListOfUsers.add(newUser);
    }

    public RatingsWatcher<Movie> LogIn(int ID, String Password) {
        if (UserIDMap.containsKey(ID)) {
            if (UserIDMap.get(ID).GetString().equals(Password))
                return UserIDMap.get(ID);
        }
        throw new RuntimeException("UserID or Password is incorrect");
    }
}