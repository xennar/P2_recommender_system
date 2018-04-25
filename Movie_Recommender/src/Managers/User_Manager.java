package Managers;

import Framework.Basic_Characteristics;
import Framework.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User_Manager<T extends User> {

    private static User_Manager user_manager = new User_Manager();

    private User_Manager(){
        //TODO CONNECT TO READER
    }

    private ArrayList<T> ListOfUsers;

    ArrayList<T> GetListOfUser(){
        return ListOfUsers;
    }

    void AddNewUser(T NewUser){


        ListOfUsers.add(NewUser);
    }



}
