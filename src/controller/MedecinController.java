package controller;

import dao.MedecinDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Medecin;

import java.util.List;

public class MedecinController {

    private MedecinDAO dao;

    public MedecinController() {

        dao = new MedecinDAO();

    }

    public ObservableList<Medecin> chargerMedecins() {

        List<Medecin> liste = dao.getAllMedecins();

        return FXCollections.observableArrayList(liste);

    }

    public boolean ajouterMedecin(Medecin medecin) {

        return dao.ajouterMedecin(medecin);

    }

    public boolean modifierMedecin(Medecin medecin) {

        return dao.modifierMedecin(medecin);

    }

    public boolean supprimerMedecin(int id) {

        return dao.supprimerMedecin(id);

    }

    public ObservableList<Medecin> rechercherMedecin(String nom) {

        return FXCollections.observableArrayList(

                dao.rechercherParNom(nom)

        );

    }

    public Medecin chercherParNomPrenomId(int id){

        for(Medecin m : dao.getAllMedecins()){

            if(m.getIdMedecin()==id){

                return m;

            }

        }

        return null;

    }

}