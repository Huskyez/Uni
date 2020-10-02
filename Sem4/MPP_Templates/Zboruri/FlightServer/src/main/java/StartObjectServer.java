import flight.repository.*;
import org.springframework.stereotype.Component;
import flight.repository.*;
import server.FlightServerImpl;
import services.IFlightServer;
import utils.AbstractServer;
import utils.FlightObjectConcurrentServer;
import utils.ServerException;

import java.io.IOException;
import java.util.Properties;

public class StartObjectServer {
    private static int defaultPort=55555;
    public static Properties serverProps;
    public static void main(String[] args) {
        serverProps=new Properties();
        try {
            serverProps.load(StartObjectServer.class.getResourceAsStream("/flightserver.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatserver.properties "+e);
            return;
        }
        AngajatRepository angajatRepository = new AngajatRepository();
        ClientRepoInterface clientRepository = new ClientRepository(serverProps);
        ZborRepoInterface zborRepository = new ZborRepository();
        BiletRepoInterface biletRepository = new BiletRepository(serverProps);

        IFlightServer flightServerImpl = new FlightServerImpl(angajatRepository,zborRepository,clientRepository,biletRepository);

        int flightServerPort=defaultPort;
        try {
            flightServerPort = Integer.parseInt(serverProps.getProperty("flight.server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number"+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+flightServerPort);
        AbstractServer server = new FlightObjectConcurrentServer(flightServerPort, flightServerImpl);
        try {
            server.start();
        } catch (ServerException e) {
            e.printStackTrace();
        }
//        try {
//            ThriftServer service = new ThriftServer(angajatRepository, zborRepository, clientRepository, biletRepository);
//            ServiceThrift.Processor processor = new ServiceThrift.Processor(service);
//            TServerTransport serverTransport = new TServerSocket(55555);
//            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
//            System.out.println("Server running...");
//            server.serve();
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
    }
}
