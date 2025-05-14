package models;// Veterinarian.java

public class Veterinarian {
    private String firstName;
    private String lastName;
    private String specialty;

    public Veterinarian(String firstName, String lastName, String specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSpecialty() {
        return specialty;
    }
}