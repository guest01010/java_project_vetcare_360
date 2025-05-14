package models;// Pet.java
import java.util.*;

public class Pet {
    private String name;
    private String type;
    private List<Visit> visits = new ArrayList<>();

    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        return visits;
    }
}