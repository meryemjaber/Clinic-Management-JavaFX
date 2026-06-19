
package view;

import controller.RendezVousController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.RendezVous;
import controller.PatientController;
import controller.MedecinController;
import dao.PatientDAO;
import dao.MedecinDAO;
import model.Patient;
import model.Medecin;

import java.time.LocalTime;

public class RendezVousView extends BorderPane {

    private TextField txtRecherche = new TextField();

    private DatePicker datePicker = new DatePicker();

    private TextField txtHeure = new TextField();

    private TextArea txtMotif = new TextArea();

    private ComboBox<String> comboStatut = new ComboBox<>();

    private TextField txtPatient = new TextField();

    private TextField txtMedecin = new TextField();

    private Button btnRecherche = new Button("Rechercher");

    private Button btnAjouter = new Button("Ajouter");

    private Button btnModifier = new Button("Modifier");

    private Button btnSupprimer = new Button("Supprimer");

    private Button btnActualiser = new Button("Actualiser");

    private TableView<RendezVous> table = new TableView<>();

    private RendezVousController controller =
            new RendezVousController();

    private PatientController patientController =
            new PatientController();

    private MedecinController medecinController =
            new MedecinController();

    private RendezVous rendezVousSelectionne = null;

    public RendezVousView() {

        HBox recherche = new HBox(10);

        recherche.setPadding(new Insets(10));

        txtRecherche.setPromptText("Rechercher par motif");

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

                "-fx-background-color:#FFF8E1;" +

                        "-fx-background-radius:15;" +

                        "-fx-border-radius:15;" +

                        "-fx-border-color:#FF9800;" +

                        "-fx-padding:20;"

        );

        ColumnConstraints c1 = new ColumnConstraints();

        c1.setMinWidth(150);

        ColumnConstraints c2 = new ColumnConstraints();

        c2.setHgrow(Priority.ALWAYS);

        form.getColumnConstraints().addAll(c1,c2);

        comboStatut.getItems().addAll(
                "En attente",
                "Confirmé",
                "Annulé"
        );

        txtPatient.setPromptText("Nom et prénom du patient");

        txtMedecin.setPromptText("Nom et prénom du médecin");

        txtMotif.setPrefRowCount(5);

        form.add(new Label("Date"),0,0);
        form.add(datePicker,1,0);

        form.add(new Label("Heure (HH:mm:ss)"),0,1);
        form.add(txtHeure,1,1);

        form.add(new Label("Motif"),0,2);
        form.add(txtMotif,1,2);

        form.add(new Label("Statut"),0,3);
        form.add(comboStatut,1,3);

        form.add(new Label("Patient"),0,4);
        form.add(txtPatient,1,4);

        form.add(new Label("Médecin"),0,5);
        form.add(txtMedecin,1,5);

        HBox boutons = new HBox(10);

        boutons.getChildren().addAll(
                btnAjouter,
                btnModifier,
                btnSupprimer,
                btnActualiser
        );

        form.add(boutons,1,6);

