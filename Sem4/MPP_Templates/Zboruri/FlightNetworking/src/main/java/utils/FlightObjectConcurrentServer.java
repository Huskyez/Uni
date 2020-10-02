package utils;

import objectprotocol.FlightClientObjectWorker;
import services.IFlightObserver;
import services.IFlightServer;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FlightObjectConcurrentServer extends AbsConcurrentServer {
    IFlightServer flightServer;
    public FlightObjectConcurrentServer(int port, IFlightServer flightServer) {
        super(port);
        this.flightServer = flightServer;
        System.out.println("Flight- FlightObjectConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        FlightClientObjectWorker worker = new FlightClientObjectWorker(flightServer, client);
        Thread tw = new Thread(worker);
        return tw;
    }
}
