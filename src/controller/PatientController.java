package controller;

import dao.PatientDAO;
import model.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PatientController {

    private PatientDAO patientDAO;

    public PatientController() {

        patientDAO = new PatientDAO();

    }

    public ObservableList<Patient> chargerPatients() {

        List<Patient> liste = patientDAO.getAllPatients();

        return FXCollections.observableArrayList(liste);

    }

    public boolean ajouterPatient(Patient patient) {

        return patientDAO.ajouterPatient(patient);

    }

    public boolean modifierPatient(Patient patient) {

        return patientDAO.modifierPatient(patient);

    }

    public boolean supprimerPatient(int idPatient) {

        return patientDAO.supprimerPatient(idPatient);

    }

    public ObservableList<Patient> rechercherPatient(String nom) {

        return FXCollections.observableArrayList(

                patientDAO.rechercherParNom(nom)

        );

    }
}