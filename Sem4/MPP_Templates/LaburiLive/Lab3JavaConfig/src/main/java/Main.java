import domain.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ClientService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ClientService service = context.getBean(ClientService.class);
        service.findAll().forEach(x->System.out.println(x));
        Client client = context.getBean(Client.class);
        System.out.println(client.getID());
        System.out.println(client.getNume());
        System.out.println(client.getAdresa());
    }
}
