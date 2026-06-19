package utils;

import model.Utilisateur;

public class Session {

    private static Utilisateur utilisateur;

    public static void setUtilisateur(Utilisateur u) {
        utilisateur = u;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void logout() {
        utilisateur = null;
    }

}