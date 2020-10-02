package objectprotocol;

import flight.domain.*;
import dto.*;
import flight.domain.*;
import services.FlightException;
import services.IFlightObserver;
import services.IFlightServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FlightClientObjectWorker implements Runnable, IFlightObserver {
    private IFlightServer server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public FlightClientObjectWorker(IFlightServer server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try{
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while(connected){
            try{
                Object request = input.readObject();
                Object response = handleRequest((Request) request);
                if(response!=null){
                    sendResponse((Response) response);
                }
            }catch(IOException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (FlightException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("Sending response " + response);
        output.writeObject(response);
        output.flush();
    }

    private Response handleRequest(Request request) throws FlightException {
        Response response = null;
        if(request instanceof LoginRequest){
            System.out.println("Login request ...");
            LoginRequest logReq = (LoginRequest) request;
            Angajat angajat = logReq.getUser();
            try{
                server.logIn(angajat, this);
                return new OkResponse();
            } catch (FlightException e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof LogoutRequest){
            System.out.println("LogoutRequest");
            LogoutRequest logReq = (LogoutRequest) request;
            Angajat angajat = logReq.getUser();
            try{
                server.logOut(angajat,this);
                connected = false;
                return new OkResponse();
            }catch (FlightException e){
                return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof GetZboruriRequest){
            System.out.println("GetZboruriRequest");
            try{
                Iterable<Zbor> zboruri = server.getZboruri();
                return new GetZboruriResponse(zboruri);
            } catch (FlightException e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof GetZboruriCautateRequest){
            GetZboruriCautateRequest getZboruriCautateRequest = (GetZboruriCautateRequest) request;
            ZborDTO2 zborDTO2 = getZboruriCautateRequest.getZborDTO2();
            System.out.println("GetZboruriCautateRequest");
            try{
                Iterable<ZborDTO> zboruri = server.getZboruriCautare(zborDTO2.getDestinatie(),zborDTO2.getPlecare());
                return new GetZboruriCautateResponse(zboruri);
            } catch (FlightException e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof FindClientRequest){
            FindClientRequest findClientRequest = (FindClientRequest) request;
            ClientDTO client = findClientRequest.getClient();
            System.out.println("FindClientRequest");
            try{
                Client client1 = server.findClient(client.getNume(),client.getAdresa());
                return new FindClientResponse(client1);
            } catch (FlightException e) {
               return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof FindFlightRequest){
            FindFlightRequest findFlightRequest = (FindFlightRequest) request;
            ZborDTO3 zborDTO3 = findFlightRequest.getZborDTO3();
            System.out.println("FindFlightRequest");
            try{
                Zbor zbor = server.findZbor(zborDTO3.getDestinatie(),zborDTO3.getData(),zborDTO3.getOra());
                return new FindFlightResponse(zbor);
            } catch (FlightException e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof FindIndexRequest){
            System.out.println("FindIndexRequest");
            try{
                Integer index = server.findIndex();
                return new FindIndexResponse(new IndexDTO(index));
            } catch (FlightException e) {
                return new ErrorResponse(e.getMessage());
            }
        }

        if(request instanceof BuyTicketRequest){
            BuyTicketRequest buyTicketRequest = (BuyTicketRequest) request;
            Bilet bilet = buyTicketRequest.getBilet();
            int id = buyTicketRequest.getId();
            System.out.println("BuyTicketRequest");
            server.cumparaBilet(bilet, id);
            return new OkResponse();
        }

        if (request instanceof ZborUpdateRequest){
            ZborUpdateRequest zborUpdateRequest = (ZborUpdateRequest) request;
            ZborDTO4 zborDTO4 = zborUpdateRequest.getZborDTO4();
            System.out.println("ZborUpdateRequest");
            try{
                server.zborUpdate(zborDTO4.getZborID(),zborDTO4.getLocuri());
                return new ZborUpdateResponse();
            } catch (FlightException e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public void ticketBought(Iterable<Zbor> zboruri) {
        System.out.println("Ticket has been bought");
        try {
            sendResponse(new BuyTicketResponse(zboruri));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
