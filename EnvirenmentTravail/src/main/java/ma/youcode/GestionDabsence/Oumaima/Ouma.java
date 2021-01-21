package ma.youcode.GestionDabsence.Oumaima;

import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDAO;
import ma.youcode.GestionDabsence.DAO.SecretaireDAO.SecretaireDaoImp;
import ma.youcode.GestionDabsence.Modeles.Secretaire;
import ma.youcode.GestionDabsence.Services.SecretaireServices;

import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ouma {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SecretaireDaoImp daoSecretaire = new SecretaireDaoImp();

        List<Secretaire> secretaires= new ArrayList<Secretaire>();
        secretaires = daoSecretaire.getAll();


        for (int i = 0; i <  secretaires.size(); i++) {
            System.out.println( secretaires.get(i).getNom());
            System.out.println( secretaires.get(i).getPrenom());
            System.out.println( secretaires.get(i).getNumTele());
            System.out.println( secretaires.get(i).getDateNaissance());
            System.out.println( secretaires.get(i).getCIN());
            System.out.println( secretaires.get(i).getEmail());
            System.out.println( secretaires.get(i).getPassword());

        }

        //getById

        /*new Secretaire();
        Secretaire secretaire;
        secretaire=daoSecretaire.getById((int) 3);

        System.out.println(secretaire.getDateNaissance());*/

        //Save

        /*Secretaire secretaire1;
        secretaire1 = daoSecretaire.sauveSecretaire("metreu", "oumaima", "0865432456", "ouma@gmail.com", "ouma123", "HA5677", "1997-09-30");*/


        //update
        /*daoSecretaire.updateSecretaire(5,"met", "oumaima", "0865432456", "ouma@gmail.com", "ouma123", "HA5677", "1997-09-30");*/

        //delete

        daoSecretaire.deleteById(2);
    }
}
