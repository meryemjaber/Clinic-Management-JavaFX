package dao;

import model.Utilisateur;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    // LOGIN
    public Utilisateur login(String login, String password) {

        try {

            Connection cn = Database.getConnection();

            String sql = "SELECT * FROM utilisateur WHERE (nom=? OR email=?) AND mot_de_passe=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, login);
            ps.setString(2, login);
            ps.setString(3, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Utilisateur(

                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    // AFFICHER TOUS LES UTILISATEURS
    public List<Utilisateur> getAllUtilisateurs() {

        List<Utilisateur> liste = new ArrayList<>();

        try {

            Connection cn = Database.getConnection();

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM utilisateur");

            while (rs.next()) {

                liste.add(

                        new Utilisateur(

                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getString("email"),
                                rs.getString("mot_de_passe"),
                                rs.getString("role")

                        )

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return liste;

    }

    // AJOUTER
    public boolean ajouterUtilisateur(Utilisateur u) {

        try {

            Connection cn = Database.getConnection();

            String sql = "INSERT INTO utilisateur(nom,email,mot_de_passe,role) VALUES(?,?,?,?)";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, u.getNom());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getMotDePasse());
            ps.setString(4, u.getRole());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // MODIFIER
    public boolean modifierUtilisateur(Utilisateur u) {

        try {

            Connection cn = Database.getConnection();

            String sql = "UPDATE utilisateur SET nom=?,email=?,mot_de_passe=?,role=? WHERE id=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, u.getNom());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getMotDePasse());
            ps.setString(4, u.getRole());
            ps.setInt(5, u.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // SUPPRIMER
    public boolean supprimerUtilisateur(int id) {

        try {

            Connection cn = Database.getConnection();

            PreparedStatement ps = cn.prepareStatement(

                    "DELETE FROM utilisateur WHERE id=?"

            );

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}