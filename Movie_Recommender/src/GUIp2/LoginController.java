package GUIp2;

import Managers.User_Manager;
import Framework.DatabaseReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Button;

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
        int NewID = Integer.parseInt(UsernameInput.getText());
        DatabaseReader userDatabaseReader = new DatabaseReader();
        User_Manager NewLogin = new User_Manager(userDatabaseReader, "src/Database/Users.csv");
        NewLogin.LogIn(NewID, passwordInput.getText());
            Parent LoginParent = FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
            this.LoginScreen.getChildren().add(LoginParent);
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void registerProcess(ActionEvent actionEvent) throws IOException {
        DatabaseReader userDatabaseReader = new DatabaseReader();
        User_Manager NewLogin = new User_Manager(userDatabaseReader, "src/Database/Users.csv");
        int NumOfUsers = NewLogin.GetListOfUsers().size() + 1;
        String LabelNumOfUsers = Integer.toString(NumOfUsers);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        fxmlLoader.setController(new RegisterController(LabelNumOfUsers));
        Parent LoginParent = (Parent) fxmlLoader.load();
        this.LoginScreen.getChildren().add(LoginParent);
    }
}
