package objectprotocol;

import dto.ClientDTO;

public class FindClientRequest implements Request{
    private ClientDTO client;

    public FindClientRequest(ClientDTO client) {
        this.client = client;
    }

    public ClientDTO getClient() {
        return client;
    }
}
