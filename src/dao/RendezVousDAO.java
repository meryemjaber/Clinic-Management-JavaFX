package dao;

import model.RendezVous;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {

    public boolean ajouterRendezVous(RendezVous rdv){

        String sql="INSERT INTO rendez_vous(date_rdv,heure,motif,statut,patient_id,medecin_id) VALUES(?,?,?,?,?,?)";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setDate(1,Date.valueOf(rdv.getDateRdv()));
            ps.setTime(2,Time.valueOf(rdv.getHeure()));
            ps.setString(3,rdv.getMotif());
            ps.setString(4,rdv.getStatut());
            ps.setInt(5,rdv.getPatientId());
            ps.setInt(6,rdv.getMedecinId());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<RendezVous> getAllRendezVous(){

        List<RendezVous> liste=new ArrayList<>();

        String sql="SELECT * FROM rendez_vous";

        try(Connection conn=Database.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql)){

            while(rs.next()){

                RendezVous rdv=new RendezVous();

                rdv.setIdRdv(rs.getInt("id_rdv"));
                rdv.setDateRdv(rs.getDate("date_rdv").toLocalDate());
                rdv.setHeure(rs.getTime("heure").toLocalTime());
                rdv.setMotif(rs.getString("motif"));
                rdv.setStatut(rs.getString("statut"));
                rdv.setPatientId(rs.getInt("patient_id"));
                rdv.setMedecinId(rs.getInt("medecin_id"));

                liste.add(rdv);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

    public boolean modifierRendezVous(RendezVous rdv){

        String sql="UPDATE rendez_vous SET date_rdv=?,heure=?,motif=?,statut=?,patient_id=?,medecin_id=? WHERE id_rdv=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setDate(1,Date.valueOf(rdv.getDateRdv()));
            ps.setTime(2,Time.valueOf(rdv.getHeure()));
            ps.setString(3,rdv.getMotif());
            ps.setString(4,rdv.getStatut());
            ps.setInt(5,rdv.getPatientId());
            ps.setInt(6,rdv.getMedecinId());
            ps.setInt(7,rdv.getIdRdv());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public boolean supprimerRendezVous(int id){

        String sql="DELETE FROM rendez_vous WHERE id_rdv=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1,id);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<RendezVous> rechercherParMotif(String motif){

        List<RendezVous> liste=new ArrayList<>();

        String sql="SELECT * FROM rendez_vous WHERE motif LIKE ?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,"%"+motif+"%");

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                RendezVous rdv=new RendezVous();

                rdv.setIdRdv(rs.getInt("id_rdv"));
                rdv.setDateRdv(rs.getDate("date_rdv").toLocalDate());
                rdv.setHeure(rs.getTime("heure").toLocalTime());
                rdv.setMotif(rs.getString("motif"));
                rdv.setStatut(rs.getString("statut"));
                rdv.setPatientId(rs.getInt("patient_id"));
                rdv.setMedecinId(rs.getInt("medecin_id"));

                liste.add(rdv);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

}