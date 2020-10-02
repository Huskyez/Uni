package main;

import model.Lucrare;
import org.springframework.web.bind.annotation.*;
import repository.LucrareRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/lucrari")
public class Controller {

    private LucrareRepository repository = new LucrareRepository();

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Iterable<Game> findOne(@PathVariable String username){
//        Iterable<Game> game = repository.findByOne(username);
//        return repository.findByOne(username);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public Lucrare[] findAll() {
        int size = (int) StreamSupport.stream(repository.findAll().spliterator(), false).count();
        Lucrare[] result = new Lucrare[size];
        result = ((List<Lucrare>) repository.findAll()).toArray(result);
        return result;
    }


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public Lucrare[] findLucrariRecorectare(@PathVariable String username){
        Predicate<Lucrare> p1 = lucrare -> lucrare.getCorector1().getUsername().equals(username);
        Predicate<Lucrare> p2 = lucrare -> lucrare.getCorector2().getUsername().equals(username);
        List<Lucrare> lucrari = StreamSupport.stream(repository.findAll().spliterator(),false)
                .filter(p1.or(p2))
                .filter(lucrare -> lucrare.getNota2().equals(Double.valueOf(-1.0)))
                .collect(Collectors.toList());
        int size = lucrari.size();
        Lucrare[] result = new Lucrare[size];
        result = lucrari.toArray(result);
        return result;
    }


}

