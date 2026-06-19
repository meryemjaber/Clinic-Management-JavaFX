
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MedicalTreatmentApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));

        Scene scene = new Scene(loader.load(),450,500);

        stage.setTitle("Connexion");

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}