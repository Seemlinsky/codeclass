package nl.stefans.energy.service;

import nl.stefans.energy.model.UsageEntry;
import nl.stefans.energy.model.tariff.Tariff;
import nl.stefans.energy.repository.TariffRepository;
import nl.stefans.energy.repository.UsageRepository;

import java.util.List;

public class EnergyService {

    // Ik heb deze repositories nodig om tarieven en verbruik op te halen
    private final TariffRepository tariffRepository;
    private final UsageRepository usageRepository;

    public EnergyService(TariffRepository tariffRepository, UsageRepository usageRepository) {

        // Ik sla de repositories op zodat ik ze later kan gebruiken
        this.tariffRepository = tariffRepository;
        this.usageRepository = usageRepository;
    }

    public double calculateElectricityCost(String customerId) {

        // Ik haal het nieuwste elektriciteitstarief op
        Tariff t = tariffRepository.getLatestTariff("ELECTRICITY")
                .orElseThrow(() -> new IllegalStateException("Geen elektriciteitstarief gevonden."));

        // Ik haal al het verbruik van deze klant op
        List<UsageEntry> usage = usageRepository.findByCustomerId(customerId);

        // Ik tel alle kWh bij elkaar op
        double units = usage.stream()
                .mapToDouble(UsageEntry::getElectricityKwh)
                .sum();

        // Ik bereken variabele kosten: kWh * prijs per kWh
        double variable = units * t.getUnitPrice();

        // Ik bereken vaste kosten (simpel): 1 maand vaste kosten per entry
        // (dit is een schatting en moet je aanpassen als je case anders werkt)
        double fixed = usage.size() * t.getMonthlyFixedFee();

        // Ik geef totale stroomkosten terug
        return variable + fixed;
    }

    public double calculateGasCost(String customerId) {

        // Ik haal het nieuwste gastarief op
        Tariff t = tariffRepository.getLatestTariff("GAS")
                .orElseThrow(() -> new IllegalStateException("Geen gastarief gevonden."));

        // Ik haal het verbruik van deze klant op
        List<UsageEntry> usage = usageRepository.findByCustomerId(customerId);

        // Ik tel alle m3 gas bij elkaar op
        double units = usage.stream()
                .mapToDouble(UsageEntry::getGasM3)
                .sum();

        // Ik bereken variabele kosten: m3 * prijs per m3
        double variable = units * t.getUnitPrice();

        // Ik bereken vaste kosten (simpel): 1 maand vaste kosten per entry
        double fixed = usage.size() * t.getMonthlyFixedFee();

        // Ik geef totale gaskosten terug
        return variable + fixed;
    }

    public double calculateTotalCost(String customerId) {

        // Ik tel stroomkosten en gaskosten bij elkaar op voor het totaal
        return calculateElectricityCost(customerId) + calculateGasCost(customerId);
    }
}