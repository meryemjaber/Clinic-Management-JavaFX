package view;

import controller.DashboardController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.LigneDashboard;

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

        VBox utilisateurs = creerCarte(
                "👥 Utilisateurs",
                String.valueOf(controller.nombreUtilisateurs()),
                "#009688"
        );

        cartes.getChildren().addAll(
                patient,
                medecin,
                rdv,
                total,
                utilisateurs
        );

        VBox bienvenue = new VBox(8);

        bienvenue.setAlignment(Pos.TOP_LEFT);

        bienvenue.setPadding(new Insets(15));

        bienvenue.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:20;" +
                        "-fx-border-radius:20;" +
                        "-fx-border-color:#E3EAF2;" +
                        "-fx-padding:15;"
        );

        Label msg1 = new Label(
                "Bienvenue dans l'application de gestion de clinique"
        );

        msg1.setStyle(
                "-fx-font-size:20;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#1565C0;"
        );


        Label msg2 = new Label(
                "Gérez facilement les patients, médecins et rendez-vous."
        );

        msg2.setStyle(
                "-fx-font-size:14;" +
                        "-fx-text-fill:#607D8B;"
        );

        bienvenue.getChildren().addAll(msg1, msg2);

        Label titreListe = new Label("📅 Rendez-vous d'aujourd'hui");

        titreListe.setStyle(
                "-fx-font-size:20;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#1565C0;"
        );

        TableView<LigneDashboard> table = new TableView<>();

        TableColumn<LigneDashboard,String> colHeure =
                new TableColumn<>("Heure");

        colHeure.setCellValueFactory(
                new PropertyValueFactory<>("heure")
        );

        TableColumn<LigneDashboard,String> colPatient =
                new TableColumn<>("Patient");

        colPatient.setCellValueFactory(
                new PropertyValueFactory<>("patient")
        );

        TableColumn<LigneDashboard,String> colMedecin =
                new TableColumn<>("Médecin");

        colMedecin.setCellValueFactory(
                new PropertyValueFactory<>("medecin")
        );

        TableColumn<LigneDashboard,String> colMotif =
                new TableColumn<>("Motif");

        colMotif.setCellValueFactory(
                new PropertyValueFactory<>("motif")
        );

        table.getColumns().addAll(
                colHeure,
                colPatient,
                colMedecin,
                colMotif
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setItems(
                controller.chargerRendezVousDuJour()
        );
        principal.getChildren().addAll(

                bienvenue,

                titre,

                cartes,

                titreListe,

                table

        );

        setCenter(principal);

    }

    private VBox creerCarte(
            String titre,
            String valeur,
            String couleur
    ) {

        VBox box = new VBox(15);

        box.setAlignment(Pos.CENTER);

        box.setPrefSize(200, 120);

        box.setStyle(

                "-fx-background-color:"+couleur+";" +

                        "-fx-background-radius:18;" +

                        "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.20),12,0,0,4);"

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

        box.getChildren().addAll(l1, l2);

        return box;

    }

}