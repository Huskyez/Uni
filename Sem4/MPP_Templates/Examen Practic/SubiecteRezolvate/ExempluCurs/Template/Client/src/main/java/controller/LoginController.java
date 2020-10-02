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
import model.Corector;
import service.IServerImpl;
import service.ServerImpl;

public class LoginController {
    private IServerImpl service;
    private Stage primaryStage;
    private Parent parent;
    private CorectorController corectorController;
    private Scene corectorScene;

    public void setCorectorController(CorectorController corectorController) {
        this.corectorController = corectorController;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setService(IServerImpl service) {
        this.service = service;
    }

    public TextField textFieldUsername;
    public PasswordField textFieldPassword;

    public void handleLoginButton(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        Corector corector = new Corector(username, password);
        if(service.login(corector, corectorController) == true){
            corectorScene.setRoot(parent);
            corectorController.setUsername(corector.getUsername());
            primaryStage.setScene(corectorScene);
            primaryStage.show();
            textFieldPassword.clear();
            textFieldUsername.clear();
        }

    }

    public void setSceneCorector(Scene corectorScene) {
        this.corectorScene = corectorScene;
    }
}
