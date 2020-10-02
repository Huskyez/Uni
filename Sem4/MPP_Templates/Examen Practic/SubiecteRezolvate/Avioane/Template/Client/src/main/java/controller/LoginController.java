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
    public GridPane gridPane;
    private IServerImpl service;
    public TextField textFieldUsername;
    public PasswordField textFieldPassword;
    private Parent gameWindow;
    private Stage primaryStage;
    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setService(IServerImpl service) {
        this.service = service;
    }


    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    public void setGameWindow(Parent rootGame) {
        this.gameWindow = rootGame;
    }

    public void handleLoginButton(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        User user = new User(username, password);
        if(service.login(user) == true){
            Scene scene = new Scene(gameWindow);
            gameController.setUser(user);
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }

    public void handleClick(MouseEvent mouseEvent) {
        Node source = (Node)mouseEvent.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.println(colIndex+" "+rowIndex);
        System.out.println("daaa");
    }


}
