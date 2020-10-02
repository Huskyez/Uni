package objectprotocol;

import dto.ZborDTO4;

public class ZborUpdateRequest implements Request {
    private ZborDTO4 zborDTO4;

    public ZborUpdateRequest(ZborDTO4 zborDTO4) {
        this.zborDTO4 = zborDTO4;
    }

    public ZborDTO4 getZborDTO4() {
        return zborDTO4;
    }
}
