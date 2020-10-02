package objectprotocol;

import flight.domain.Zbor;

public class FindFlightResponse implements Response {
    private Zbor zbor;

    public FindFlightResponse(Zbor zbor) {
        this.zbor = zbor;
    }

    public Zbor getZbor() {
        return zbor;
    }
}
