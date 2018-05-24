//Made by Emil Palmelund Voldby, evoldb17@student.aau.dk

package GUIp2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NeighborUserScorePresent {
    private StringProperty title;
    private StringProperty userscore;
    private double userrating;
    private StringProperty neighborscore;
    private double neighborrating;

    NeighborUserScorePresent(String title, double userscore, double neighborscore) {
        this.title = new SimpleStringProperty(title);
        this.userscore = new SimpleStringProperty(String.valueOf(userscore));
        this.neighborscore = new SimpleStringProperty(String.valueOf(neighborscore));
        this.userrating = userscore;
        this.neighborrating = neighborscore;
    }

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