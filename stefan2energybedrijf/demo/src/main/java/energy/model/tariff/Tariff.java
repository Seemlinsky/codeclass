package nl.stefans.energy.model.tariff;

import java.time.LocalDate;

public abstract class Tariff {

    // Ik sla hier op vanaf wanneer dit tarief geldt
    private final LocalDate startDate;

    // Ik sla hier op hoeveel 1 eenheid kost (bijv kWh of m3)
    private final double unitPrice;

    // Ik sla hier het vaste bedrag op dat je per maand betaalt
    private final double monthlyFixedFee;

    // Ik maak een constructor die de basisgegevens van een tarief opslaat
    protected Tariff(LocalDate startDate, double unitPrice, double monthlyFixedFee) {

        // Ik bewaar de startdatum in dit object
        this.startDate = startDate;

        // Ik bewaar de prijs per eenheid in dit object
        this.unitPrice = unitPrice;

        // Ik bewaar de vaste maandkosten in dit object
        this.monthlyFixedFee = monthlyFixedFee;
    }

    // Ik geef de startdatum terug wanneer iemand dat opvraagt
    public LocalDate getStartDate() {
        return startDate;
    }

    // Ik geef de prijs per eenheid terug
    public double getUnitPrice() {
        return unitPrice;
    }

    // Ik geef de vaste maandkosten terug
    public double getMonthlyFixedFee() {
        return monthlyFixedFee;
    }

    // Dit moet elke sub-class invullen: of het GAS of ELECTRICITY is
    public abstract String getType();
}