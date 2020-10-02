package objectprotocol;

import flight.domain.Client;

public class FindClientResponse implements Response{
    private Client client;

    public FindClientResponse(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
