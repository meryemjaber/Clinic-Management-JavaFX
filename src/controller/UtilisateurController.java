package controller;

import dao.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Utilisateur;

import java.util.List;

public class UtilisateurController {

    private UtilisateurDAO dao;

    public UtilisateurController() {

        dao = new UtilisateurDAO();

    }

    public ObservableList<Utilisateur> chargerUtilisateurs() {

        List<Utilisateur> liste = dao.getAllUtilisateurs();

        return FXCollections.observableArrayList(liste);

    }

    public boolean ajouterUtilisateur(Utilisateur utilisateur) {

        return dao.ajouterUtilisateur(utilisateur);

    }

    public boolean modifierUtilisateur(Utilisateur utilisateur) {

        return dao.modifierUtilisateur(utilisateur);

    }

    public boolean supprimerUtilisateur(int id) {

        return dao.supprimerUtilisateur(id);

    }

}