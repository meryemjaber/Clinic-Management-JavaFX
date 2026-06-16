package view;

import controller.MedecinController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Medecin;

public class MedecinView extends BorderPane {

    private TextField txtRecherche = new TextField();

    private TextField txtNom = new TextField();
    private TextField txtPrenom = new TextField();
    private TextField txtSpecialite = new TextField();
    private TextField txtTelephone = new TextField();
    private TextField txtEmail = new TextField();

    private CheckBox chkDisponible =
            new CheckBox("Disponible");

    private Button btnRecherche =
            new Button("Rechercher");

    private Button btnAjouter =
            new Button("Ajouter");

    private Button btnModifier =
            new Button("Modifier");

    private Button btnSupprimer =
            new Button("Supprimer");

    private Button btnActualiser =
            new Button("Actualiser");

    private TableView<Medecin> table =
            new TableView<>();

    private MedecinController controller =
            new MedecinController();

    private Medecin medecinSelectionne = null;

    public MedecinView() {

        HBox recherche = new HBox(10);

        recherche.setPadding(new Insets(10));

        txtRecherche.setPromptText("Rechercher un médecin");

        recherche.getChildren().addAll(
                new Label("Recherche :"),
                txtRecherche,
                btnRecherche
        );

        setTop(recherche);

        GridPane form = new GridPane();

        form.setPadding(new Insets(20));
        form.setHgap(10);
        form.setVgap(10);

        form.setStyle(

                "-fx-background-color:#F1F8E9;" +

                        "-fx-background-radius:15;" +

                        "-fx-border-radius:15;" +

                        "-fx-border-color:#4CAF50;" +

                        "-fx-padding:20;"

        );

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setMinWidth(130);

        ColumnConstraints c2 = new ColumnConstraints();
        c2.setHgrow(Priority.ALWAYS);

        form.getColumnConstraints().addAll(c1,c2);

        form.add(new Label("Nom"),0,0);
        form.add(txtNom,1,0);

        form.add(new Label("Prénom"),0,1);
        form.add(txtPrenom,1,1);

        form.add(new Label("Spécialité"),0,2);
        form.add(txtSpecialite,1,2);

        form.add(new Label("Téléphone"),0,3);
        form.add(txtTelephone,1,3);

        form.add(new Label("Email"),0,4);
        form.add(txtEmail,1,4);

        form.add(chkDisponible,1,5);

        HBox boutons = new HBox(10);

        boutons.getChildren().addAll(
                btnAjouter,
                btnModifier,
                btnSupprimer,
                btnActualiser
        );

        form.add(boutons,1,6);

        TableColumn<Medecin,Integer> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idMedecin"));

        TableColumn<Medecin,String> colNom =
                new TableColumn<>("Nom");

        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        TableColumn<Medecin,String> colPrenom =
                new TableColumn<>("Prénom");

        colPrenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));

        TableColumn<Medecin,String> colSpecialite =
                new TableColumn<>("Spécialité");

        colSpecialite.setCellValueFactory(
                new PropertyValueFactory<>("specialite"));

        TableColumn<Medecin,String> colTelephone =
                new TableColumn<>("Téléphone");

        colTelephone.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));

        TableColumn<Medecin,String> colEmail =
                new TableColumn<>("Email");

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        table.getColumns().addAll(
                colId,
                colNom,
                colPrenom,
                colSpecialite,
                colTelephone,
                colEmail
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        chargerTable();

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs,ancien,nouveau)->{

                    if(nouveau!=null){

                        medecinSelectionne=nouveau;

                        txtNom.setText(nouveau.getNom());
                        txtPrenom.setText(nouveau.getPrenom());
                        txtSpecialite.setText(nouveau.getSpecialite());
                        txtTelephone.setText(nouveau.getTelephone());
                        txtEmail.setText(nouveau.getEmail());
                        chkDisponible.setSelected(
                                nouveau.isDisponible()
                        );

                    }

                }

        );

        btnAjouter.setOnAction(e -> {

            Medecin m = new Medecin();

            m.setNom(txtNom.getText());
            m.setPrenom(txtPrenom.getText());
            m.setSpecialite(txtSpecialite.getText());
            m.setTelephone(txtTelephone.getText());
            m.setEmail(txtEmail.getText());
            m.setDisponible(chkDisponible.isSelected());

            controller.ajouterMedecin(m);

            chargerTable();

            viderFormulaire();

        });

        btnModifier.setOnAction(e -> {

            if (medecinSelectionne == null)
                return;

            medecinSelectionne.setNom(txtNom.getText());
            medecinSelectionne.setPrenom(txtPrenom.getText());
            medecinSelectionne.setSpecialite(txtSpecialite.getText());
            medecinSelectionne.setTelephone(txtTelephone.getText());
            medecinSelectionne.setEmail(txtEmail.getText());
            medecinSelectionne.setDisponible(chkDisponible.isSelected());

            controller.modifierMedecin(medecinSelectionne);

            chargerTable();

            viderFormulaire();

            medecinSelectionne = null;

        });

        btnSupprimer.setOnAction(e -> {

            if (medecinSelectionne == null)
                return;

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Confirmation");

            confirmation.setHeaderText("Suppression");

            confirmation.setContentText(
                    "Voulez-vous vraiment supprimer ce médecin ?"
            );

            if (confirmation.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {

                controller.supprimerMedecin(
                        medecinSelectionne.getIdMedecin()
                );

                chargerTable();

                viderFormulaire();

                medecinSelectionne = null;

            }

        });

        btnRecherche.setOnAction(e -> {

            String nom = txtRecherche.getText();

            if (nom.isEmpty()) {

                chargerTable();

            } else {

                table.setItems(
                        controller.rechercherMedecin(nom)
                );

            }

        });

        btnActualiser.setOnAction(e -> {

            chargerTable();

            viderFormulaire();

            txtRecherche.clear();

            medecinSelectionne = null;

        });

        SplitPane split = new SplitPane();

        split.getItems().addAll(
                form,
                table
        );

        split.setDividerPositions(0.38);

        setCenter(split);

    }

    private void chargerTable() {

        ObservableList<Medecin> liste =
                controller.chargerMedecins();

        table.setItems(liste);

    }

    private void viderFormulaire() {

        txtNom.clear();
        txtPrenom.clear();
        txtSpecialite.clear();
        txtTelephone.clear();
        txtEmail.clear();

        chkDisponible.setSelected(false);

    }

}