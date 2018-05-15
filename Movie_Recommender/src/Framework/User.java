package Framework;

//This class represents the most basic user possible, and all other users extends this.
public class User implements Basic_Characteristics {
    private int UserID;
    private String Password;

    public User(int UserID, String Password) {
        this.UserID = UserID;
        this.Password = Password;
    }

    public int GetID() {
        return UserID;
    }


    public String GetString() {
        return Password;
    }
}
