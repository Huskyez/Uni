package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Carte;
import model.User;
import service.IServerImpl;
import utils.IObserver;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController extends UnicastRemoteObject implements Initializable, IObserver {
    public Button logoutButton;
    public TableView tableViewParticipanti;
    public TableColumn tableColumnParticipantiJoc;
    public Button btnStartJoc;
    public TableView tableViewPachetCarti;
    public TableColumn tableColumnPachetCarti;
    public Button btnTrimiteCarte;
    public TableColumn tableColumnCartiAdversar;
    public TableView tableViewCartiAdversari;
    private User user;
    private IServerImpl server;
    private Stage primaryStage;
    private Scene loginScene;
    private ObservableList<User> modelLogati = FXCollections.observableArrayList();
    private ObservableList<Carte> modelCarti = FXCollections.observableArrayList();
    private ObservableList<Carte> modelCartiAdversari = FXCollections.observableArrayList();


    public MainController() throws RemoteException{};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //pentru incarcare tabele
        tableColumnParticipantiJoc.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        tableColumnPachetCarti.setCellValueFactory(new PropertyValueFactory<Carte, String>("nume"));

        tableColumnCartiAdversar.setCellValueFactory(new PropertyValueFactory<Carte, String>("nume"));
    }

    public void setUser(User user) {
        this.user = user;
         tableViewParticipanti.setItems(modelLogati);
         tableViewPachetCarti.setItems(modelCarti);
         tableViewCartiAdversari.setItems(modelCartiAdversari);
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
        primaryStage.setScene(loginScene);
        server.logout(user);
    }

    //ALERT BOX
    public void alertBox(){
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

    public void handleStartJoc(ActionEvent actionEvent) throws RemoteException {
        server.startJoc();
        btnStartJoc.setDisable(true);
    }

    @Override
    public void updateJucatoriLogatiSiPachetCarti(List<User> playingUsers, List<Carte> listaCarti) throws RemoteException {
        modelLogati.setAll(playingUsers);
        modelCarti.setAll(listaCarti);
    }

    public void handleTrimiteCarte(ActionEvent actionEvent) throws RemoteException {
        btnTrimiteCarte.setDisable(true);
        Carte carte = (Carte) tableViewPachetCarti.getSelectionModel().getSelectedItem();
        server.primesteCarti(user, carte);
    }

    @Override
    public void updateCartiAdversari(List<Carte> listaOTuraDeJoc) throws RemoteException {
        modelCartiAdversari.setAll(listaOTuraDeJoc);
    }

    @Override
    public void updateStergeCarteTrimisa(List<Carte> cartiJucator) throws RemoteException {
        modelCarti.setAll(cartiJucator);
    }

    @Override
    public void updateCastigator(List<Carte> listaCarti) throws RemoteException {
        modelCarti.setAll(listaCarti);
    }

    @Override
    public void alegereCarteNoua() throws RemoteException {
        btnTrimiteCarte.setDisable(false);
    }
}
