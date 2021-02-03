package ma.youcode.GestionDabsence;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ma.youcode.GestionDabsence.DAO.UserDAO.UserDaoImp;
import ma.youcode.GestionDabsence.Modeles.User;

public class Login {

    SingletonConnectedUser singletonConnectedUser;
    @FXML
    private JFXButton login;

    @FXML
    private Label errorMessage;
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
        Button btn = (Button) event.getSource();
        String usernameValue = "";
        usernameValue = username.getText();

        btn.setDisable(true);
        String passwordValue = "";
        passwordValue = password.getText();
        System.out.println(usernameValue);
        System.out.println(passwordValue);

        try {
            User user = userDaoImp.getUserByEmailOrCin(usernameValue, passwordValue);
            System.out.println(user);
            if (user != null) {
                singletonConnectedUser = SingletonConnectedUser.getSingletonConnectedUser(user);
                System.out.println(singletonConnectedUser.connectedUser.getNom() + singletonConnectedUser.connectedUser.getCIN());
                if (user.isAdmin()) {
                    App.setRoot("admin");
                }else if (user.getRole().equals(GlobalVar.secreture)) {
                    App.setRoot("secScene");
                }else if (user.getRole().equals(GlobalVar.apprenant)) {
                    App.setRoot("apprSecene");
                }else if (user.getRole().equals(GlobalVar.formateur)) {
                    App.setRoot("formateurScene");
                }else {
                    errorMessage.setText("password or email is not correct");
                }
            }
            else {
                errorMessage.setText("password or email is not correct");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            btn.setDisable(false);
        }
    }

}
