package controller;

import javafx.scene.layout.BorderPane;
import view.UtilisateurView;
import view.DashboardView;
import view.PatientView;
import view.MedecinView;
import view.RendezVousView;
import view.BanqueSangView;
import view.AlerteUrgenceView;

public class MainController {

    private BorderPane root;

    public MainController(BorderPane root) {

        this.root = root;

    }

    public void afficherUtilisateurs() {

        root.setCenter(new UtilisateurView());

    }

    public void afficherDashboard() {

        root.setCenter(new DashboardView());

    }

    public void afficherPatients() {

        root.setCenter(new PatientView());

    }

    public void afficherMedecins() {

        root.setCenter(new MedecinView());

    }

    public void afficherRendezVous() {

        root.setCenter(new RendezVousView());

    }

    public void afficherBanqueSang() {

        root.setCenter(new BanqueSangView());

    }

    public void afficherAlerteUrgence() {

        root.setCenter(new AlerteUrgenceView());

    }

}