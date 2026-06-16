package view;

import controller.DashboardController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DashboardView extends BorderPane {

    public DashboardView() {

        DashboardController controller = new DashboardController();

        VBox principal = new VBox(20);

        principal.setPadding(new Insets(30));

        principal.setStyle("-fx-background-color:#F5F7FA;");

        Label titre = new Label("🏥 Tableau de bord - Gestion de Clinique");

        titre.setStyle(
                "-fx-font-size:28;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#1565C0;"
        );

        HBox cartes = new HBox(20);

        cartes.setAlignment(Pos.CENTER);

        VBox patient = creerCarte(
                "👤 Patients",
                String.valueOf(controller.nombrePatients()),
                "#2196F3"
        );

        VBox medecin = creerCarte(
                "👨‍⚕️ Médecins",
                String.valueOf(controller.nombreMedecins()),
                "#4CAF50"
        );

        VBox rdv = creerCarte(
                "📅 Rendez-vous",
                String.valueOf(controller.nombreRendezVous()),
                "#FF9800"
        );

        VBox total = creerCarte(
                "📊 Total",
                String.valueOf(
                        controller.nombrePatients()
                                + controller.nombreMedecins()
                                + controller.nombreRendezVous()
                ),
                "#F44336"
        );

        cartes.getChildren().addAll(
                patient,
                medecin,
                rdv,
                total
        );

        VBox bienvenue = new VBox(10);

        bienvenue.setAlignment(Pos.CENTER);

        bienvenue.setPadding(new Insets(30));

        bienvenue.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:15;" +
                        "-fx-border-radius:15;" +
                        "-fx-border-color:#E3F2FD;"
        );

        Label msg1 = new Label(
                "Bienvenue dans l'application de gestion de clinique"
        );

        msg1.setStyle(
                "-fx-font-size:22;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#1565C0;"
        );

        Label msg2 = new Label(
                "Gérez facilement les patients, médecins et rendez-vous."
        );

        msg2.setStyle(
                "-fx-font-size:15;" +
                        "-fx-text-fill:#455A64;"
        );

        bienvenue.getChildren().addAll(msg1,msg2);

        principal.getChildren().addAll(
                titre,
                cartes,
                bienvenue
        );

        setCenter(principal);

    }

    private VBox creerCarte(
            String titre,
            String valeur,
            String couleur
    ){

        VBox box = new VBox(15);

        box.setAlignment(Pos.CENTER);

        box.setPrefSize(220,140);

        box.setStyle(
                "-fx-background-color:"+couleur+";" +
                        "-fx-background-radius:15;"
        );

        Label l1 = new Label(titre);

        l1.setStyle(
                "-fx-font-size:18;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label l2 = new Label(valeur);

        l2.setStyle(
                "-fx-font-size:34;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        box.getChildren().addAll(l1,l2);

        return box;

    }

}