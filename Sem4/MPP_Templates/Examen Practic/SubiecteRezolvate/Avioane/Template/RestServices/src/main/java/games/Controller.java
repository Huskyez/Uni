package games;

import model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.GameRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class Controller {

    private GameRepository repository = new GameRepository();

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable String username){
        int size = (int) StreamSupport.stream(repository.findOne(username).spliterator(), false).count();
        Game[] result = new Game[size];
        result = ((List<Game>) repository.findOne(username)).toArray(result);
        if(size == 0)
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
