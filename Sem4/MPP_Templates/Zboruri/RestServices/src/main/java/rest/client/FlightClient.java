package rest.client;

import flight.domain.Zbor;
import flight.services.rest.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class FlightClient {
    public static final String URL = "http://localhost:8080/flight/zboruri";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable){
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Zbor[] getAll(){return execute(()->restTemplate.getForObject(URL,Zbor[].class));}

    public Zbor create(Zbor zbor){return execute(()->restTemplate.postForObject(URL, zbor, Zbor.class));}

    public Zbor update(Zbor zbor){return execute(()-> {
        restTemplate.put(URL+'/'+zbor.getId(),zbor);
        return zbor;
    });}

    public ResponseEntity<Zbor> delete(Zbor zbor){return  execute(()->{
        restTemplate.delete(URL+'/'+zbor.getId(),zbor);
        return new ResponseEntity<>(zbor, HttpStatus.OK);
    });}

    public Zbor findOne(Integer id){
        return execute(()->restTemplate.getForObject(URL+'/'+id.toString(),Zbor.class));
    }
}
