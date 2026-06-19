package view;

import dao.BanqueSangDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Patient;

public class BanqueSangView extends BorderPane {

    private ComboBox<String> cmbGroupe = new ComboBox<>();

    private ComboBox<String> cmbVille = new ComboBox<>();

    private CheckBox chkDisponible =
            new CheckBox("Disponible uniquement");

    private Button btnRecherche =
            new Button("🩸 Rechercher");

    private TableView<Patient> table =
            new TableView<>();

    public BanqueSangView() {

        VBox root = new VBox(20);

        root.setPadding(new Insets(20));

        Label titre = new Label(
                "🩸 Banque de sang intelligente"
        );

        titre.setStyle(
                "-fx-font-size:24;" +
                        "-fx-font-weight:bold;"
        );

        cmbGroupe.getItems().addAll(

                "O-",
                "O+",
                "A-",
                "A+",
                "B-",
                "B+",
                "AB-",
                "AB+"

        );

        cmbGroupe.setPromptText(
                "Choisir le groupe du receveur"
        );

        cmbVille.getItems().addAll(

                "Oujda",
                "Berkane",
                "Nador",
                "Taourirt",
                "Jerada"

        );

        cmbVille.setPromptText(
                "Choisir une ville"
        );

        chkDisponible.setSelected(true);

        HBox recherche = new HBox(15);

        recherche.getChildren().addAll(

                new Label("Receveur :"),

                cmbGroupe,

                new Label("Ville :"),

                cmbVille,

                chkDisponible,

                btnRecherche

        );


        TableColumn<Patient,String> colNom =
                new TableColumn<>("Nom");

        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );

        TableColumn<Patient,String> colPrenom =
                new TableColumn<>("Prénom");

        colPrenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom")
        );

        TableColumn<Patient,String> colTel =
                new TableColumn<>("Téléphone");

        colTel.setCellValueFactory(
                new PropertyValueFactory<>("telephone")
        );

        TableColumn<Patient,String> colVille =
                new TableColumn<>("Ville");

        colVille.setCellValueFactory(
                new PropertyValueFactory<>("ville")
        );

        TableColumn<Patient,String> colGroupe =
                new TableColumn<>("Groupe");

        colGroupe.setCellValueFactory(
                new PropertyValueFactory<>("groupeSanguin")
        );

        table.getColumns().addAll(

                colNom,
                colPrenom,
                colTel,
                colVille,
                colGroupe

        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        btnRecherche.setOnAction(e -> {

            if(cmbGroupe.getValue()==null){

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setContentText(
                        "Choisissez un groupe sanguin."
                );

                alert.show();

                return;

            }

            BanqueSangDAO dao =
                    new BanqueSangDAO();

            ObservableList<Patient> liste =
                    FXCollections.observableArrayList(

                            dao.rechercherDonneursCompatibles(

                                    cmbGroupe.getValue(),

                                    cmbVille.getValue(),

                                    chkDisponible.isSelected()

                            )

                    );

            table.setItems(liste);

        });

        root.getChildren().addAll(

                titre,

                recherche,

                table

        );

        setCenter(root);

    }

}