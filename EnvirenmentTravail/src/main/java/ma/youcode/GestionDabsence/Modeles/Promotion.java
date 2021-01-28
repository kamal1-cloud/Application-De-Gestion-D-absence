package ma.youcode.GestionDabsence.Modeles;

import java.sql.Date;

public class Promotion {
    private int idPromotion;
    private String Nom;
    private String annee;

    public Promotion() {

    }

    public Promotion(int idPromotion, String nom, String annee) {
        this.idPromotion = idPromotion;
        this.Nom = nom;
        this.annee = annee;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
