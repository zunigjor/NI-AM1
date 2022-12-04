

package cz.cvut.fit.niam1.rest.models;

public class Tour {
    String id;
    String name;

    public Tour(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}