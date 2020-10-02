package start;

import flight.domain.Zbor;
import flight.services.rest.ServiceException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rest.client.FlightClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class StartRestClient {
    private final static FlightClient flightClient = new FlightClient();

    public static void main(String[] args) {
        String str = "1986-04-08T12:30";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Zbor zbor1 = new Zbor(1, "Moscova", dateTime, "GVA", 100);
        Zbor zbor2 = new Zbor(2, "Moscova", dateTime, "GVA", 200);
        Zbor zbor3 = new Zbor(3, "Moscova", dateTime, "GVA", 300);
        Zbor zbor4 = new Zbor(1, "Moscova", dateTime, "GVA", 400);
        Zbor zbor5 = new Zbor(2, "Moscova", dateTime, "GVA", 200);
        try{
            show(()-> System.out.println(flightClient.create(zbor1)));
            show(()-> System.out.println(flightClient.create(zbor2)));
            show(()-> System.out.println(flightClient.create(zbor3)));

            show(()->{
                Zbor[] res=flightClient.getAll();
               for(Zbor z:res){
                    System.out.println(z.getId()+ "  " +z.getAeroport());
                }
            });

            show(()-> System.out.println(flightClient.update(zbor4)));

            show(()->{
                Zbor[] res=flightClient.getAll();
                for(Zbor z:res){
                    System.out.println(z.getId()+ "  " +z.getAeroport());
                }
            });

            show(()-> System.out.println(flightClient.findOne(zbor2.getId())));

            show(()-> System.out.println(flightClient.delete(zbor5)));

            show(()->{
                Zbor[] res=flightClient.getAll();
                for(Zbor z:res){
                    System.out.println(z.getId()+ "  " +z.getAeroport());
                }
            });

        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }
    }
    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            System.out.println("Service exception "+ e);
        }
    }

}

