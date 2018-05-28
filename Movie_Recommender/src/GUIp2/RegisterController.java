package GUIp2;

import Managers.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


//This controller handles Register.fxml, and allows the creation of new users
public class RegisterController implements Initializable {


    String newPasswordInput;
    String repeatPasswordInput;
    @FXML
    Label GivenID;
    @FXML
    TextField repeatPassword;
    @FXML
    TextField newPassword;
    @FXML
    Button createButton;
    @FXML
    Button cancelbutton;
    @FXML
    AnchorPane registerScreen;

    String NumberofUsers;

    User_Manager user_manager;
    private Product_Manager product_manager;
    private Ratings_Manager ratings_manager;
    private Neighbor_Manager neighbor_manager;
    private Session_Manager session_manager;

    public RegisterController(String NumberofUsers, User_Manager user_manager, Product_Manager product_manager, Ratings_Manager ratings_manager, Neighbor_Manager neighbor_manager, Session_Manager session_manager) {

        this.NumberofUsers = NumberofUsers;
        this.user_manager = user_manager;
        this.product_manager = product_manager;
        this.ratings_manager = ratings_manager;
        this.neighbor_manager = neighbor_manager;
        this.session_manager = session_manager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //The UserID of the next user made is set.
        int NumOfUsers = user_manager.GetListOfUsers().size() + 1;
        String LabelNumOfUsers = Integer.toString(NumOfUsers);
        GivenID.setText(LabelNumOfUsers);

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int IDToInt = Integer.parseInt(GivenID.getText());
                //creates a new user if the password is the same in both fields.
                if (newPassword.getText().equals(repeatPassword.getText())) {
                    user_manager.AddNewUser(IDToInt, newPassword.getText());
                    //if a new user is made, then the GUI switches to the login screen.
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                        fxmlLoader.setController(new LoginController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));

                        registerScreen.getChildren().add(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //If the cancel button is pressed, then the screen switches back to the login scree.
        cancelbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    fxmlLoader.setController(new LoginController(user_manager, product_manager, ratings_manager, neighbor_manager, session_manager));
                    try {
                    registerScreen.getChildren().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void registerPassword(ActionEvent actionEvent) {

    }

    public void registerRepeatPassword(ActionEvent actionEvent) {
    }


    public void LoginProcess(ActionEvent actionEvent) throws IOException {
        int IDToInt = Integer.parseInt(GivenID.getText());
        if (newPassword.getText().equals(repeatPassword.getText())) {
            user_manager.AddNewUser(IDToInt, newPassword.getText());

            Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("Login.fxml"));
            this.registerScreen.getChildren().add(LoginParent);
        }
        //int GivenID = Integer.parseInt(UsernameInput.getText());
        //Framework.DatabaseReader UserFileReader = new Framework.DatabaseReader();
        //User_Manager NewLogin = new User_Manager(UserFileReader, "src/Database/Users.csv");

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

