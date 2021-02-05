package ma.youcode.GestionDabsence.Modeles;

import ma.youcode.GestionDabsence.GlobalVar;

public class ApprenantAbsence {
    private String cin;
    private String nom;
    private String prenom;
    private String specialite;
    private String dateDebu;
    private String dateFin;
    private byte isJustifie;
    private  boolean retard;
    private String email;
    private String numTele;
    private int absenceId;
    private String absAccp;
    private String change = "";

    public ApprenantAbsence(){

    }

    public ApprenantAbsence(int absenceId, String cin, String nom, String prenom, String email, String numTele,String dateDebu, String dateFin, byte isJustifie, boolean retard) {
        this.absenceId = absenceId;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        //this.specialite = specialite;
        this.dateDebu = dateDebu;
        this.dateFin = dateFin;
        this.isJustifie = isJustifie;
        this.retard = retard;
        this.email = email;
        this.numTele = numTele;
        if (isJustifie == GlobalVar.ACCEPTER) {
            this.absAccp = "accepter";
            this.change = "refuser";
        }else if (isJustifie == GlobalVar.REFUSER) {
            this.absAccp = "refuser";
            this.change = "accepter";
        }
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

    public byte getIsJustifie() {
        return isJustifie;
    }

    public void setIsJustifie(byte isJustifie) {
        this.isJustifie = isJustifie;
    }

    public boolean isRetard() {
        return retard;
    }

    public void setRetard(boolean retard) {
        this.retard = retard;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTele() {
        return numTele;
    }

    public void setNumTele(String numTele) {
        this.numTele = numTele;
    }

    public int getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(int absenceId) {
        this.absenceId = absenceId;
    }

    public String getAbsAccp() {
        return absAccp;
    }

    public void setAbsAccp(String absAccp) {
        this.absAccp = absAccp;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
