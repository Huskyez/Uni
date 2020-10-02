import controller.LoginController;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IServerImpl;


public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IServerImpl server = (IServerImpl) factory.getBean("serverService");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/loginView.fxml"));
        Parent rootLayout = loader.load();
        LoginController loginController = loader.getController();
        Scene loginScene = new Scene(rootLayout);
        loginController.setService(server);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        FXMLLoader loaderMain = new FXMLLoader();
        loaderMain.setLocation(getClass().getResource("/view/mainView.fxml"));
        Parent rootMain = loaderMain.load();
        Scene mainScene = new Scene(rootMain);
        MainController mainController = loaderMain.getController();
        mainController.setServer(server);
        mainController.setPrimaryStage(primaryStage);
        mainController.setLoginScene(loginScene);
        mainController.setRoot(rootMain);

        loginController.setMainScene(mainScene);
        loginController.setPrimaryStage(primaryStage);
        loginController.setMainController(mainController);
    }
}
