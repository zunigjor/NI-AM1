package cz.cvut.fit.niam1.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

public class Tour {
    String id;
    String name;
    List<String> customers;

    public Tour(String id, String name, List<String> customers) {
        this.id = id;
        this.name = name;
        this.customers = customers;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCustomers() {
        return customers;
    }

    @JsonIgnore
    public int getWeakHash() {
        return Objects.hash(id, name);
    }

    public void addCustomer(String customer) {
        this.customers.add(customer);
    }
}
