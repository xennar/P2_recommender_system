//Made by Emil Palmelund Voldby, evoldb17@student.aau.dk

package GUIp2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//This class is used by Neighbors to show, in a tableview, a movie title, and the score both the user and neighbor gave it
public class NeighborUserScorePresent {
    private StringProperty title;
    private StringProperty userscore;
    private double userrating;
    private StringProperty neighborscore;
    private double neighborrating;

    //Because it needs to be in a tableview, the values are converted into StringProperties.
    NeighborUserScorePresent(String title, double userscore, double neighborscore) {
        this.title = new SimpleStringProperty(title);
        this.userscore = new SimpleStringProperty(String.valueOf(userscore));
        this.neighborscore = new SimpleStringProperty(String.valueOf(neighborscore));
        this.userrating = userscore;
        this.neighborrating = neighborscore;
    }

    //Basic getters for all variables
    public StringProperty getTitle(){
        return title;
    }

    public StringProperty getUserscore(){
        return userscore;
    }

    public StringProperty getNeighborscore(){
        return neighborscore;
    }

    public double GetUserrating(){
        return userrating;
    }

    public double GetNeighborRating(){
        return neighborrating;
    }

}