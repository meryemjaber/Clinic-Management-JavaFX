package dao;

import model.Medecin;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinDAO {

    public boolean ajouterMedecin(Medecin medecin){

        String sql="INSERT INTO medecin(nom,prenom,specialite,telephone,email,disponible) VALUES(?,?,?,?,?,?)";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,medecin.getNom());
            ps.setString(2,medecin.getPrenom());
            ps.setString(3,medecin.getSpecialite());
            ps.setString(4,medecin.getTelephone());
            ps.setString(5,medecin.getEmail());
            ps.setBoolean(6,medecin.isDisponible());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<Medecin> getAllMedecins(){

        List<Medecin> liste=new ArrayList<>();

        String sql="SELECT * FROM medecin";

        try(Connection conn=Database.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql)){

            while(rs.next()){

                Medecin m=new Medecin();

                m.setIdMedecin(rs.getInt("id_medecin"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setSpecialite(rs.getString("specialite"));
                m.setTelephone(rs.getString("telephone"));
                m.setEmail(rs.getString("email"));
                m.setDisponible(rs.getBoolean("disponible"));

                liste.add(m);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

    public boolean modifierMedecin(Medecin medecin){

        String sql="UPDATE medecin SET nom=?,prenom=?,specialite=?,telephone=?,email=?,disponible=? WHERE id_medecin=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,medecin.getNom());
            ps.setString(2,medecin.getPrenom());
            ps.setString(3,medecin.getSpecialite());
            ps.setString(4,medecin.getTelephone());
            ps.setString(5,medecin.getEmail());
            ps.setBoolean(6,medecin.isDisponible());
            ps.setInt(7,medecin.getIdMedecin());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public boolean supprimerMedecin(int id){

        String sql="DELETE FROM medecin WHERE id_medecin=?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1,id);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<Medecin> rechercherParNom(String nom){

        List<Medecin> liste=new ArrayList<>();

        String sql="SELECT * FROM medecin WHERE nom LIKE ?";

        try(Connection conn=Database.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setString(1,"%"+nom+"%");

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Medecin m=new Medecin();

                m.setIdMedecin(rs.getInt("id_medecin"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setSpecialite(rs.getString("specialite"));
                m.setTelephone(rs.getString("telephone"));
                m.setEmail(rs.getString("email"));
                m.setDisponible(rs.getBoolean("disponible"));

                liste.add(m);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return liste;

    }

}