package objectprotocol;

import dto.ZborDTO2;

public class GetZboruriCautateRequest implements Request {
    private ZborDTO2 zborDTO2;

    public GetZboruriCautateRequest(ZborDTO2 zborDTO2) {
        this.zborDTO2 = zborDTO2;
    }

    public ZborDTO2 getZborDTO2() {
        return zborDTO2;
    }
}
