package view;

import controller.PatientController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Patient;

public class PatientView extends BorderPane {

    private TextField txtNom = new TextField();
    private TextField txtPrenom = new TextField();
    private DatePicker dateNaissance = new DatePicker();
    private DatePicker dateDernierDon = new DatePicker();
    private TextField txtTelephone = new TextField();
    private ComboBox<String> comboVille = new ComboBox<>();

    private ComboBox<String> comboGroupe = new ComboBox<>();

    private CheckBox chkActif = new CheckBox("Patient actif");

    private RadioButton homme = new RadioButton("Homme");
    private RadioButton femme = new RadioButton("Femme");

    private Button btnAjouter = new Button("Ajouter");
    private Button btnModifier = new Button("Modifier");
    private Button btnSupprimer = new Button("Supprimer");

    private TextField txtRecherche = new TextField();

    private Button btnRecherche = new Button("Rechercher");

    private TableView<Patient> table = new TableView<>();

    private PatientController controller = new PatientController();

    private Patient patientSelectionne = null;

    public PatientView() {

        GridPane form = new GridPane();

        form.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:15;" +
                        "-fx-padding:20;" +
                        "-fx-border-color:#BBDEFB;"
        );

        HBox recherche = new HBox(10);

        txtRecherche.setPromptText("Rechercher par nom");

        recherche.getChildren().addAll(
                new Label("Recherche :"),
                txtRecherche,
                btnRecherche
        );

        setTop(recherche);

        form.setPadding(new Insets(20));
        form.setHgap(10);
        form.setVgap(10);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setMinWidth(140);

        ColumnConstraints c2 = new ColumnConstraints();
        c2.setHgrow(Priority.ALWAYS);

        form.getColumnConstraints().addAll(c1, c2);

        ToggleGroup group = new ToggleGroup();
        homme.setToggleGroup(group);
        femme.setToggleGroup(group);

        comboGroupe.getItems().addAll(
                "A+","A-","B+","B-",
                "AB+","AB-","O+","O-"
        );

        comboVille.getItems().addAll(
                "Oujda",
                "Berkane",
                "Nador",
                "Taourirt",
                "Jerada",
                "Figuig",
                "Rabat",
                "Casablanca",
                "Fès",
                "Marrakech",
                "Agadir",
                "Tanger"
        );

        comboVille.setEditable(true);

        form.add(new Label("Nom"),0,0);
        form.add(txtNom,1,0);

        form.add(new Label("Prénom"),0,1);
        form.add(txtPrenom,1,1);

        form.add(new Label("Date naissance"),0,2);
        form.add(dateNaissance,1,2);

        form.add(new Label("Sexe"),0,3);

        HBox sexe = new HBox(10,homme,femme);

        form.add(sexe,1,3);

        form.add(new Label("Téléphone"),0,4);
        form.add(txtTelephone,1,4);

        form.add(new Label("Ville"),0,5);
        form.add(comboVille,1,5);

        form.add(new Label("Groupe sanguin"),0,6);
        form.add(comboGroupe,1,6);

        form.add(new Label("Dernier don"),0,7);
        form.add(dateDernierDon,1,7);

        form.add(chkActif,1,8);

        HBox boutons = new HBox(10);

        boutons.getChildren().addAll(
                btnAjouter,
                btnModifier,
                btnSupprimer
        );

        form.add(boutons,1,9);

