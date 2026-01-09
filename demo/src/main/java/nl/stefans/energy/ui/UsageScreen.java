package nl.stefans.energy.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.stefans.energy.model.Customer;
import nl.stefans.energy.model.UsageEntry;
import nl.stefans.energy.repository.CustomerRepository;
import nl.stefans.energy.repository.UsageRepository;
import nl.stefans.energy.service.ValidationService;

import java.time.LocalDate;

public class UsageScreen {

    // Ik maak dit het hoofd-paneel van deze pagina (hierin zet ik alle UI elementen)
    private final GridPane root;

    // Ik zet dit als class-variabele (dus niet alleen in de constructor),
    // zodat ik hem later ook kan uitlezen en leegmaken in de save-knop.
    private TextField electricityField;

    public UsageScreen(
            Stage stage,
            MainScreen mainScreen,
            CustomerRepository customerRepository,
            UsageRepository usageRepository,
            ValidationService validationService
    ) {
        // Ik maak een GridPane zodat ik makkelijk labels + velden netjes onder elkaar kan zetten
        root = new GridPane();

        // Ik geef het scherm padding (ruimte rondom)
        root.setPadding(new Insets(25));

        // Ik geef de GridPane ruimte tussen kolommen en rijen
        root.setHgap(12);
        root.setVgap(10);

        // Ik maak de titel bovenaan
        Label title = new Label("Verbruik invoeren");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Ik maak een dropdown met klanten (ComboBox)
        ComboBox<Customer> customerBox = new ComboBox<>(
                FXCollections.observableArrayList(customerRepository.findAll())
        );

        // Ik zorg dat in de dropdown de naam netjes getoond wordt (voor de lijst)
        customerBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getDisplayName());
            }
        });

        // Ik zorg dat bovenaan in het geselecteerde vakje ook de naam netjes staat
        customerBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getDisplayName());
            }
        });

        // Ik maak een datumkiezer en zet standaard op vandaag
        DatePicker datePicker = new DatePicker(LocalDate.now());

        // ✅ FIX: Ik maak electricityField zonder "TextField" ervoor, want ik gebruik de class-variabele
        electricityField = new TextField();
        electricityField.setPromptText("kWh");

        // Ik maak een veld voor gas
        TextField gasField = new TextField();
        gasField.setPromptText("m³");

        // Ik maak een label om feedback te tonen (foutmelding of succes)
        Label feedback = new Label();
        feedback.setStyle("-fx-text-fill: #cc0000;");

        // Ik maak de knoppen
        Button save = new Button("Opslaan");
        Button back = new Button("Terug");

        // Ik bepaal wat er gebeurt als ik op "Opslaan" klik
        save.setOnAction(e -> {
            try {
                // Ik haal de geselecteerde klant op
                Customer c = customerBox.getValue();

                // Als er geen klant gekozen is, gooi ik een foutmelding
                if (c == null) throw new IllegalArgumentException("Kies een klant.");

                // Ik lees de datum uit de DatePicker
                LocalDate date = datePicker.getValue();

                // Ik lees de teksten uit de velden en zet ze om naar doubles
                double kwh = parseDoubleOrZero(electricityField.getText());
                double gas = parseDoubleOrZero(gasField.getText());

                // Ik laat de validation service checken of alles oké is
                validationService.validateUsage(date, kwh, gas);

                // Als alles goed is, voeg ik de entry toe aan de repository
                usageRepository.add(new UsageEntry(c.getId(), date, kwh, gas));

                // Ik toon een groene succesmelding
                feedback.setStyle("-fx-text-fill: #008800;");
                feedback.setText("Opgeslagen ✅");

                // Ik maak de invoervelden leeg zodat je makkelijk een volgende entry kan doen
                electricityField.clear();
                gasField.clear();

            } catch (Exception ex) {
                // Als er iets fout gaat, toon ik de foutmelding in rood
                feedback.setStyle("-fx-text-fill: #cc0000;");
                feedback.setText(ex.getMessage());
            }
        });

        // Ik bepaal wat er gebeurt als ik op "Terug" klik: terug naar MainScreen
        back.setOnAction(e -> stage.getScene().setRoot(mainScreen.getRoot()));

        // Ik zet alles in de GridPane (rij voor rij)
        int r = 0;

        // Titel (span over 2 kolommen)
        root.add(title, 0, r++, 2, 1);

        // Klant label + dropdown
        root.add(new Label("Klant:"), 0, r);
        root.add(customerBox, 1, r++);

        // Datum label + datepicker
        root.add(new Label("Datum:"), 0, r);
        root.add(datePicker, 1, r++);

        // Stroom label + veld
        root.add(new Label("Stroomverbruik:"), 0, r);
        root.add(electricityField, 1, r++);

        // Gas label + veld
        root.add(new Label("Gasverbruik:"), 0, r);
        root.add(gasField, 1, r++);

        // Knoppen
        root.add(save, 0, r);
        root.add(back, 1, r++);

        // Feedback onderaan (span over 2 kolommen)
        root.add(feedback, 0, r, 2, 1);
    }

    // Ik maak een helper die lege input als 0 ziet, en komma’s omzet naar punten
    private double parseDoubleOrZero(String s) {
        if (s == null || s.isBlank()) return 0;
        return Double.parseDouble(s.replace(",", "."));
    }

    // Ik geef de root terug zodat Main/Scene deze pagina kan tonen
    public Parent getRoot() {
        return root;
    }
}