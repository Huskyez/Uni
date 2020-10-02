package objectprotocol;

import flight.domain.Zbor;

public class GetZboruriResponse implements Response{
    private Iterable<Zbor> zboruri;

    public GetZboruriResponse(Iterable<Zbor> zboruri) {
        this.zboruri = zboruri;
    }

    public Iterable<Zbor> getZboruri(){return zboruri;}
}
