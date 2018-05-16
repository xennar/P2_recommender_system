package GUIp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    String newPasswordInput;
    String repeatPasswordInput;

    @FXML
    TextField repeatPassword;
    @FXML
    TextField newPassword;
    @FXML
    Button createButton;
    @FXML
    AnchorPane registerScreen;

    public RegisterController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerPassword(ActionEvent actionEvent) {

    }

    public void registerRepeatPassword(ActionEvent actionEvent) {
    }

    public void LoginProcess(ActionEvent actionEvent) throws IOException {

        System.out.println(newPassword.getText());
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("Login.fxml"));
        this.registerScreen.getChildren().add(LoginParent);
    }

    public void registerProcess(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("Login.fxml"));
        this.registerScreen.getChildren().add(LoginParent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterController that = (RegisterController) o;
        return Objects.equals(newPasswordInput, that.newPasswordInput) &&
                Objects.equals(repeatPasswordInput, that.repeatPasswordInput);
    }

    @Override
    public int hashCode() {

        return Objects.hash(newPasswordInput, repeatPasswordInput);
    }
}
