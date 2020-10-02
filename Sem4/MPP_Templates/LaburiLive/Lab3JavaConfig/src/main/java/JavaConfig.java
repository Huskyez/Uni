import domain.Client;
import domain.Zbor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import repository.ClientRepository;
import repository.Repository;
import repository.ZborRepository;
import service.ClientService;


import java.io.IOException;
import java.util.Properties;

@Configuration
public class JavaConfig {
    @Bean
    @Primary
    public Properties jdbcProps(){
        Properties serverProps=new Properties();
        try {
            serverProps.load(getClass().getResourceAsStream("/database.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        return  serverProps;
    }

    @Bean(name="clientRepo")
    public ClientRepository createRepo(Properties jdbcProps){
        return new ClientRepository(jdbcProps);
    }

    @Bean(name="zborService")
    public ClientService createService(ClientRepository repo){
        return new ClientService(repo);
    }

    @Bean(name="client")
    public Client createClient(){
        return new Client(1,"Tamas Adrian","Grigorescu, Cluj");
    }

}