        TableColumn<RendezVous,Integer> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idRdv")
        );

        TableColumn<RendezVous,Object> colDate =
                new TableColumn<>("Date");

        colDate.setCellValueFactory(
                new PropertyValueFactory<>("dateRdv")
        );

        TableColumn<RendezVous,Object> colHeure =
                new TableColumn<>("Heure");

        colHeure.setCellValueFactory(
                new PropertyValueFactory<>("heure")
        );

        TableColumn<RendezVous,String> colMotif =
                new TableColumn<>("Motif");

        colMotif.setCellValueFactory(
                new PropertyValueFactory<>("motif")
        );

        TableColumn<RendezVous,String> colStatut =
                new TableColumn<>("Statut");

        colStatut.setCellValueFactory(
                new PropertyValueFactory<>("statut")
        );

        table.getColumns().addAll(
                colId,
                colDate,
                colHeure,
                colMotif,
                colStatut
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        chargerTable();

        table.getSelectionModel().selectedItemProperty().addListener(

                (obs, ancien, nouveau) -> {

                    if (nouveau != null) {

                        rendezVousSelectionne = nouveau;

                        datePicker.setValue(
                                nouveau.getDateRdv()
                        );

                        txtHeure.setText(
                                nouveau.getHeure().toString()
                        );

                        txtMotif.setText(
                                nouveau.getMotif()
                        );

                        comboStatut.setValue(
                                nouveau.getStatut()
                        );

                        model.Patient patient =
                                patientController.chercherParNomPrenomId(
                                        nouveau.getPatientId()
                                );

                        if(patient != null){

                            txtPatient.setText(
                                    patient.getNom() + " " + patient.getPrenom()
                            );

                        }

                        model.Medecin medecin =
                                medecinController.chercherParNomPrenomId(
                                        nouveau.getMedecinId()
                                );

                        if(medecin != null){

                            txtMedecin.setText(
                                    medecin.getNom() + " " + medecin.getPrenom()
                            );

                        }

                    }

                }

        );

        btnAjouter.setOnAction(e -> {

            try{

                PatientDAO patientDAO = new PatientDAO();

                MedecinDAO medecinDAO = new MedecinDAO();

                Patient patient =
                        patientDAO.chercherParNomPrenom(
                                txtPatient.getText()
                        );

                Medecin medecin =
                        medecinDAO.chercherParNomPrenom(
                                txtMedecin.getText()
                        );

                if(patient==null){

                    new Alert(
                            Alert.AlertType.ERROR,
                            "Patient introuvable."
                    ).showAndWait();

                    return;

                }

                if(medecin==null){

                    new Alert(
                            Alert.AlertType.ERROR,
                            "Médecin introuvable."
                    ).showAndWait();

                    return;

                }

                RendezVous rdv = new RendezVous();

                rdv.setDateRdv(datePicker.getValue());

                rdv.setHeure(
                        java.time.LocalTime.parse(
                                txtHeure.getText()
                        )
                );

                rdv.setMotif(txtMotif.getText());

                rdv.setStatut(
                        comboStatut.getValue()
                );

                rdv.setPatientId(
                        patient.getIdPatient()
                );

                rdv.setMedecinId(
                        medecin.getIdMedecin()
                );

                controller.ajouterRendezVous(rdv);

                chargerTable();

                viderFormulaire();

            }

            catch(Exception ex){

                ex.printStackTrace();

                new Alert(
                        Alert.AlertType.ERROR,
                        ex.getMessage()
                ).showAndWait();

            }

        });

        btnModifier.setOnAction(e -> {

            if (rendezVousSelectionne == null)
                return;

            try {

                PatientDAO patientDAO = new PatientDAO();

                MedecinDAO medecinDAO = new MedecinDAO();

                Patient patient =
                        patientDAO.chercherParNomPrenom(
                                txtPatient.getText()
                        );

                Medecin medecin =
                        medecinDAO.chercherParNomPrenom(
                                txtMedecin.getText()
                        );

                if(patient == null){

                    new Alert(
                            Alert.AlertType.ERROR,
                            "Patient introuvable."
                    ).showAndWait();

                    return;

                }

                if(medecin == null){

                    new Alert(
                            Alert.AlertType.ERROR,
                            "Médecin introuvable."
                    ).showAndWait();

                    return;

                }

                rendezVousSelectionne.setDateRdv(
                        datePicker.getValue()
                );

                rendezVousSelectionne.setHeure(
                        LocalTime.parse(txtHeure.getText())
                );

                rendezVousSelectionne.setMotif(
                        txtMotif.getText()
                );

                rendezVousSelectionne.setStatut(
                        comboStatut.getValue()
                );

                rendezVousSelectionne.setPatientId(
                        patient.getIdPatient()
                );

                rendezVousSelectionne.setMedecinId(
                        medecin.getIdMedecin()
                );

                controller.modifierRendezVous(
                        rendezVousSelectionne
                );

                chargerTable();

                viderFormulaire();

                rendezVousSelectionne = null;

            } catch (Exception ex) {

                ex.printStackTrace();

                new Alert(
                        Alert.AlertType.ERROR,
                        ex.getMessage()
                ).showAndWait();

            }

        });

        btnSupprimer.setOnAction(e -> {

            if (rendezVousSelectionne == null)
                return;

            Alert confirmation =
                    new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Confirmation");

            confirmation.setHeaderText("Suppression");

            confirmation.setContentText(
                    "Voulez-vous supprimer ce rendez-vous ?"
            );

            if (confirmation.showAndWait().orElse(ButtonType.CANCEL)
                    == ButtonType.OK) {

                controller.supprimerRendezVous(
                        rendezVousSelectionne.getIdRdv()
                );

                chargerTable();

                viderFormulaire();

                rendezVousSelectionne = null;

            }

        });

        btnRecherche.setOnAction(e -> {

            String texte = txtRecherche.getText();

            if (texte.isEmpty()) {

                chargerTable();

            } else {

                table.setItems(

                        controller.rechercherRendezVous(texte)

                );

            }

        });

        btnActualiser.setOnAction(e -> {

            chargerTable();

            viderFormulaire();

            txtRecherche.clear();

            rendezVousSelectionne = null;

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

        ObservableList<RendezVous> liste =
                controller.chargerRendezVous();

        table.setItems(liste);

    }

    private void viderFormulaire() {

        datePicker.setValue(null);

        txtHeure.clear();

        txtMotif.clear();

        comboStatut.setValue(null);

        txtPatient.clear();

        txtMedecin.clear();

    }

}
