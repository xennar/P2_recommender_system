package Framework;

public class User implements Basic_Characteristics {
    private int UserID;
    private String Password;

    public int GetID() {
        return UserID;
    }


    public String GetString() {
        return Password;
    }
}
