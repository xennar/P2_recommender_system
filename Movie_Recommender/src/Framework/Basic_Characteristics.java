package Framework;

//This interface is implemented by both User and Recommendation so that all extenders of those can be used generically.
//This means that it should always be possible to get the ID and password/Title.
public interface Basic_Characteristics {
    int GetID();

    String GetString();
}
