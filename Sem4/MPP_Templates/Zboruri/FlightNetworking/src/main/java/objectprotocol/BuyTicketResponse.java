package objectprotocol;

import flight.domain.Zbor;

public class BuyTicketResponse implements UpdateResponse {
    private Iterable<Zbor> zboruri;

    public BuyTicketResponse(Iterable<Zbor> zboruri){this.zboruri = zboruri;}

    public Iterable<Zbor> getZboruri(){return zboruri;}


}
