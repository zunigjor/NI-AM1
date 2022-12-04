package cz.cvut.fit.niam1.rest;

import cz.cvut.fit.niam1.rest.models.Tour;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApplicationController {
    final RestApplicationRepository repository;

    public RestApplicationController(RestApplicationRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/tour")
    @ResponseStatus(HttpStatus.OK)
    public List<Tour> getTours() {
        return repository.getTours();
    }

    @GetMapping(value = "/tour/awaiting-delete")
    @ResponseStatus(HttpStatus.OK)
    public List<Tour> getAwaitingDeleteTour() {
        return repository.getAwaitingDeleteTours();
    }

    @PostMapping(value = "/tour")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour created = repository.addTour(tour);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/tour/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tour> getTour(@PathVariable String id) {
        Tour tour = repository.getTourById(id);

        if (tour == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @DeleteMapping("/tour/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tour> deleteTour(@PathVariable String id) {
        boolean deleted = repository.deleteTour(id);
        if (!deleted)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}