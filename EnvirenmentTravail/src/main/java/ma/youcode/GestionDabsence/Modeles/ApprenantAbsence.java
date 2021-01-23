package ma.youcode.GestionDabsence.Modeles;

public class ApprenantAbsence {
    private String cin;
    private String nom;
    private String prenom;
    private String specialite;
    private String dateDebu;
    private String dateFin;
    private String isJustifie;
    private  int retard;

    public ApprenantAbsence(){


    }

    public ApprenantAbsence(String cin, String nom, String prenom, String specialite, String dateDebu, String dateFin, String isJustifie, int retard) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.dateDebu = dateDebu;
        this.dateFin = dateFin;
        this.isJustifie = isJustifie;
        this.retard = retard;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getDateDebu() {
        return dateDebu;
    }

    public void setDateDebu(String dateDebu) {
        this.dateDebu = dateDebu;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getIsJustifie() {
        return isJustifie;
    }

    public void setIsJustifie(String isJustifie) {
        this.isJustifie = isJustifie;
    }

    public int getRetard() {
        return retard;
    }

    public void setRetard(int retard) {
        this.retard = retard;
    }
}
