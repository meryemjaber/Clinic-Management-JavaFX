package controller;

import dao.RendezVousDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RendezVous;

import java.util.List;

public class RendezVousController {

    private RendezVousDAO dao;

    public RendezVousController() {

        dao = new RendezVousDAO();

    }

    public ObservableList<RendezVous> chargerRendezVous() {

        return FXCollections.observableArrayList(
                dao.getAllRendezVous()
        );

    }

    public boolean ajouterRendezVous(RendezVous rdv) {

        return dao.ajouterRendezVous(rdv);

    }

    public boolean modifierRendezVous(RendezVous rdv) {

        return dao.modifierRendezVous(rdv);

    }

    public boolean supprimerRendezVous(int id) {

        return dao.supprimerRendezVous(id);

    }

    public ObservableList<RendezVous> rechercherRendezVous(String motif) {

        return FXCollections.observableArrayList(
                dao.rechercherParMotif(motif)
        );

    }

}