package ma.youcode.GestionDabsence;

import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;

public class AlertBox {

    public static void display(String title) throws IOException {
        Stage window = new Stage();

        window.setResizable(false);
        window.setTitle(title);

        Parent root = FXMLLoader.load(AlertBox.class.getResource("addUser.fxml"));
        //Parent quiz = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

}