package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import view.NotificationTelephoneView;

public class AlerteUrgenceView extends BorderPane {

    private TextField txtMedecin = new TextField();

    private TextField txtPatient = new TextField();

    private TextField txtSalle = new TextField();

    private TextField txtUrgence = new TextField();

    private TextArea txtMessage = new TextArea();

    private Button btnEnvoyer =
            new Button("🚨 Envoyer l'alerte");

    public AlerteUrgenceView() {

        GridPane form = new GridPane();

        form.setPadding(new Insets(20));

        form.setHgap(10);

        form.setVgap(10);

        Label titre = new Label(
                "🚨 Alerte Urgence"
        );

        titre.setStyle(
                "-fx-font-size:24;" +
                        "-fx-font-weight:bold;"
        );

        txtMessage.setPrefRowCount(5);

        form.add(titre,0,0,2,1);

        form.add(new Label("Médecin"),0,1);
        form.add(txtMedecin,1,1);

        form.add(new Label("Patient"),0,2);
        form.add(txtPatient,1,2);

        form.add(new Label("Salle"),0,3);
        form.add(txtSalle,1,3);

        form.add(new Label("Nature de l'urgence"),0,4);
        form.add(txtUrgence,1,4);

        form.add(new Label("Message"),0,5);
        form.add(txtMessage,1,5);

        form.add(btnEnvoyer,1,6);

        setCenter(form);

        btnEnvoyer.setOnAction(e -> {

            if(txtMedecin.getText().isEmpty()
                    || txtPatient.getText().isEmpty()
                    || txtSalle.getText().isEmpty()
                    || txtUrgence.getText().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Attention");

                alert.setHeaderText(null);

                alert.setContentText(
                        "Veuillez remplir tous les champs."
                );

                alert.showAndWait();

                return;

            }

            NotificationTelephoneView.afficher(

                    txtMedecin.getText(),

                    txtPatient.getText(),

                    txtSalle.getText(),

                    txtUrgence.getText(),

                    txtMessage.getText()

            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Succès");

            alert.setHeaderText(null);

            alert.setContentText(
                    "✅ Notification envoyée au médecin."
            );

            alert.showAndWait();

        });

    }

}