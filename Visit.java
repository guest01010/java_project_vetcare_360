package models;// Visit.java
import java.time.LocalDate;

public class Visit {
    private LocalDate date;
    private String description;

    public Visit(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}