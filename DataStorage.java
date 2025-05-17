package main;

import java.util.*;
import java.util.stream.Collectors;
import models.*;

public class DataStorage {
    private static Map<String, Owner> owners = new HashMap<>();
    public static List<Veterinarian> veterinarians = new ArrayList<>();


    public static void addOwner(Owner owner) {
        owners.put(owner.getFullName().toLowerCase(), owner);
    }

    public static void removeOwner(String fullName) {
        owners.remove(fullName.toLowerCase());
    }


    public static List<Owner> searchOwnersByLastName(String lastName) {
        return owners.values().stream()
                .filter(o -> o.getFullName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

 
    public static List<Owner> getOwners() {
        return new ArrayList<>(owners.values());
    }


    public static void initVeterinarians() {
        veterinarians.add(new Veterinarian("Marıam", "Smith", "Dentisterie"));
        veterinarians.add(new Veterinarian("John", "Miller", "Chirurgie"));
        veterinarians.add(new Veterinarian("Emma", "Johnson", "Dermatologie"));
        veterinarians.add(new Veterinarian("Robert", "Brown", "Cardiologie"));
        veterinarians.add(new Veterinarian("Isabelle", "Martin", "Ophtalmologie"));
        veterinarians.add(new Veterinarian("William", "Garcia", "Neurologie"));
        veterinarians.add(new Veterinarian("Olivia", "Davis", "Urgences"));

    }

}
