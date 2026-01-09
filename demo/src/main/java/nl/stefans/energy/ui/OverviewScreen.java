package nl.stefans.energy.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.stefans.energy.model.Customer;
import nl.stefans.energy.model.UsageEntry;
import nl.stefans.energy.repository.CustomerRepository;
import nl.stefans.energy.repository.UsageRepository;
import nl.stefans.energy.service.EnergyService;

import java.util.List;

public class OverviewScreen {

    // Ik gebruik een VBox omdat ik alles onder elkaar wil zetten
    private final VBox root;

    public OverviewScreen(
            Stage stage,
            MainScreen mainScreen,
            CustomerRepository customerRepository,
            UsageRepository usageRepository,
            EnergyService energyService
    ) {
        // Ik maak de layout met wat ruimte tussen onderdelen
        root = new VBox(12);

        // Ik geef padding zodat het scherm niet tegen de randen plakt
        root.setPadding(new Insets(25));

        // Ik maak de titel van dit scherm
        Label title = new Label("Overzicht / Kosten");

        // Ik maak de titel wat groter en dikker
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Ik maak een dropdown met alle klanten uit de repository
        ComboBox<Customer> customerBox = new ComboBox<>(
                FXCollections.observableArrayList(customerRepository.findAll())
        );

        // Ik zorg dat in de dropdown de naam netjes zichtbaar is (voornaam + achternaam)
        customerBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getDisplayName());
            }
        });

        // Ik zorg dat ook de geselecteerde waarde netjes wordt weergegeven
        customerBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getDisplayName());
            }
        });

        // Ik maak een tekstveld waar het overzicht in komt
        TextArea output = new TextArea();

        // Ik zet output readonly zodat je het niet per ongeluk aanpast
        output.setEditable(false);

        // Ik maak de output wat hoger zodat er genoeg ruimte is
        output.setPrefRowCount(18);

        // Ik maak een knop om het overzicht te tonen
        Button show = new Button("Toon overzicht");

        // Ik maak een knop om terug te gaan naar het hoofdmenu
        Button back = new Button("Terug");

        // Als ik op "Toon overzicht" klik, maak ik het overzicht zichtbaar
        show.setOnAction(e -> {

            // Ik pak de geselecteerde klant uit de dropdown
            Customer c = customerBox.getValue();

            // Als er geen klant is gekozen, zeg ik dat eerst
            if (c == null) {
                output.setText("Kies eerst een klant.");
                return;
            }

            // Ik haal alle entries op van deze klant
            List<UsageEntry> usage = usageRepository.findByCustomerId(c.getId());

            // Ik bereken kosten met mijn service
            double elec = energyService.calculateElectricityCost(c.getId());
            double gas = energyService.calculateGasCost(c.getId());
            double total = energyService.calculateTotalCost(c.getId());

            // Ik bouw een tekst op met StringBuilder (dat is snel en netjes)
            StringBuilder sb = new StringBuilder();

            // Ik zet klant-info bovenaan
            sb.append("Klant: ").append(c.getDisplayName()).append("\n");
            sb.append("Adres: ").append(c.getAddress()).append("\n\n");

            // Ik zet hoeveel entries er zijn
            sb.append("Entries: ").append(usage.size()).append("\n");

            // Ik loop alle entries langs en voeg ze toe in tekstvorm
            for (UsageEntry u : usage) {
                sb.append("- ").append(u.getDate())
                        .append(" | kWh=").append(u.getElectricityKwh())
                        .append(" | m3=").append(u.getGasM3())
                        .append("\n");
            }

            // Ik zet daarna de kosten erbij
            sb.append("\nKosten (schatting):\n");
            sb.append("Stroom: € ").append(String.format("%.2f", elec)).append("\n");
            sb.append("Gas:    € ").append(String.format("%.2f", gas)).append("\n");
            sb.append("Totaal: € ").append(String.format("%.2f", total)).append("\n");

            // Ik zet de tekst in de output box
            output.setText(sb.toString());
        });

        // Als ik op terug klik, ga ik terug naar de MainScreen
        back.setOnAction(e -> stage.getScene().setRoot(mainScreen.getRoot()));

        // Ik voeg alles toe aan mijn layout
        root.getChildren().addAll(
                title,
                new Label("Klant:"),
                customerBox,
                show,
                output,
                back
        );
    }

    public Parent getRoot() {

        // Ik geef de root terug zodat de Scene dit scherm kan tonen
        return root;
    }
}