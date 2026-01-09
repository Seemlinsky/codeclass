package nl.stefans.energy.model.tariff;

import java.time.LocalDate;

public class ElectricityTariff extends Tariff {

    // Ik maak een elektriciteitstarief aan met: startdatum, prijs per kWh, en vaste kosten per maand
    public ElectricityTariff(LocalDate startDate, double unitPrice, double monthlyFixedFee) {

        // Ik geef deze waarden door aan de "basis" class Tariff, zodat Tariff ze opslaat
        super(startDate, unitPrice, monthlyFixedFee);
    }

    @Override
    public String getType() {

        // Ik zeg hier: dit tarief hoort bij ELECTRICITY (stroom)
        return "ELECTRICITY";
    }
}