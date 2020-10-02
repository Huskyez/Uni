package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Carte;
import model.Jucator;
import service.IServerImpl;
import utils.IObserver;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends UnicastRemoteObject implements Initializable, IObserver {
    public Button logoutButton;
    public TableView<Jucator> tableViewJucatori;
    public TableColumn<String,Jucator> columnJucatori;
    public TableView tableViewCarti;
    public TableColumn columnCarti;
    public Button buttonAlegere;
    private Parent root;

    private Jucator jucator;
    private IServerImpl server;
    private Stage primaryStage;
    private Scene loginScene;

    private ObservableList<Jucator> modelJucatori = FXCollections.observableArrayList();
    private ObservableList<Carte> modelCarti = FXCollections.observableArrayList();
    private Carte carte;

    public MainController() throws RemoteException{}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //pentru tabele sau incarcari dinainte
        columnJucatori.setCellValueFactory(new PropertyValueFactory<String, Jucator>("username"));
        tableViewJucatori.setItems(modelJucatori);

        columnCarti.setCellValueFactory(new PropertyValueFactory<String, Carte>("simbol"));
        tableViewCarti.setItems(modelCarti);

        tableViewCarti.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue!=null)
                getCarte(newValue);
        }));
    }

    private void getCarte(Object newValue) {
        this.carte = (Carte) newValue;
    }

    public void setJucator(Jucator jucator) {
        this.jucator = jucator;
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
        server.logout(jucator);
        primaryStage.close();
        System.exit(0);
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

    public void handleStart(ActionEvent actionEvent) {
        server.inceputJoc();
    }

    @Override
    public void inceputJoc(List<Jucator> jucatori) throws RemoteException {
        modelJucatori.setAll(jucatori);
    }

    @Override
    public void primesteCarti(List<Carte> carti) throws RemoteException {
        modelCarti.setAll(carti);
    }

    @Override
    public void endOfRound(boolean b, List<Carte> carti) throws RemoteException {
        if (b) {
            //modelCarti.remove(carte);
            modelCarti.setAll(carti);
        }else
            modelCarti.remove(carte);
    }

    public void setRoot(Parent rootMain) {
        this.root = rootMain;
    }

    public void setDisableWindow(){
        root.setDisable(true);
    }

    public void handleAlegere(ActionEvent actionEvent) {
        buttonAlegere.setDisable(false);
        server.amAlesCarte(carte, jucator, this);
        buttonAlegere.setDisable(true);
    }
}
