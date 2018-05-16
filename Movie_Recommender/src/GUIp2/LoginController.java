package GUIp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Button;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField UsernameInput;
    @FXML
    PasswordField passwordInput;
    @FXML
    Button LoginButton;
    @FXML
    Button RegisterButton;
    @FXML
    AnchorPane LoginScreen;

    public LoginController(){

    }



    public void LoginProcess(ActionEvent actionEvent) throws IOException {
        FileReader ReadID = new FileReader("src\\Database\\Users.csv");
        //for();
        if (UsernameInput.getText().equals("1")) {
            Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
            this.LoginScreen.getChildren().add(LoginParent);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void registerProcess(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("Register.fxml"));
        this.LoginScreen.getChildren().add(LoginParent);
    }
}
