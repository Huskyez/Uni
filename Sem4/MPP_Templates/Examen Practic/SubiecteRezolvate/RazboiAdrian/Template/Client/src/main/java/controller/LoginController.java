package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import service.IServerImpl;
import service.ServerImpl;

public class LoginController {
    private IServerImpl server;
    private Stage primaryStage;
    private Scene mainScene;
    private MainController mainController;

    public TextField textFieldUsername;
    public PasswordField textFieldPassword;

    public void setServer(IServerImpl server) {
        this.server = server;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }





    public void handleLoginButton(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        User user = new User(username, password);
        if(server.login(user, this.mainController) == true){
            mainController.setUser(user);
            primaryStage.setScene(mainScene);
            textFieldUsername.clear();
            textFieldPassword.clear();
        }

    }

}
