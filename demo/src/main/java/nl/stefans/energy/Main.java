package nl.stefans.energy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.stefans.energy.repository.CustomerRepository;
import nl.stefans.energy.repository.TariffRepository;
import nl.stefans.energy.repository.UsageRepository;
import nl.stefans.energy.service.EnergyService;
import nl.stefans.energy.service.ValidationService;
import nl.stefans.energy.ui.MainScreen;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Ik maak repositories aan: dat zijn de "opslagplekken" van mijn data
        CustomerRepository customerRepository = new CustomerRepository();
        UsageRepository usageRepository = new UsageRepository();
        TariffRepository tariffRepository = new TariffRepository();

        // Ik maak services aan: dat zijn de "logica" helpers (berekenen en valideren)
        ValidationService validationService = new ValidationService();
        EnergyService energyService = new EnergyService(tariffRepository, usageRepository);

        // Ik maak het hoofdscherm aan en geef alles door wat het nodig heeft
        MainScreen mainScreen = new MainScreen(
                stage,
                customerRepository,
                usageRepository,
                tariffRepository,
                validationService,
                energyService
        );

        // Ik maak de scene met een vaste grootte
        Scene scene = new Scene(mainScreen.getRoot(), 900, 600);

        // Ik zet de titel bovenin het venster
        stage.setTitle("Energiebedrijf Current");

        // Ik koppel de scene aan het venster
        stage.setScene(scene);

        // Ik laat het venster zien
        stage.show();
    }

    public static void main(String[] args) {

        // Ik start hier de JavaFX applicatie
        launch(args);
    }
}