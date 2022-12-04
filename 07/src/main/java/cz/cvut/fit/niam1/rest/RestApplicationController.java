package cz.cvut.fit.niam1.rest;

import cz.cvut.fit.niam1.rest.models.Tour;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class RestApplicationController {

    final RestApplicationRepository repository;

    public RestApplicationController(RestApplicationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tour/last-modified")
    public ResponseEntity<List<Tour>> getToursLastModified(@RequestHeader Map<String, String> headers) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");

        if (headers.containsKey("If-Modified-Since")) {
            String modifiedSince = headers.get("If-Modified-Since");

            long lastModifiedMiliseconds = format.parse(modifiedSince).getTime();
            if (repository.lastModified <= lastModifiedMiliseconds)
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .lastModified(repository.lastModified)
                .body(repository.getTours());
    }

    @GetMapping("/tour/weak-etag")
    public ResponseEntity<List<Tour>> getToursWeakETag(@RequestHeader Map<String, String> headers){
        List<Tour> tours = repository.getTours();
        int hash = Objects.hash(tours.stream().map(Tour::getWeakHash).toArray());
        String weakETag = String.format("W/\"%d\"", hash);

        if (headers.containsKey("If-None-Match")) {
            String oldWeakETag = headers.get("If-None-Match");
            if (weakETag.equals(oldWeakETag))
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.status(HttpStatus.OK).eTag(weakETag).body(tours);
    }

    @GetMapping("/tour/strong-etag")
    public ResponseEntity<List<Tour>> getToursStrongETag(){
        // Works by using shallowEtagHeaderFilter bean in RestApplicationConfig.java
        List<Tour> tours = repository.getTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @PostMapping("/tour/{id}/customer")
    public ResponseEntity<Tour> addCustomer(@PathVariable String id, @RequestBody String customer) {
        Tour tour = repository.addCustomer(id, customer);
        if (tour != null)
            return new ResponseEntity<>(tour, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}