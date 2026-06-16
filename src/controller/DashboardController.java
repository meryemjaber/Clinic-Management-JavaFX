package controller;

import dao.PatientDAO;
import dao.MedecinDAO;
import dao.RendezVousDAO;

public class DashboardController {

    private PatientDAO patientDAO;
    private MedecinDAO medecinDAO;
    private RendezVousDAO rendezVousDAO;

    public DashboardController() {

        patientDAO = new PatientDAO();
        medecinDAO = new MedecinDAO();
        rendezVousDAO = new RendezVousDAO();

    }

    public int nombrePatients() {
        return patientDAO.getAllPatients().size();
    }

    public int nombreMedecins() {
        return medecinDAO.getAllMedecins().size();
    }

    public int nombreRendezVous() {
        return rendezVousDAO.getAllRendezVous().size();
    }

}