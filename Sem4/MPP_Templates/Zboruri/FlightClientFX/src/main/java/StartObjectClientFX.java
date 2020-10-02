import gui.LogInController;
import gui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objectprotocol.FlightServerObjectProxy;
import services.IFlightServer;

import java.io.IOException;
import java.util.Properties;

public class StartObjectClientFX extends Application {
    private Stage primaryStage;

    private static int defaultChatPort = 55555;
    private static String defaultServer = "localhost";

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(StartObjectClientFX.class.getResourceAsStream("/flightclient.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find flightclient.properties " + e);
            return;
        }
        String serverIP = clientProps.getProperty("flight.server.host", defaultServer);
        int serverPort = defaultChatPort;

        try {
            serverPort = Integer.parseInt(clientProps.getProperty("flight.server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultChatPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

        IFlightServer server = new FlightServerObjectProxy(serverIP, serverPort);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("logIn.fxml"));
        Parent root = loader.load();

        LogInController ctrlLogIn = loader.getController();
        ctrlLogIn.setServer(server);

        FXMLLoader mainWindowLoader = new FXMLLoader(getClass().getClassLoader().getResource("mainWindow.fxml"));
        Parent mainRoot = mainWindowLoader.load();

        MainWindowController mainWindowController = mainWindowLoader.getController();

        mainWindowController.setServer(server);
        ctrlLogIn.setMainWindowController(mainWindowController);
        ctrlLogIn.setParent(mainRoot);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

