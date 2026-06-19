package model;

import java.time.LocalDate;

public class Patient {

    private int idPatient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String sexe;
    private String telephone;
    private String adresse;
    private String groupeSanguin;
    private String ville;
    private boolean disponibleDon;
    private boolean actif;
    private LocalDate dernierDon;

    public Patient() {
    }

    public Patient(int idPatient,
                   String nom,
                   String prenom,
                   LocalDate dateNaissance,
                   String sexe,
                   String telephone,
                   String adresse,
                   String groupeSanguin,
                   String ville,
                   boolean disponibleDon,
                   boolean actif) {

        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.telephone = telephone;
        this.adresse = adresse;
        this.groupeSanguin = groupeSanguin;
        this.ville = ville;
        this.disponibleDon = disponibleDon;
        this.actif = actif;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public boolean isDisponibleDon() {
        return disponibleDon;
    }

    public void setDisponibleDon(boolean disponibleDon) {
        this.disponibleDon = disponibleDon;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public LocalDate getDernierDon() {
        return dernierDon;
    }

    public void setDernierDon(LocalDate dernierDon) {
        this.dernierDon = dernierDon;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

}