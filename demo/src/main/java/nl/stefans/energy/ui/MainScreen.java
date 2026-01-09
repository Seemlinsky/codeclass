package nl.stefans.energy.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.stefans.energy.repository.CustomerRepository;
import nl.stefans.energy.repository.TariffRepository;
import nl.stefans.energy.repository.UsageRepository;
import nl.stefans.energy.service.EnergyService;
import nl.stefans.energy.service.ValidationService;

public class MainScreen {

    // Ik maak een VBox die alles onder elkaar zet
    private final VBox root;

    public MainScreen(
            Stage stage,
            CustomerRepository customerRepository,
            UsageRepository usageRepository,
            TariffRepository tariffRepository,
            ValidationService validationService,
            EnergyService energyService
    ) {
        // Ik maak een VBox met 15px ruimte tussen items
        root = new VBox(15);

        // Ik geef rondom ruimte zodat het niet tegen de randen plakt
        root.setPadding(new Insets(30));

        // Ik zet alles links uitgelijnd
        root.setAlignment(Pos.CENTER_LEFT);

        // Ik maak de titel van het hoofdmenu
        Label title = new Label("Energiebedrijf Current");

        // Ik geef de titel een grote en dikke stijl
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Ik maak een knop waarmee je naar verbruik invoeren gaat
        Button usageBtn = new Button("Verbruik invoeren");

        // Ik maak een knop waarmee je naar overzicht/kosten gaat
        Button overviewBtn = new Button("Overzicht / Kosten");

        // Als ik op 'verbruik invoeren' klik, laad ik de UsageScreen in het scherm
        usageBtn.setOnAction(e ->
                stage.getScene().setRoot(
                        new UsageScreen(stage, this, customerRepository, usageRepository, validationService).getRoot()
                )
        );

        // Als ik op 'overzicht/kosten' klik, laad ik de OverviewScreen in het scherm
        overviewBtn.setOnAction(e ->
                stage.getScene().setRoot(
                        new OverviewScreen(stage, this, customerRepository, usageRepository, energyService).getRoot()
                )
        );

        // Ik voeg alle onderdelen toe aan het hoofdmenu
        root.getChildren().addAll(title, usageBtn, overviewBtn);
    }

    public Parent getRoot() {

        // Ik geef de root terug zodat Main.java hem kan tonen in de Scene
        return root;
    }
}