package GUIp2;

import Managers.*;
import Framework.DatabaseReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public LoginController(User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager){
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    public void initialize(URL location, ResourceBundle resources) {
        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int NumOfUsers = user_manager.GetListOfUsers().size() + 1;
                String LabelNumOfUsers = Integer.toString(NumOfUsers);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
                fxmlLoader.setController(new RegisterController(LabelNumOfUsers, user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try{
                Parent LoginParent = (Parent) fxmlLoader.load();
                LoginScreen.getChildren().add(LoginParent);}catch(IOException e){e.printStackTrace();}

            }
        });

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int NewID = Integer.parseInt(UsernameInput.getText());
                user_manager.LogIn(NewID, passwordInput.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                fxmlLoader.setController(new MenuOptionsController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                try{
                LoginScreen.getChildren().add(fxmlLoader.load());}catch (IOException e){e.printStackTrace();}
            }
        });
    }
}
