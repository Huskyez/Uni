package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import model.User;
import service.IServerImpl;
import utils.IObserver;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class MainController extends UnicastRemoteObject implements Initializable, IObserver {
    public Button logoutButton;

    private User user;
    private IServerImpl server;
    private Stage primaryStage;
    private Scene loginScene;

    public MainController() throws RemoteException{}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //pentru tabele sau incarcari dinainte
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setServer(IServerImpl server) {
        this.server = server;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void handleLogout(ActionEvent actionEvent) {
        primaryStage.close();
        System.exit(0);
        server.logout(user);
    }




    //ALERT BOX
    public void alertBox(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informatii lucrare : ");
                alert.setContentText("RECORECTARE");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-background-color: RED");
                alert.showAndWait();
            }
        });
    }
}
