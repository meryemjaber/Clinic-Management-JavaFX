package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView extends BorderPane {

    public MainView() {

        MainController controller = new MainController(this);

        // ================= MENU BAR =================

        MenuBar menuBar = new MenuBar();

        Menu fichier = new Menu("Fichier");

        MenuItem quitter = new MenuItem("Quitter");

        quitter.setOnAction(e -> System.exit(0));

        fichier.getItems().add(quitter);

        Menu aide = new Menu("Aide");

        MenuItem apropos = new MenuItem("À propos");

        apropos.setOnAction(e -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("À propos");

            alert.setHeaderText("Application de Gestion de Clinique");

            alert.setContentText(
                    "Mini Projet JavaFX\n\n" +
                            "✔ Gestion des Patients\n" +
                            "✔ Gestion des Médecins\n" +
                            "✔ Gestion des Rendez-vous"
            );

            alert.showAndWait();

        });

        aide.getItems().add(apropos);

        menuBar.getMenus().addAll(fichier, aide);

        setTop(menuBar);

        // ================= MENU LATÉRAL =================

        VBox menu = new VBox(15);

        menu.setPadding(new Insets(20));

        menu.setPrefWidth(240);

        menu.setStyle(

                "-fx-background-color:#0D47A1;"

        );

        Button btnDashboard = new Button("📊 Dashboard");

        Button btnPatient = new Button("👤 Patients");

        Button btnMedecin = new Button("👨‍⚕️ Médecins");

        Button btnRdv = new Button("📅 Rendez-vous");

        btnDashboard.setMaxWidth(Double.MAX_VALUE);
        btnPatient.setMaxWidth(Double.MAX_VALUE);
        btnMedecin.setMaxWidth(Double.MAX_VALUE);
        btnRdv.setMaxWidth(Double.MAX_VALUE);

        btnDashboard.setPrefHeight(50);
        btnPatient.setPrefHeight(50);
        btnMedecin.setPrefHeight(50);
        btnRdv.setPrefHeight(50);

        menu.getChildren().addAll(
                btnDashboard,
                btnPatient,
                btnMedecin,
                btnRdv
        );

        setLeft(menu);

        // ================= PAGE PAR DÉFAUT =================

        controller.afficherDashboard();

        btnDashboard.setOnAction(e ->
                controller.afficherDashboard());

        btnPatient.setOnAction(e ->
                controller.afficherPatients());

        btnMedecin.setOnAction(e ->
                controller.afficherMedecins());

        btnRdv.setOnAction(e ->
                controller.afficherRendezVous());

        // ================= BARRE D'ÉTAT =================

        Label status = new Label(
                "Application de Gestion de Clinique © 2026"
        );

        HBox footer = new HBox(status);

        footer.setPadding(new Insets(10));

        setBottom(footer);

    }

}