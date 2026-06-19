package dao;

import model.Patient;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean ajouterPatient(Patient patient) {

        String sql =
                "INSERT INTO patient(nom,prenom,date_naissance,sexe,telephone,ville,groupe_sanguin,dernier_don,actif) VALUES(?,?,?,?,?,?,?,?,?)";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1, patient.getNom());
            ps.setString(2, patient.getPrenom());

            if(patient.getDateNaissance()!=null)
                ps.setDate(3, Date.valueOf(patient.getDateNaissance()));
            else
                ps.setDate(3, null);

            ps.setString(4, patient.getSexe());

            ps.setString(5, patient.getTelephone());

            ps.setString(6, patient.getVille());

            ps.setString(7, patient.getGroupeSanguin());

            if(patient.getDernierDon()!=null)
                ps.setDate(8, Date.valueOf(patient.getDernierDon()));
            else
                ps.setDate(8, null);

            ps.setBoolean(9, patient.isActif());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<Patient> getAllPatients(){

        List<Patient> liste=new ArrayList<>();

        String sql="SELECT * FROM patient";

        try(Connection conn=Database.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql)){

            while(rs.next()){

                Patient p=new Patient();

                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));

                if(rs.getDate("date_naissance")!=null)
                    p.setDateNaissance(rs.getDate("date_naissance").toLocalDate());

                p.setSexe(rs.getString("sexe"));
                p.setTelephone(rs.getString("telephone"));
                p.setVille(rs.getString("ville"));
                p.setGroupeSanguin(rs.getString("groupe_sanguin"));
                p.setActif(rs.getBoolean("actif"));

                if(rs.getDate("dernier_don")!=null)
                    p.setDernierDon(
                            rs.getDate("dernier_don").toLocalDate()
                    );

                liste.add(p);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

    public boolean modifierPatient(Patient patient){

        String sql =
                "UPDATE patient SET nom=?,prenom=?,date_naissance=?,sexe=?,telephone=?,ville=?,groupe_sanguin=?,dernier_don=?,actif=? WHERE id_patient=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,patient.getNom());
            ps.setString(2,patient.getPrenom());

            if(patient.getDateNaissance()!=null)
                ps.setDate(3,Date.valueOf(patient.getDateNaissance()));
            else
                ps.setDate(3,null);

            ps.setString(4,patient.getSexe());

            ps.setString(5,patient.getTelephone());

            ps.setString(6,patient.getVille());

            ps.setString(7,patient.getGroupeSanguin());

            if(patient.getDernierDon()!=null)
                ps.setDate(8,Date.valueOf(patient.getDernierDon()));
            else
                ps.setDate(8,null);

            ps.setBoolean(9,patient.isActif());

            ps.setInt(10,patient.getIdPatient());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public boolean supprimerPatient(int id){

        String sql="DELETE FROM patient WHERE id_patient=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1,id);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<Patient> rechercherParNom(String nom){

        List<Patient> liste=new ArrayList<>();

        String sql="SELECT * FROM patient WHERE nom LIKE ?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,"%"+nom+"%");

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Patient p=new Patient();

                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));

                if(rs.getDate("date_naissance")!=null)
                    p.setDateNaissance(rs.getDate("date_naissance").toLocalDate());

                p.setSexe(rs.getString("sexe"));
                p.setTelephone(rs.getString("telephone"));
                p.setVille(rs.getString("ville"));
                p.setGroupeSanguin(rs.getString("groupe_sanguin"));
                p.setActif(rs.getBoolean("actif"));

                if(rs.getDate("dernier_don")!=null)
                    p.setDernierDon(
                            rs.getDate("dernier_don").toLocalDate()
                    );
                liste.add(p);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

    public Patient chercherParNomPrenom(String texte){

        String sql =
                "SELECT * FROM patient WHERE CONCAT(nom,' ',prenom)=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,texte);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                Patient p=new Patient();

                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));

                return p;

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

}