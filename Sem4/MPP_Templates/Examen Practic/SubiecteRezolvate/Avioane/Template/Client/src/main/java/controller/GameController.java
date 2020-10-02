package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import service.IServerImpl;
import utils.IObserver;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class GameController extends UnicastRemoteObject implements IObserver, Initializable, Serializable {

    public GameController() throws RemoteException {
    }
    public Button startBtn;
    public GridPane gridPane;
    public TextField yTextField;
    public TextField xTextField;
    private IServerImpl service;
    private User user;
    private Stage primaryStage;

    public void setUser(User user) {
        this.user = user;
    }
    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    public Button b_11;
    public Button b_12;
    public Button b_13;
    public Button b_21;
    public Button b_22;
    public Button b_23;
    public Button b_31;
    public Button b_32;
    public Button b_33;
    public void setService(IServerImpl service) {
        this.service = service;
        gridPane.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addGridEvent();
    }

    private void addGridEvent() {
        gridPane.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    item.setStyle("-fx-background-color: red");
                    item.setDisable(true);
                    Integer x = gridPane.getRowIndex(item);
                    Integer y = gridPane.getColumnIndex(item);
                    if(x == null)
                        x = 0;
                    if(y == null)
                        y = 0;
                    service.oneMoved(user , x + "" + y);
                    gridPane.setDisable(true);
                }
            });
        });
    }

    @Override
    public void move() {
        gridPane.setDisable(false);
    }

    @Override
    public void endOfGame(boolean winner) throws RemoteException {
        if(winner){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("End of game");
                    alert.setContentText("You have won!");
                    alert.showAndWait();
                    primaryStage.close();
                }
            });
        }
        else{
            Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("End of game");
                alert.setContentText("You have lost!");
                alert.showAndWait();
                primaryStage.close();
            }
        });
        }
    }

    public void handleStartGame(ActionEvent actionEvent) {
        Integer x = Integer.parseInt(xTextField.getText()) - 1;
        Integer y = Integer.parseInt(yTextField.getText()) - 1;
        this.user.setPositieAvion(x + "" + y);

        if(x == 0){
            x = null;
        }
        if(y == 0){
            y = null;
        }

        Button result = new Button();
        for (Node node : gridPane.getChildren()) {
            if(gridPane.getRowIndex(node) == x && gridPane.getColumnIndex(node) == y) {
                result = (Button) node;
                break;
            }
        }
        result.setStyle("-fx-background-color: blue");
        result.setText("X");
        //result.setStyle("-fx-border-color: #ff0000; -fx-border-width: 10px;");
        gridPane.setDisable(true);
        service.startGame(this.user, this);

        xTextField.setDisable(true);
        yTextField.setDisable(true);

    }


}
