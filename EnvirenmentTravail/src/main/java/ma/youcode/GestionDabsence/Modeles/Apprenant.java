package ma.youcode.GestionDabsence.Modeles;


import java.util.Date;

public class Apprenant extends User {
    private int classe;
    private int specialite;
    private int promotion;
    private boolean isFirstYear;



    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, boolean isAdmin) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance, "apprenant", isAdmin);
    }
    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance, "apprenant");
    }

    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe, int specialite, int promotion, boolean isAdmin) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance, "apprenant", isAdmin);
        this.classe = classe;
        this.specialite = specialite;
        this.promotion = promotion;
    }

    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, int classe, int specialite, int promotion) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance, "apprenant");
        this.classe = classe;
        this.specialite = specialite;
        this.promotion = promotion;
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

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public boolean isFirstYear() {
        return isFirstYear;
    }

    public void setFirstYear(boolean firstYear) {
        isFirstYear = firstYear;
    }
}