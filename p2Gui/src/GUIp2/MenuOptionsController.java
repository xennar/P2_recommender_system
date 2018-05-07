package GUIp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuOptionsController implements Initializable {
    @FXML
    Button PreviousRatings;
    @FXML
    AnchorPane MenuOptionsScreen;

    public MenuOptionsController(){

    }




    public void initialize(URL location, ResourceBundle resources) {

    }

    public void PreviousRatingsButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("PreviousRatings.fxml"));
        this.MenuOptionsScreen.getChildren().add(PreviousRatingsParent);
    }
}
