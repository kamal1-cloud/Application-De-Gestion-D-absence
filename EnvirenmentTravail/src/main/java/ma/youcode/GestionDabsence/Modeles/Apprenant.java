package ma.youcode.GestionDabsence.Modeles;


import java.util.Date;

public class Apprenant extends User {

    private int classe;
    private int specialite;

    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance, "apprenant");
    }

    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe, int specialite) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance, "apprenant");
        this.classe = classe;
        this.specialite = specialite;
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

}