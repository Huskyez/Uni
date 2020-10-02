import domain.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ClientService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ZborApp.xml");
        ClientService service = context.getBean(ClientService.class);
        Client client = context.getBean(Client.class);
        System.out.println(client.getID());
        System.out.println(client.getNume());
        System.out.println(client.getAdresa());
        service.findAll().forEach(x->System.out.println(x));

    }
}
