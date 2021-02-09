package ma.youcode.GestionDabsence.Modeles;

import java.math.BigInteger;
import java.util.Date;

public class Secretaire extends User{
    public Secretaire()
    {

    }
    public Secretaire(Long idSecretaire, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance) {
        super(idSecretaire, nom, prenom, numTele, email, CIN, dateNaissance, "secreture");
    }
    public Secretaire(Long idSecretaire, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, boolean isAdmin) {
        super(idSecretaire, nom, prenom, numTele, email, CIN, dateNaissance, "secreture", isAdmin);
    }
}
