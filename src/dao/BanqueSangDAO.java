package dao;

import model.Patient;
import utils.CompatibiliteSang;
import utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BanqueSangDAO {

    public List<Patient> rechercherDonneursCompatibles(

            String groupeReceveur,

            String ville,

            boolean disponible

    ) {

        List<Patient> liste = new ArrayList<>();

        try {

            List<String> groupes =
                    CompatibiliteSang.groupesCompatibles(groupeReceveur);

            Connection conn = Database.getConnection();

            StringBuilder sql = new StringBuilder(

                    "SELECT * FROM patient WHERE groupe_sanguin IN ("

            );

            for (int i = 0; i < groupes.size(); i++) {

                sql.append("?");

                if (i < groupes.size() - 1) {

                    sql.append(",");

                }

            }

            sql.append(")");

            if(ville!=null && !ville.isEmpty()){

                sql.append(" AND ville=?");

            }

            if(disponible){

                sql.append(
                        " AND (dernier_don IS NULL OR dernier_don <= DATE_SUB(CURDATE(), INTERVAL 90 DAY))"
                );

            }

            PreparedStatement ps =
                    conn.prepareStatement(sql.toString());

            for (int i = 0; i < groupes.size(); i++) {

                ps.setString(i + 1, groupes.get(i));

            }

            if(ville!=null && !ville.isEmpty()){

                ps.setString(

                        groupes.size()+1,

                        ville

                );

            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient p = new Patient();

                p.setIdPatient(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setTelephone(rs.getString("telephone"));
                p.setAdresse(rs.getString("adresse"));
                p.setVille(rs.getString("ville"));
                p.setGroupeSanguin(rs.getString("groupe_sanguin"));
                p.setDisponibleDon(rs.getBoolean("disponible_don"));

                liste.add(p);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return liste;

    }

}