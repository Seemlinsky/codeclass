package energy;

import javafx.scene.Scene;
import energy.repository.CustomerRepository;
import energy.repository.TariffRepository;
import energy.repository.UsageRepository;
import energy.service.EnergyService;
import energy.service.ValidationService;
import energy.ui.MainScreen;

public class Main<Stage> {

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