package flight.services.rest;

import flight.domain.Zbor;
import flight.repository.ZborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/flight/zboruri")
@CrossOrigin
public class FlightController {
    private static final String template = "Hello, %s!";

    @Autowired
    private ZborRepository zborRepository;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format(template, name);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Zbor> getAll(){return zborRepository.findAll();}

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Zbor zbor = zborRepository.findOne(id);
        if(zbor == null)
            return new ResponseEntity<>("Flight not found",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(zbor,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Zbor create(@RequestBody Zbor zbor){
        String s = zbor.getPlecare().toString();
        String f = "";
        for(int i= 0; i<=15;i++)
            f+=s.charAt(i);
        f.replace(' ','T');
        zbor.setPlecare(LocalDateTime.parse(f));
        if(zbor.getId() == null)
            zbor.setId(17);
        zborRepository.save(zbor);
        return zbor;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Zbor update(@RequestBody Zbor zbor){
        System.out.println("Updating flight ...");
        if(zbor.getId() ==  null)
            zbor.setId(17);
        zborRepository.updateZbor(zbor.getId(),zbor.getLocuri());
        return zbor;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("Deleting flight with id ..." + id);
        try{
            zborRepository.delete(id);
            return new ResponseEntity<Zbor>(HttpStatus.OK);
        }catch(Exception ex){
            System.out.println("Ctrl delete exception");
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

//    @RequestMapping(value = "/{param1}", method = RequestMethod.GET)
//    public ResponseEntity<?> getByPlecareAndDate(@PathVariable String param){
//        String[] params = param.split(",");
//        String destinatie = params[0];
//        String plecare = params[1];
//        Zbor zbor = zborRepository.findOneByDestinatieAndDateTime(destinatie,plecare);
//        if(zbor == null)
//            return new ResponseEntity<>("Flight not found",HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(zbor,HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{param2}", method = RequestMethod.GET)
//    public ResponseEntity<?> getByPlecareAndHour(@PathVariable String param){
//        String[] params = param.split(",");
//        String destinatie = params[0];
//        String plecare = params[1];
//        Iterable<Zbor> zboruri = zborRepository.findByDestinatieAndPlecare(destinatie,plecare);
//        if(zboruri == null)
//            return new ResponseEntity<>("Flight not found",HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(zboruri,HttpStatus.OK);
//    }

}
