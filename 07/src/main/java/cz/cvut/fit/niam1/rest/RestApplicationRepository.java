package cz.cvut.fit.niam1.rest;


import cz.cvut.fit.niam1.rest.models.Tour;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RestApplicationRepository {

    long lastModified;
    private static final List<Tour> tours = new ArrayList<>();

    @PostConstruct
    public void initRepo() {
        tours.add(new Tour("1", "City tour", new ArrayList<>(Arrays.asList("Jorge", "Franta"))));
        tours.add(new Tour("2", "Village tour", new ArrayList<>(Arrays.asList("Pepa", "Ondra"))));

        updateLastModified();
    }

    private void updateLastModified() {
        lastModified = System.currentTimeMillis();
    }

    public List<Tour> getTours() {
        return tours;
    }

    public Tour getTourById(String id) {
        return tours.stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
    }

    public Tour addCustomer(String id, String customer) {
        Tour tour = getTourById(id);
        tour.addCustomer(customer);
        updateLastModified();
        return tour;
    }
}