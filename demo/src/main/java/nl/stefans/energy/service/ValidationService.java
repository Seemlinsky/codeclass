package nl.stefans.energy.service;

import java.time.LocalDate;

public class ValidationService {

    public void validateUsage(LocalDate date, double electricityKwh, double gasM3) {

        // Ik check of de datum is ingevuld
        if (date == null) {
            throw new IllegalArgumentException("Datum is verplicht.");
        }

        // Ik check of de datum niet in de toekomst ligt
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datum mag niet in de toekomst liggen.");
        }

        // Ik check of stroom niet negatief is
        if (electricityKwh < 0) {
            throw new IllegalArgumentException("Stroomverbruik (kWh) mag niet negatief zijn.");
        }

        // Ik check of gas niet negatief is
        if (gasM3 < 0) {
            throw new IllegalArgumentException("Gasverbruik (m³) mag niet negatief zijn.");
        }
    }
}