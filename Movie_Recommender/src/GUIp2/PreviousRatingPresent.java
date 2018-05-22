package GUIp2;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableDoubleValue;

public class PreviousRatingPresent {
    private IntegerProperty ID;
    private StringProperty title;
    private StringProperty rating;



    public PreviousRatingPresent(int ID, String title, double rating){
        this.ID = new SimpleIntegerProperty(ID);
        this.title = new SimpleStringProperty(title);
        this.rating = new SimpleStringProperty(String.valueOf(rating));
    }

    public IntegerProperty getID() {
        return ID;
    }

    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getRating() {
        return rating;
    }
}
