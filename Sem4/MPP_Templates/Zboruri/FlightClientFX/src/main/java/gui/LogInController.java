package gui;

import flight.domain.Angajat;
import flight.domain.Zbor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.FlightException;

import services.IFlightServer;

public class LogInController{
    public TextField userBox;
    public PasswordField passBox;

    private Angajat angajat;
    private Parent mainWindowParent;
    private IFlightServer server;
    private MainWindowController mainWindowController;
    private Iterable<Zbor> zboruri;

    public void setServer(IFlightServer server){
        this.server = server;
    }

    public void setParent(Parent p){this.mainWindowParent = p;}

    public void setMainWindowController(MainWindowController mainWindowController){this.mainWindowController = mainWindowController;}
    public void logInHandle(ActionEvent actionEvent) {
        String user = userBox.getText();
        String pass = passBox.getText();
        angajat = new Angajat(user,pass);
        try{
            server.logIn(angajat,mainWindowController);
            zboruri = server.getZboruri();
            mainWindowController.firstLoad(zboruri);
            Stage stage = new Stage();
            stage.setScene(new Scene(mainWindowParent));

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    mainWindowController.logOut();
                    System.exit(0);
                }
            });

            stage.show();
            mainWindowController.setAngajat(angajat);
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (FlightException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Flight LOGIN");
            alert.setHeaderText("Authentication failure");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
