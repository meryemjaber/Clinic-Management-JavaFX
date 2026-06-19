package model;

public class LigneRdv {

    private String heure;
    private String patient;
    private String medecin;
    private String motif;

    public LigneRdv(String heure, String patient, String medecin, String motif) {
        this.heure = heure;
        this.patient = patient;
        this.medecin = medecin;
        this.motif = motif;
    }

    public String getHeure() {
        return heure;
    }

    public String getPatient() {
        return patient;
    }

    public String getMedecin() {
        return medecin;
    }

    public String getMotif() {
        return motif;
    }
}