import controller.GameController;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IServerImpl;
import service.ServerImpl;

public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IServerImpl server = (IServerImpl) factory.getBean("serverService");
       // server.saveUser(new User("admin", "admin"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/loginView.fxml"));
        Parent rootLayout = loader.load();
        LoginController loginController = loader.getController();
        Scene loginScene = new Scene(rootLayout);
        loginController.setService(server);
        loginController.setPrimaryStage(primaryStage);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("MENIU");
        primaryStage.show();

        FXMLLoader loaderGame = new FXMLLoader();
        loaderGame.setLocation(getClass().getResource("/view/gameView.fxml"));
        Parent rootGame = loaderGame.load();
        GameController gameController = loaderGame.getController();
        gameController.setService(server);
        gameController.setPrimaryStage(primaryStage);

        loginController.setGameWindow(rootGame);
        loginController.setGameController(gameController);
    }
}
