package objectprotocol;

import flight.domain.ZborDTO;

public class GetZboruriCautateResponse implements Response{
    private Iterable<ZborDTO> zboruri;

    public GetZboruriCautateResponse(Iterable<ZborDTO> zboruri) {
        this.zboruri = zboruri;
    }

    public Iterable<ZborDTO> getZboruri(){return zboruri;}
}
