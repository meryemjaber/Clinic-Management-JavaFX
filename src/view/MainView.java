package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import utils.Session;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainView extends BorderPane {

    public MainView() {

        MainController controller = new MainController(this);

        String role = utils.Session.getUtilisateur().getRole();

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
        menu.setSpacing(20);

        ImageView logo = new ImageView(

                new Image(

                        getClass().getResourceAsStream(

                                "logo.png"

                        )

                )

        );

        logo.setFitWidth(90);

        logo.setFitHeight(90);

        Label nomClinique = new Label(

                "NovaCare\nClinic"

        );

        nomClinique.setStyle(

                "-fx-text-fill:white;" +

                        "-fx-font-size:22;" +

                        "-fx-font-weight:bold;"

        );

        nomClinique.setAlignment(Pos.CENTER);

        menu.setPrefWidth(240);

        menu.setStyle(

                "-fx-background-color:#1565C0;" +

                        "-fx-padding:20;" +

                        "-fx-spacing:15;"

        );

        Button btnDashboard = new Button("📊 Dashboard");

        Button btnPatient = new Button("👤 Patients");

        Button btnMedecin = new Button("👨‍⚕️ Médecins");

        Button btnRdv = new Button("📅 Rendez-vous");

        Button btnUtilisateur = new Button("👥 Utilisateurs");

        Button btnBanqueSang = new Button("🩸 Banque de sang");

        Button btnUrgence = new Button("🚨 Alerte Urgence");

        String styleMenu =

                "-fx-background-color:white;" +

                        "-fx-text-fill:#1565C0;" +

                        "-fx-font-size:16px;" +

                        "-fx-font-weight:bold;" +

                        "-fx-background-radius:15;" +

                        "-fx-border-radius:15;" +

                        "-fx-border-color:#D6EAF8;" +

                        "-fx-border-width:2;" +

                        "-fx-cursor:hand;";

        btnDashboard.setStyle(styleMenu);
        btnPatient.setStyle(styleMenu);
        btnMedecin.setStyle(styleMenu);
        btnRdv.setStyle(styleMenu);
        btnBanqueSang.setStyle(styleMenu);
        btnUrgence.setStyle(styleMenu);
        btnUtilisateur.setStyle(styleMenu);

        btnDashboard.setMaxWidth(Double.MAX_VALUE);
        btnPatient.setMaxWidth(Double.MAX_VALUE);
        btnMedecin.setMaxWidth(Double.MAX_VALUE);
        btnRdv.setMaxWidth(Double.MAX_VALUE);
        btnUtilisateur.setMaxWidth(Double.MAX_VALUE);
        btnBanqueSang.setMaxWidth(Double.MAX_VALUE);
        btnUrgence.setMaxWidth(Double.MAX_VALUE);

        btnDashboard.setPrefHeight(65);
        btnPatient.setPrefHeight(65);
        btnMedecin.setPrefHeight(65);
        btnRdv.setPrefHeight(65);
        btnBanqueSang.setPrefHeight(65);
        btnUrgence.setPrefHeight(65);
        btnUtilisateur.setPrefHeight(65);


        menu.getChildren().addAll(

                logo,

                nomClinique,

                new Separator(),

                btnDashboard,

                btnPatient,

                btnMedecin,

                btnRdv,

                btnBanqueSang,

                btnUrgence,

                btnUtilisateur

        );

        if (utils.Session.getUtilisateur().getRole().equals("SECRETAIRE")) {

            btnUtilisateur.setVisible(false);
            btnUtilisateur.setManaged(false);

        }

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

        btnBanqueSang.setOnAction(e ->
                controller.afficherBanqueSang());

        // ================= BARRE D'ÉTAT =================

        Label utilisateur = new Label(

                "👤 Connecté : "

                        + Session.getUtilisateur().getNom()

                        + " ("

                        + Session.getUtilisateur().getRole()

                        + ")"

        );

        Button btnLogout = new Button("🚪 Déconnexion");

        Region espace = new Region();

        HBox.setHgrow(espace, Priority.ALWAYS);

        HBox footer = new HBox(10);

        footer.setPadding(new Insets(10));

        footer.setAlignment(Pos.CENTER_LEFT);

        footer.getChildren().addAll(

                utilisateur,

                espace,

                btnLogout

        );

        setBottom(footer);

        btnUtilisateur.setOnAction(e ->
                controller.afficherUtilisateurs());

        btnLogout.setOnAction(e -> {

            try {

                Session.logout();

                FXMLLoader loader = new FXMLLoader(

                        getClass().getResource("/view/login.fxml")

                );

                Scene scene = new Scene(loader.load(),450,500);

                Stage stage =

                        (Stage)getScene().getWindow();

                stage.setScene(scene);

                stage.setTitle("Connexion");

                stage.show();

            }

            catch (Exception ex){

                ex.printStackTrace();

            }

        });

        btnUrgence.setOnAction(e ->

                controller.afficherAlerteUrgence()

        );

    }



}