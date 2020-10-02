package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Lucrare;
import service.IServerImpl;
import utils.IObserver;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CorectorController extends UnicastRemoteObject implements Initializable, IObserver {
    public TableColumn tableColumnPaperId;
    public TableView<Lucrare> tableView;
    public TextField notaTextField;
    public Button evaluareLucrareBtn;
    public Button logoutBtn;
    private String username;
    private IServerImpl service;
    private Stage primaryStage;
    private Scene loginScene;
    private ObservableList<Lucrare> model = FXCollections.observableArrayList();
    private Lucrare lucrare;

    public CorectorController() throws RemoteException{}
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setService(IServerImpl service) {
        this.service = service;
    }
    public void setUsername(String username) {
        this.username = username;
        model.setAll(service.getAllLucrariCorector(username));
        tableView.setItems(model);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnPaperId.setCellValueFactory(new PropertyValueFactory<Lucrare, Integer>("paperId"));
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue!= null)
                getLucrare(newValue);
        } );
    }

    private void getLucrare(Lucrare newValue) {
        this.lucrare = newValue;
    }

    public void handleEvaluareLucrare(ActionEvent actionEvent) {
        Integer paperId =  lucrare.getPaperId();
        Double nota = Double.parseDouble(notaTextField.getText());
        service.notaLucrare(paperId, nota, username, this);
    }

    public void handleLogout(ActionEvent actionEvent) {
        primaryStage.setScene(loginScene);
        service.logout(username);
    }

    @Override
    public void ok() {
        model.setAll(service.getAllLucrariCorector(username));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informatii lucrare : ");
                alert.setContentText("OK");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-background-color: green");
                alert.showAndWait();
            }
        });
    }

    @Override
    public void recorectare() {
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
