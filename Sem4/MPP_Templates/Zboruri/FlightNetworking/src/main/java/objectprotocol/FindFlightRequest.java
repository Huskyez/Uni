package objectprotocol;

import dto.ZborDTO3;

public class FindFlightRequest implements Request{
    private ZborDTO3 zborDTO3;

    public FindFlightRequest(ZborDTO3 zborDTO3) {
        this.zborDTO3 = zborDTO3;
    }

    public ZborDTO3 getZborDTO3() {
        return zborDTO3;
    }
}
