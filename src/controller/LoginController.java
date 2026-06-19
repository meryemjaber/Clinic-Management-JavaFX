package controller;

import dao.UtilisateurDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Utilisateur;
import utils.Session;
import view.MainView;

public class LoginController {

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMessage;

    @FXML
    public void seConnecter() {

        String login = txtLogin.getText();

        String password = txtPassword.getText();

        UtilisateurDAO dao = new UtilisateurDAO();

        Utilisateur utilisateur = dao.login(login, password);

        if (utilisateur != null) {

            Session.setUtilisateur(utilisateur);

            Stage stage = (Stage) txtLogin.getScene().getWindow();

            MainView root = new MainView();

            Scene scene = new Scene(root, 1200, 750);

            stage.setTitle("Gestion de Clinique");

            stage.setScene(scene);

            stage.show();

        } else {

            lblMessage.setText("Nom, email ou mot de passe incorrect");

        }

    }

}