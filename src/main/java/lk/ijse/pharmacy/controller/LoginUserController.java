package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.ijse.pharmacy.bo.custom.impl.LoginUserBOImpl;
import lk.ijse.pharmacy.dao.custom.impl.UserDAOImpl;
import lk.ijse.pharmacy.dto.UserDTO;
import lk.ijse.pharmacy.entity.User;
import lk.ijse.pharmacy.util.AlertController;
import lk.ijse.pharmacy.util.Navigation;

public class LoginUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink dontHaveAnAccountHyperLink;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private TextField userNameTxt;

    @FXML
    private PasswordField passwordTxt;

    String username;
    String password;
    String userr;

    UserDAOImpl loginUserBO = new UserDAOImpl();

    @FXML
    void hyperLinkOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("signUpForm.fxml",event);

    }

    @FXML
    void loginOnAction(ActionEvent event) {
        username = userNameTxt.getText();
        password = null;
        try {
            ArrayList<User> userDTO = loginUserBO.search(username);
            for (User u : userDTO) {
                System.out.println(u.getPassword()+""+u.getUsername());
                password = u.getPassword();
                userr = u.getUsername();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (passwordTxt.getText().equals(password) ) {
            AlertController.animationMesseage("/img/tick.gif", "Login",
                    "Login succesfull !!");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {
                loginBtn.getScene().getWindow().hide();

                try {
                    Navigation.switchNavigation("Dashboard.fxml",event);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }));
            timeline.play();
        }else if (userNameTxt.getText().isEmpty() && passwordTxt.getText().isEmpty()) {
            passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 3 3 3 3;");
            userNameTxt.setStyle("-fx-border-color: red; -fx-border-width: 3 3 3 3;");
            AlertController.errormessage("Username field and Password field can't be empty");


        }else if (!userNameTxt.getText().equals(userr) && !passwordTxt.getText().equals(password)) {
            userNameTxt.setStyle("-fx-border-color: red; -fx-border-width: 3 3 3 3;");
            passwordTxt.setStyle("-fx-border-color: red; -fx-border-width: 3 3 3 3;");
            AlertController.errormessage("Username or password is incorrect.please check your details again!!");

        }

    }

    @FXML
    void initialize() {

    }

    @FXML
    void txPasswordOnMouseClicked(MouseEvent event) {
        passwordTxt.setStyle("-fx-border-color: transparent");
    }

    @FXML
    void txtUserNameOnMouseClicked(MouseEvent event) {
        userNameTxt.setStyle("-fx-border-color: transparent");
    }

}
