import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import view.MainView;

public class MedicalTreatmentApp extends Application {

    @Override
    public void start(Stage stage) {

        MainView root = new MainView();

        Scene scene = new Scene(root, 1200, 750);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/css/style.css")
                        .toExternalForm()
        );

        stage.setTitle("Gestion d'une Clinique");

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}