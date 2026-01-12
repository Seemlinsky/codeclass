package nl.stefans.energy.model;

import java.time.LocalDate;
import java.util.UUID;

public class UsageEntry {

    // Ik geef elke entry een unieke id
    private final String id;

    // Ik sla op bij welke klant dit hoort
    private final String customerId;

    // Ik sla op op welke datum dit verbruik is
    private final LocalDate date;

    // Ik sla op hoeveel stroom verbruikt is in kWh
    private final double electricityKwh;

    // Ik sla op hoeveel gas verbruikt is in m3
    private final double gasM3;

    public UsageEntry(String customerId, LocalDate date, double electricityKwh, double gasM3) {

        // Ik maak hier automatisch een unieke id aan
        this.id = UUID.randomUUID().toString();

        // Ik sla klant-id op zodat ik weet bij wie dit hoort
        this.customerId = customerId;

        // Ik sla de datum op
        this.date = date;

        // Ik sla de stroom op
        this.electricityKwh = electricityKwh;

        // Ik sla het gas op
        this.gasM3 = gasM3;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getElectricityKwh() {
        return electricityKwh;
    }

    public double getGasM3() {
        return gasM3;
    }
}