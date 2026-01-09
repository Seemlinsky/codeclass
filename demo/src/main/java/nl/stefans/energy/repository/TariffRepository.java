package nl.stefans.energy.repository;

import nl.stefans.energy.model.tariff.ElectricityTariff;
import nl.stefans.energy.model.tariff.GasTariff;
import nl.stefans.energy.model.tariff.Tariff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TariffRepository {

    // Ik bewaar alle tarieven in één lijst
    private final List<Tariff> tariffs = new ArrayList<>();

    public TariffRepository() {

        // Ik zet hier demo tarieven neer zodat de berekening iets heeft om mee te werken
        // (dit moet je later aanpassen aan je echte case)
        tariffs.add(new ElectricityTariff(LocalDate.of(2025, 1, 1), 0.40, 6.50));
        tariffs.add(new GasTariff(LocalDate.of(2025, 1, 1), 1.25, 5.00));
    }

    public void add(Tariff tariff) {

        // Ik voeg een nieuw tarief toe aan de lijst
        tariffs.add(tariff);
    }

    public Optional<Tariff> getLatestTariff(String type) {

        // Ik zoek het laatste tarief van een bepaald type (GAS of ELECTRICITY)
        return tariffs.stream()
                .filter(t -> t.getType().equals(type))
                .max(Comparator.comparing(Tariff::getStartDate));
    }

    public List<Tariff> findAll() {

        // Ik geef een kopie terug zodat de originele lijst veilig blijft
        return List.copyOf(tariffs);
    }
}