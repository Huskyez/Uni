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
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FlightServerObjectProxy implements IFlightServer {
    private String host;
    private int port;

    IFlightObserver client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Object> qresponses;
    private volatile boolean finished;

    public FlightServerObjectProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<>();
    }

    @Override
    public void logIn(Angajat angajat, IFlightObserver observer) throws FlightException {
        initializeConnection();
        sendRequest(new LoginRequest(angajat));
        Response response = readResponse();
        if (response instanceof OkResponse) {
            this.client = observer;
            return;
        }
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            closeConnection();
            throw new FlightException(err.getMessage());
        }
    }

    @Override
    public void logOut(Angajat angajat, IFlightObserver observer) throws FlightException {
        sendRequest(new LogoutRequest(angajat));
        Response response = readResponse();
        closeConnection();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
    }

    @Override
    public Iterable<Zbor> getZboruri() throws FlightException {
        sendRequest(new GetZboruriRequest());
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
        GetZboruriResponse resp = (GetZboruriResponse) response;
        Iterable<Zbor> zboruri = resp.getZboruri();
        return zboruri;
    }

    @Override
    public Iterable<ZborDTO> getZboruriCautare(String destinatie, String plecare) throws FlightException {
        sendRequest(new GetZboruriCautateRequest(new ZborDTO2(destinatie, plecare)));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
        GetZboruriCautateResponse resp = (GetZboruriCautateResponse) response;
        Iterable<ZborDTO> zborDTOS = resp.getZboruri();
        return zborDTOS;
    }

    @Override
    public void zborUpdate(Integer zborID, Integer locuri) throws FlightException {
        sendRequest(new ZborUpdateRequest(new ZborDTO4(zborID, locuri)));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
    }

    @Override
    public Client findClient(String numeClient, String adresaClient) throws FlightException {
        sendRequest(new FindClientRequest(new ClientDTO(numeClient, adresaClient)));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
        FindClientResponse findClientResponse = (FindClientResponse) response;
        Client client = findClientResponse.getClient();
        return client;
    }

    @Override
    public Zbor findZbor(String destinatie, String data, String ora) throws FlightException {
        sendRequest(new FindFlightRequest(new ZborDTO3(destinatie, data, ora)));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
        FindFlightResponse findFlightResponse = (FindFlightResponse) response;
        Zbor zbor = findFlightResponse.getZbor();
        return zbor;
    }

    @Override
    public Integer findIndex() throws FlightException {
        sendRequest(new FindIndexRequest());
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
        FindIndexResponse findIndexResponse = (FindIndexResponse) response;
        IndexDTO index = findIndexResponse.getIndex();
        return index.getIndex();
    }

    @Override
    public void cumparaBilet(Bilet bilet, int id) throws FlightException {
        sendRequest(new BuyTicketRequest(bilet, id));
        Response response = readResponse();
        if (response instanceof ErrorResponse) {
            ErrorResponse err = (ErrorResponse) response;
            throw new FlightException(err.getMessage());
        }
    }

    private void sendRequest(Request request) throws FlightException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new FlightException("Error sending object " + e);
        }
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = (Response) qresponses.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate(UpdateResponse update){
        BuyTicketResponse response = (BuyTicketResponse) update;
        Iterable<Zbor> zboruri = response.getZboruri();
        System.out.println("Ticket Bought");
        try {
            client.ticketBought(zboruri);
        }catch (FlightException e){
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader() {
        Thread tw = new Thread(new ThreadReader());
        tw.start();
    }


    private class ThreadReader implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("Response received " + response);
                    if (response instanceof UpdateResponse) {
                        handleUpdate((UpdateResponse) response);
                    } else
                        try {
                        qresponses.put(response);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

