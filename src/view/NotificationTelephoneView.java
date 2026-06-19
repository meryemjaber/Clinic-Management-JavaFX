package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NotificationTelephoneView {

    public static void afficher(

            String medecin,

            String patient,

            String salle,

            String urgence,

            String message

    ){

        Stage stage = new Stage();

        stage.initStyle(StageStyle.UNDECORATED);

        VBox root = new VBox(10);

        root.setAlignment(Pos.TOP_LEFT);

        root.setPadding(new Insets(20));

        root.setPrefSize(320,550);

        root.setStyle(

                "-fx-background-color:white;" +

                        "-fx-border-color:black;" +

                        "-fx-border-width:3;" +

                        "-fx-background-radius:25;" +

                        "-fx-border-radius:25;"

        );

        Label tel = new Label("📱 Téléphone du médecin");

        tel.setStyle(

                "-fx-font-size:20;" +

                        "-fx-font-weight:bold;"

        );

        Label notif = new Label(

                "🔔 Nouvelle notification"

        );

        notif.setStyle(

                "-fx-font-size:18;" +

                        "-fx-text-fill:#1565C0;" +

                        "-fx-font-weight:bold;"

        );

        Label contenu = new Label(

                "🏥 Clinique\n\n"+

                        "🚨 CAS D'URGENCE\n\n"+

                        "👨‍⚕️ "+medecin+"\n\n"+

                        "👤 Patient : "+patient+"\n\n"+

                        "🏥 Salle : "+salle+"\n\n"+

                        "🩺 Cas : "+urgence+"\n\n"+

                        message

        );

        contenu.setWrapText(true);

        contenu.setStyle(

                "-fx-font-size:16;"

        );

        root.getChildren().addAll(

                tel,

                notif,

                contenu

        );

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

    }

}