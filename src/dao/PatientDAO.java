package dao;

import model.Patient;
import utils.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean ajouterPatient(Patient patient) {

        String sql = "INSERT INTO patient(nom, prenom, date_naissance, sexe, telephone, adresse, groupe_sanguin, actif) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getNom());
            ps.setString(2, patient.getPrenom());

            if (patient.getDateNaissance() != null) {
                ps.setDate(3, Date.valueOf(patient.getDateNaissance()));
            } else {
                ps.setDate(3, null);
            }

            ps.setString(4, patient.getSexe());
            ps.setString(5, patient.getTelephone());
            ps.setString(6, patient.getAdresse());
            ps.setString(7, patient.getGroupeSanguin());
            ps.setBoolean(8, patient.isActif());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Patient> getAllPatients() {

        List<Patient> liste = new ArrayList<>();

        String sql = "SELECT * FROM patient";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Patient p = new Patient();

                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));

                Date d = rs.getDate("date_naissance");
                if (d != null) {
                    p.setDateNaissance(d.toLocalDate());
                }

                p.setSexe(rs.getString("sexe"));
                p.setTelephone(rs.getString("telephone"));
                p.setAdresse(rs.getString("adresse"));
                p.setGroupeSanguin(rs.getString("groupe_sanguin"));
                p.setActif(rs.getBoolean("actif"));

                liste.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    public boolean modifierPatient(Patient patient) {

        String sql = "UPDATE patient SET nom=?, prenom=?, date_naissance=?, sexe=?, telephone=?, adresse=?, groupe_sanguin=?, actif=? WHERE id_patient=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getNom());
            ps.setString(2, patient.getPrenom());

            if (patient.getDateNaissance() != null) {
                ps.setDate(3, Date.valueOf(patient.getDateNaissance()));
            } else {
                ps.setDate(3, null);
            }

            ps.setString(4, patient.getSexe());
            ps.setString(5, patient.getTelephone());
            ps.setString(6, patient.getAdresse());
            ps.setString(7, patient.getGroupeSanguin());
            ps.setBoolean(8, patient.isActif());
            ps.setInt(9, patient.getIdPatient());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerPatient(int idPatient) {

        String sql = "DELETE FROM patient WHERE id_patient=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPatient);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Patient> rechercherParNom(String nom) {

        List<Patient> liste = new ArrayList<>();

        String sql = "SELECT * FROM patient WHERE nom LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nom + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient p = new Patient();

                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));

                Date d = rs.getDate("date_naissance");
                if (d != null) {
                    p.setDateNaissance(d.toLocalDate());
                }

                p.setSexe(rs.getString("sexe"));
                p.setTelephone(rs.getString("telephone"));
                p.setAdresse(rs.getString("adresse"));
                p.setGroupeSanguin(rs.getString("groupe_sanguin"));
                p.setActif(rs.getBoolean("actif"));

                liste.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

}
