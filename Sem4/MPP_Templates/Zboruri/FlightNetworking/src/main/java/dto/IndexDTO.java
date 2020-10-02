package dto;

import java.io.Serializable;

public class IndexDTO implements Serializable {
    private Integer index;

    public IndexDTO(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
