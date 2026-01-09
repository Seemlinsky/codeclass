package nl.stefans.energy.model.tariff;

import java.time.LocalDate;

public class GasTariff extends Tariff {

    // Ik maak een gastarief aan met: startdatum, prijs per m3, en vaste kosten per maand
    public GasTariff(LocalDate startDate, double unitPrice, double monthlyFixedFee) {

        // Ik geef de waarden door aan Tariff zodat die alles netjes opslaat
        super(startDate, unitPrice, monthlyFixedFee);
    }

    @Override
    public String getType() {

        // Ik zeg hier: dit tarief hoort bij GAS
        return "GAS";
    }
}