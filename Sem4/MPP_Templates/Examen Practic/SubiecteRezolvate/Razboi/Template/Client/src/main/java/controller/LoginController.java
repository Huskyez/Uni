package controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Jucator;
import service.IServerImpl;
import utils.AnException;

public class LoginController {
    private IServerImpl service;
    private Stage primaryStage;
    private Scene mainScene;
    private MainController mainController;

    public TextField textFieldUsername;
    public PasswordField textFieldPassword;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setService(IServerImpl service) {
        this.service = service;
    }



    public void handleLoginButton(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        Jucator jucator = new Jucator(username, password);
        try {
            if(service.login(jucator, mainController) == true){
                if(service.getJocStatus())
                    mainController.setDisableWindow();
                mainController.setJucator(jucator);
                primaryStage.setScene(mainScene);
                textFieldUsername.clear();
                textFieldPassword.clear();
            }
        } catch (AnException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOG IN");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

}
