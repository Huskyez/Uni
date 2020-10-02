package services;

import flight.domain.Zbor;

public interface IFlightObserver {
    void ticketBought(Iterable<Zbor> zboruri) throws FlightException;
}
