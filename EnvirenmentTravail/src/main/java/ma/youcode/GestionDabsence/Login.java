package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.User;

public class Login {

    SingletonConnectedUser singletonConnectedUser;
    @FXML
    private JFXButton login;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private UserDaoImp userDaoImp;



    public Login() {
        userDaoImp = new UserDaoImp();
    }

    @FXML
    void loginUser(ActionEvent event) {
        String usernameValue = "";
        usernameValue = username.getText();

        String passwordValue = "";
        passwordValue = password.getText();

        try {
            User user = userDaoImp.getUserByEmailOrCin(usernameValue, passwordValue);
            if (user != null) {
                singletonConnectedUser = SingletonConnectedUser.getSingletonConnectedUser(user);
                System.out.println(singletonConnectedUser.connectedUser.getNom() + singletonConnectedUser.connectedUser.getCIN());
                if (user.isAdmin()) {
                    App.setRoot("admin");
                }
            }
            else {
                System.out.println("error while connecting to database");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
