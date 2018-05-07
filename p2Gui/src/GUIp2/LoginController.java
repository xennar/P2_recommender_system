package GUIp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    Button LoginButton;
    @FXML
    Button RegisterButton;
    @FXML
    AnchorPane LoginScreen;

    public LoginController(){

    }



    public void LoginProcess(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
        this.LoginScreen.getChildren().add(LoginParent);
    }

    public void initialize(URL location, ResourceBundle resources) {
    }
}
