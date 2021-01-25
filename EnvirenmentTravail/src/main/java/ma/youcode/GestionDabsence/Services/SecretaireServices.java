package ma.youcode.GestionDabsence.Services;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDAO;

public class SecretaireServices {



    public void AjoutJustification() {

        initialisation();
    }

    private void initialisation() {
        Label titreLabel = new Label("Ajouter une justification !");
        VBox y = new VBox();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(titreLabel, 0, 0, 2, 1);

        //formulaire
        Label nom = new Label("Nom :");
        grid.add(nom, 0, 1);

        final TextField nomTextField = new TextField();
        grid.add(nomTextField, 1, 1);

        Label prenom = new Label("Prenom :");
        grid.add(prenom, 0, 2);

        final TextField prenomTextField = new TextField();
        grid.add(prenomTextField, 1, 2);

        //button
        Button btnEnregistrer = new Button("Enregistrer");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnEnregistrer);
        grid.add(hbBtn, 1, 4);
    }
}
