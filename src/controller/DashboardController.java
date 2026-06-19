package controller;

import dao.PatientDAO;
import dao.MedecinDAO;
import dao.RendezVousDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LigneDashboard;
import utils.Database;
import dao.UtilisateurDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public int nombreUtilisateurs() {

        return new UtilisateurDAO().getAllUtilisateurs().size();

    }

    public ObservableList<LigneDashboard> chargerRendezVousDuJour() {

        ObservableList<LigneDashboard> liste =
                FXCollections.observableArrayList();

        try {

            Connection conn = Database.getConnection();

            String sql =
                    "SELECT r.heure, " +
                            "CONCAT(p.nom,' ',p.prenom) patient, " +
                            "CONCAT(m.nom,' ',m.prenom) medecin, " +
                            "r.motif " +
                            "FROM rendez_vous r " +
                            "JOIN patient p ON r.patient_id=p.id_patient " +
                            "JOIN medecin m ON r.medecin_id=m.id_medecin " +
                            "WHERE r.date_rdv=CURDATE() " +
                            "ORDER BY r.heure";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                liste.add(

                        new LigneDashboard(

                                rs.getString("heure"),

                                rs.getString("patient"),

                                rs.getString("medecin"),

                                rs.getString("motif")

                        )

                );

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

}