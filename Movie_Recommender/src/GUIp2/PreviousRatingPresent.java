package GUIp2;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableDoubleValue;

public class PreviousRatingPresent {
    private IntegerProperty IDprop;
    private int ID;
    private StringProperty titleprop;
    private String title;
    private StringProperty ratingprop;
    private double rating;



    PreviousRatingPresent(int ID, String title, double rating){
        this.IDprop = new SimpleIntegerProperty(ID);
        this.titleprop = new SimpleStringProperty(title);
        this.ratingprop = new SimpleStringProperty(String.valueOf(rating));
        this.ID = ID;
        this.title = title;
        this.rating = rating;
    }

    IntegerProperty getPropID() {
        return IDprop;
    }

    StringProperty getPropTitle() {
        return titleprop;
    }

    StringProperty getPropRating() {
        return ratingprop;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    void SetRating(double change){
        rating = change;
    }

    void SetRatingpropToRating(){
        ratingprop = new SimpleStringProperty(String.valueOf(rating));
    }
}
