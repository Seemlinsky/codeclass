package nl.stefans.energy.repository;

import nl.stefans.energy.model.UsageEntry;

import java.util.ArrayList;
import java.util.List;

public class UsageRepository {

    // Ik bewaar hier alle verbruik entries in een lijst (in-memory)
    private final List<UsageEntry> usageEntries = new ArrayList<>();

    public void add(UsageEntry entry) {

        // Ik voeg een nieuwe entry toe
        usageEntries.add(entry);
    }

    public List<UsageEntry> findByCustomerId(String customerId) {

        // Ik geef alleen de entries terug die bij een bepaalde klant horen
        return usageEntries.stream()
                .filter(u -> u.getCustomerId().equals(customerId))
                .toList();
    }

    public List<UsageEntry> findAll() {

        // Ik geef een veilige kopie terug
        return List.copyOf(usageEntries);
    }
}