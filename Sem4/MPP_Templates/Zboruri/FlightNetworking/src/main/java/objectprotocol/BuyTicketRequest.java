package objectprotocol;

import flight.domain.Bilet;

public class BuyTicketRequest implements Request{
    private Bilet bilet;
    private int id;

    public BuyTicketRequest(Bilet bilet, int id){this.bilet = bilet;this.id =id;}

    public Bilet getBilet(){return bilet;}

    public int getId(){return id;}
}
