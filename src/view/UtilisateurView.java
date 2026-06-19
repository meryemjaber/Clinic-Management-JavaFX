package view;

import controller.UtilisateurController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Utilisateur;

public class UtilisateurView extends BorderPane {

    private TextField txtNom = new TextField();
    private TextField txtEmail = new TextField();
    private PasswordField txtMotDePasse = new PasswordField();

    private ComboBox<String> cmbRole = new ComboBox<>();

    private Button btnAjouter = new Button("Ajouter");
    private Button btnModifier = new Button("Modifier");
    private Button btnSupprimer = new Button("Supprimer");
    private Button btnActualiser = new Button("Actualiser");

    private TableView<Utilisateur> table = new TableView<>();

    private UtilisateurController controller =
            new UtilisateurController();

    private Utilisateur utilisateurSelectionne = null;

    public UtilisateurView() {

        GridPane form = new GridPane();

        form.setPadding(new Insets(20));
        form.setHgap(10);
        form.setVgap(10);

        form.setStyle(
                "-fx-background-color:#E3F2FD;" +
                        "-fx-background-radius:15;" +
                        "-fx-border-radius:15;" +
                        "-fx-border-color:#1976D2;" +
                        "-fx-padding:20;"
        );

        form.add(new Label("Nom"),0,0);
        form.add(txtNom,1,0);

        form.add(new Label("Email"),0,1);
        form.add(txtEmail,1,1);

        form.add(new Label("Mot de passe"),0,2);
        form.add(txtMotDePasse,1,2);

        form.add(new Label("Rôle"),0,3);
        form.add(cmbRole,1,3);

        cmbRole.getItems().addAll(
                "ADMIN",
                "SECRETAIRE"
        );

        HBox boutons = new HBox(10);

        boutons.getChildren().addAll(
                btnAjouter,
                btnModifier,
                btnSupprimer,
                btnActualiser
        );

        form.add(boutons,1,4);

        TableColumn<Utilisateur,Integer> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Utilisateur,String> colNom =
                new TableColumn<>("Nom");

        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );

        TableColumn<Utilisateur,String> colEmail =
                new TableColumn<>("Email");

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn<Utilisateur,String> colRole =
                new TableColumn<>("Rôle");

        colRole.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );

        table.getColumns().addAll(
                colId,
                colNom,
                colEmail,
                colRole
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        SplitPane split = new SplitPane();

        split.getItems().addAll(
                form,
                table
        );

        split.setDividerPositions(0.40);

        setCenter(split);

        chargerTable();

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, ancien, nouveau) -> {

                    if (nouveau != null) {

                        utilisateurSelectionne = nouveau;

                        txtNom.setText(nouveau.getNom());
                        txtEmail.setText(nouveau.getEmail());
                        txtMotDePasse.setText(nouveau.getMotDePasse());
                        cmbRole.setValue(nouveau.getRole());

                    }

                }
        );

        btnAjouter.setOnAction(e -> {

            Utilisateur u = new Utilisateur();

            u.setNom(txtNom.getText());
            u.setEmail(txtEmail.getText());
            u.setMotDePasse(txtMotDePasse.getText());
            u.setRole(cmbRole.getValue());

            controller.ajouterUtilisateur(u);

            chargerTable();

            viderFormulaire();

        });

        btnModifier.setOnAction(e -> {

            if (utilisateurSelectionne == null)
                return;

            utilisateurSelectionne.setNom(txtNom.getText());
            utilisateurSelectionne.setEmail(txtEmail.getText());
            utilisateurSelectionne.setMotDePasse(txtMotDePasse.getText());
            utilisateurSelectionne.setRole(cmbRole.getValue());

            controller.modifierUtilisateur(utilisateurSelectionne);

            chargerTable();

            viderFormulaire();

            utilisateurSelectionne = null;

        });

        btnSupprimer.setOnAction(e -> {

            if (utilisateurSelectionne == null)
                return;

            controller.supprimerUtilisateur(
                    utilisateurSelectionne.getId()
            );

            chargerTable();

            viderFormulaire();

            utilisateurSelectionne = null;

        });

        btnActualiser.setOnAction(e -> {

            chargerTable();

            viderFormulaire();

            utilisateurSelectionne = null;

        });

    }

    private void chargerTable() {

        ObservableList<Utilisateur> liste =
                controller.chargerUtilisateurs();

        table.setItems(liste);

    }

    private void viderFormulaire() {

        txtNom.clear();
        txtEmail.clear();
        txtMotDePasse.clear();
        cmbRole.getSelectionModel().clearSelection();

    }

}