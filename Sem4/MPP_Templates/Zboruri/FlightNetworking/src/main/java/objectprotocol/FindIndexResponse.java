package objectprotocol;

import dto.IndexDTO;

public class FindIndexResponse implements Response{
    private IndexDTO index;

    public FindIndexResponse(IndexDTO index) {
        this.index = index;
    }

    public IndexDTO getIndex() {
        return index;
    }
}
