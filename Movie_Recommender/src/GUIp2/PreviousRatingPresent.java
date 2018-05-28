package GUIp2;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableDoubleValue;

//This class is used to show information in the tableview of preciously rated products
public class PreviousRatingPresent {
    private IntegerProperty IDprop;
    private int ID;
    private StringProperty titleprop;
    private String title;
    private StringProperty ratingprop;
    private double rating;


    //Since tableviews can't use Strings and Integers, Properties are made instead.
    PreviousRatingPresent(int ID, String title, double rating){
        this.IDprop = new SimpleIntegerProperty(ID);
        this.titleprop = new SimpleStringProperty(title);
        this.ratingprop = new SimpleStringProperty(String.valueOf(rating));
        this.ID = ID;
        this.title = title;
        this.rating = rating;
    }


    //Basic getters
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

    //Sets the visual number to be equal the actual number
    void SetRatingpropToRating(){
        ratingprop = new SimpleStringProperty(String.valueOf(rating));
    }
}
