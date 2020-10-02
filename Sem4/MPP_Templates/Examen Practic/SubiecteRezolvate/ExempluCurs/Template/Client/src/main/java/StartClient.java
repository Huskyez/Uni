import controller.CorectorController;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Corector;
import model.Lucrare;
import model.Participant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IServerImpl;
import service.ServerImpl;

public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IServerImpl server = (IServerImpl) factory.getBean("serverService");

//        Corector corector1 = new Corector("a", "a");
//        Corector corector2 = new Corector("b", "b");
//        server.saveCorector(corector1);
//        server.saveCorector(corector2);
//        Participant participant1 = new Participant(1, "Adrian");
//        server.saveParticipant(participant1);
//        server.saveParticipant(new Participant(2, "Ciprian"));
//        server.saveLucrare(new Lucrare(1, corector1, corector2, participant1));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/loginView.fxml"));
        Parent rootLayout = loader.load();
        LoginController loginController = loader.getController();
        Scene loginScene = new Scene(rootLayout);
        loginController.setService(server);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("MENIU");
        primaryStage.show();

        FXMLLoader loaderCorector = new FXMLLoader();
        loaderCorector.setLocation(getClass().getResource("/view/corectorView.fxml"));
        Parent rootCorector = loaderCorector.load();
        CorectorController corectorController = loaderCorector.getController();
        corectorController.setService(server);
        corectorController.setPrimaryStage(primaryStage);
        corectorController.setLoginScene(loginScene);

        Scene corectorScene = new Scene(rootCorector);
        loginController.setSceneCorector(corectorScene);

        loginController.setParent(rootCorector);
        loginController.setPrimaryStage(primaryStage);
        loginController.setCorectorController(corectorController);
    }
}
