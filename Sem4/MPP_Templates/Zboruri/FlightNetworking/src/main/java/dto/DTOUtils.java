package dto;

import flight.domain.Bilet;

public class DTOUtils {
    public static BiletDTO getDTO(Bilet bilet){
        Integer id = bilet.getZborID();
        Integer locuri = bilet.getLocuri();
        return new BiletDTO(id,locuri);
    }
}
