package main;

import model.Carte;
import model.Joc;
import model.JocDTO;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.JocRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/jocuri")
public class Controller {

    private JocRepository jocRepository = new JocRepository();


    @RequestMapping(value = "/{idJoc}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable Integer idJoc){

        Joc joc = jocRepository.findOne(idJoc);

        if(joc == null){
            return new ResponseEntity<>("Niciun joc disponibil !", HttpStatus.NOT_FOUND);
        }
        else{
            Map<String, List<Carte>> map = new HashMap<>();

            String[] participanti = joc.getParticipanti().split("/");
            String[] cartiVirgula = joc.getCartiServer().split("/");
            List<String> allParticipanti = new ArrayList<>();
            for(String s : participanti){
                allParticipanti.add(s);
                String cartiParticipant = cartiVirgula[allParticipanti.indexOf(s)];
                List<Carte> cPart = new ArrayList<>();
                for(String c : cartiParticipant.split(",")){
                    cPart.add(new Carte(c));
                }
                map.put(s, cPart);
            }

            String[] cartiCastigate = joc.getCartiCastigate().split(",");
            List<Carte> cPart = new ArrayList<>();
            for(String c : cartiCastigate){
                cPart.add(new Carte(c));
            }

            return new ResponseEntity<>(new JocDTO(joc.getId(), allParticipanti, map, joc.getCastigator(), cPart), HttpStatus.OK);
        }
    }

}
