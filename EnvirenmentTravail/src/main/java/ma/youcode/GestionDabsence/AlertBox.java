package ma.youcode.GestionDabsence;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import ma.youcode.GestionDabsence.Modeles.User;

import java.io.IOException;

public class AlertBox {

    static Stage window;
    public static void display(String title) throws IOException {
        window = new Stage();

        window.setResizable(false);
        window.setTitle(title);

        Parent root = FXMLLoader.load(AlertBox.class.getResource("addUser.fxml"));
        //Parent quiz = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

}