        TableColumn<Patient,Integer> colId =
                new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<>("idPatient"));

        TableColumn<Patient,String> colNom =
                new TableColumn<>("Nom");
        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));

        TableColumn<Patient,String> colPrenom =
                new TableColumn<>("Prénom");
        colPrenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));

        TableColumn<Patient,String> colTelephone =
                new TableColumn<>("Téléphone");
        colTelephone.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));

        TableColumn<Patient,String> colGroupe =
                new TableColumn<>("Groupe");
        colGroupe.setCellValueFactory(
                new PropertyValueFactory<>("groupeSanguin"));

        table.getColumns().addAll(
                colId,
                colNom,
                colPrenom,
                colTelephone,
                colGroupe
        );

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        chargerTable();

        table.getSelectionModel().selectedItemProperty().addListener((obs,ancien,nouveau)->{

            if(nouveau!=null){

                patientSelectionne=nouveau;

                txtNom.setText(nouveau.getNom());
                txtPrenom.setText(nouveau.getPrenom());
                dateNaissance.setValue(nouveau.getDateNaissance());
                txtTelephone.setText(nouveau.getTelephone());
                comboVille.setValue(nouveau.getVille());
                comboGroupe.setValue(nouveau.getGroupeSanguin());
                dateDernierDon.setValue(nouveau.getDernierDon());
                chkActif.setSelected(nouveau.isActif());

                if("Homme".equals(nouveau.getSexe()))
                    homme.setSelected(true);
                else
                    femme.setSelected(true);

            }

        });

        btnAjouter.setOnAction(e->{

            Patient p=new Patient();

            p.setNom(txtNom.getText());
            p.setPrenom(txtPrenom.getText());
            p.setDateNaissance(dateNaissance.getValue());

            if(homme.isSelected())
                p.setSexe("Homme");
            else
                p.setSexe("Femme");

            p.setTelephone(txtTelephone.getText());
            p.setVille(comboVille.getValue());
            p.setGroupeSanguin(comboGroupe.getValue());
            p.setDernierDon(dateDernierDon.getValue());
            p.setActif(chkActif.isSelected());

            controller.ajouterPatient(p);

            chargerTable();

            viderFormulaire();

        });

        btnModifier.setOnAction(e->{

            if(patientSelectionne==null)
                return;

            patientSelectionne.setNom(txtNom.getText());
            patientSelectionne.setPrenom(txtPrenom.getText());
            patientSelectionne.setDateNaissance(dateNaissance.getValue());

            if(homme.isSelected())
                patientSelectionne.setSexe("Homme");
            else
                patientSelectionne.setSexe("Femme");

            patientSelectionne.setTelephone(txtTelephone.getText());
            patientSelectionne.setVille(comboVille.getValue());
            patientSelectionne.setGroupeSanguin(comboGroupe.getValue());
            patientSelectionne.setDernierDon(dateDernierDon.getValue());
            patientSelectionne.setActif(chkActif.isSelected());

            controller.modifierPatient(patientSelectionne);

            chargerTable();

            viderFormulaire();

            patientSelectionne=null;

        });

        btnSupprimer.setOnAction(e -> {

            if (patientSelectionne == null) {

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Attention");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez sélectionner un patient.");

                alert.showAndWait();

                return;
            }

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Confirmation");

            confirmation.setHeaderText("Suppression d'un patient");

            confirmation.setContentText("Voulez-vous vraiment supprimer ce patient ?");

            if (confirmation.showAndWait().get() == ButtonType.OK) {

                controller.supprimerPatient(patientSelectionne.getIdPatient());

                chargerTable();

                viderFormulaire();

                patientSelectionne = null;
            }

        });

        btnRecherche.setOnAction(e -> {

            String nom = txtRecherche.getText();

            if(nom.isEmpty()){

                chargerTable();

            }

            else{

                table.setItems(

                        controller.rechercherPatient(nom)

                );

            }

        });

        SplitPane split=new SplitPane();

        split.getItems().addAll(form,table);

        split.setDividerPositions(0.40);

        setCenter(split);

    }

    private void chargerTable(){

        ObservableList<Patient> liste=
                controller.chargerPatients();

        table.setItems(liste);

    }

    private void viderFormulaire(){

        txtNom.clear();
        txtPrenom.clear();
        dateNaissance.setValue(null);
        txtTelephone.clear();
        comboVille.setValue(null);
        comboGroupe.setValue(null);
        chkActif.setSelected(false);
        homme.setSelected(false);
        femme.setSelected(false);
        dateDernierDon.setValue(null);

    }

}