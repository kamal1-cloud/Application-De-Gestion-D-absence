package ma.youcode.GestionDabsence.helper;


import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import ma.youcode.GestionDabsence.App;

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();


        try {
            //Block events to other windows
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);

            Label label = new Label();
            label.setText(message);
            Button closeButton = new Button("Close this window");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);


            /** load fxml file */
            //ma/youcode/GestionDabsence/addUser.fxml
            Parent root = FXMLLoader.load(AlertBox.class.getResource("./ma/youcode/GestionDabsence/addUser.fxml"));
            window.setScene(new Scene(root));
            //Display window and wait for it to be closed before returning
            // Scene scene = new Scene(layout);
            //window.setScene(scene);
            window.showAndWait();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}