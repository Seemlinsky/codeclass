package nl.stefans.energy.model;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    // Ik maak een unieke id die nooit verandert
    private final String id;

    // Ik sla de voornaam op van de klant
    private String firstName;

    // Ik sla de achternaam op van de klant
    private String lastName;

    // Ik sla het adres op van de klant
    private String address;

    public Customer(String firstName, String lastName, String address) {

        // Ik maak hier automatisch een unieke code aan voor deze klant
        this.id = UUID.randomUUID().toString();

        // Ik sla de voornaam op
        this.firstName = firstName;

        // Ik sla de achternaam op
        this.lastName = lastName;

        // Ik sla het adres op
        this.address = address;
    }

    // Ik geef de id terug
    public String getId() {
        return id;
    }

    // Ik geef de voornaam terug
    public String getFirstName() {
        return firstName;
    }

    // Ik geef de achternaam terug
    public String getLastName() {
        return lastName;
    }

    // Ik geef het adres terug
    public String getAddress() {
        return address;
    }

    // Ik maak een handige naam voor in de UI (voornaam + achternaam)
    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {

        // Als dit exact hetzelfde object is, dan is het gelijk
        if (this == o) return true;

        // Als het geen Customer is, is het nooit gelijk
        if (!(o instanceof Customer customer)) return false;

        // Ik vergelijk klanten op id, want id is uniek
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {

        // Ik maak een hashcode op basis van id, zodat sets/lijsten goed werken
        return Objects.hash(id);
    }
}