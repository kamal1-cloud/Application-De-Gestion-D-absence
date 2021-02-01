package ma.youcode.GestionDabsence.Modeles;

import java.math.BigInteger;
import java.util.Date;

public class Formateur extends User {
    private int classe;
    private int specialite;
    private boolean isFirstYear;

    public Formateur(Long id, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe, int specialite) {
        super(id, nom, prenom, numTele, email, CIN, dateNaissance, "formateur");
        this.classe = classe;
        this.specialite = specialite;
    }
    public Formateur(Long id, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe) {
        super(id, nom, prenom, numTele, email, CIN, dateNaissance, "formateur");
        this.classe = classe;
    }

    public Formateur(Long idApprenant, String nom, String prenom, String numTele, String email, String cin, String dateNaissance) {
        super(idApprenant, nom, prenom, numTele, email, cin, dateNaissance, "formateur");
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public int getSpecialite() {
        return specialite;
    }

    public void setSpecialite(int specialite) {
        this.specialite = specialite;
    }

    public boolean isFirstYear() {
        return isFirstYear;
    }

    public void setFirstYear(boolean firstYear) {
        isFirstYear = firstYear;
    }
}
