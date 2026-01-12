package nl.stefans.energy.repository;

import nl.stefans.energy.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    // Ik bewaar hier alle klanten in een lijst (in-memory, dus niet database)
    private final List<Customer> customers = new ArrayList<>();

    public CustomerRepository() {

        // Ik voeg hier demo klanten toe zodat de app meteen iets kan tonen
        add(new Customer("Jan", "Jansen", "Hoofdstraat 1"));
        add(new Customer("Fatima", "El Amrani", "Kerklaan 12"));
        add(new Customer("Noah", "De Vries", "Dorpsweg 8"));
    }

    public List<Customer> findAll() {

        // Ik geef een kopie terug zodat niemand per ongeluk mijn originele lijst kan slopen
        return List.copyOf(customers);
    }

    public void add(Customer customer) {

        // Ik voeg een klant toe aan de lijst
        customers.add(customer);
    }

    public Optional<Customer> findById(String id) {

        // Ik zoek in de lijst naar een klant met dezelfde id
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}