package GUIp2;

import Managers.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


//This controller controls the login process.
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

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    //Since all managers need to be passed around as arguments, space is made for them.
    public LoginController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    //The actions of each button is assigned.
    public void initialize(URL location, ResourceBundle resources) {
        //When pressed, this changes the window into the Register window.
        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int NumOfUsers = user_manager.GetListOfUsers().size() + 1;
                String LabelNumOfUsers = Integer.toString(NumOfUsers);
                //The Register.fxml file is assigned to a fxmlloader, a custom controller is bound to it as well, and the file is loaded and executed.
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
                fxmlLoader.setController(new RegisterController(LabelNumOfUsers, user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try {
                    Parent LoginParent = (Parent) fxmlLoader.load();
                    LoginScreen.getChildren().add(LoginParent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //On action, this button tries to use the inputted userID and password to log the user in.
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int NewID = Integer.parseInt(UsernameInput.getText());
                try {
                    //If successful, the main menu MenuOptions.fxml is loaded and executed.
                    user_manager.LogIn(NewID, passwordInput.getText());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                    fxmlLoader.setController(new MenuOptionsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                    try {
                        LoginScreen.getChildren().add(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //If not successful, then an alert tells the user that the userID or password is incorrect.
                } catch (RuntimeException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login unsuccessful");
                    alert.setHeaderText("Unable to Login");
                    alert.setContentText("Incorrect username/password");
                    alert.showAndWait();
                }
            }
        });
    }
}
