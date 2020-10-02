package services;

import flight.domain.*;
import flight.domain.*;

public interface IFlightServer {
    void logIn(Angajat angajat, IFlightObserver observer) throws FlightException;

    void logOut(Angajat angajat, IFlightObserver observer) throws FlightException;

    Client findClient(String numeClient, String adresaClient) throws FlightException;

    Zbor findZbor(String destinatie, String data, String ora) throws FlightException;

    Integer findIndex() throws FlightException;

    void cumparaBilet(Bilet bilet, int idZbor) throws FlightException;

    Iterable<Zbor> getZboruri() throws FlightException;

    Iterable<ZborDTO> getZboruriCautare(String destinatie, String plecare) throws FlightException;

    void zborUpdate(Integer zborID, Integer locuri) throws FlightException;
}
