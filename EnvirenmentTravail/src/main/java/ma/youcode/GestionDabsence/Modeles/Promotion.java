package ma.youcode.GestionDabsence.Modeles;

import java.sql.Date;

public class Promotion {
    private Long idPromotion;
    private String Nom;
    private Date annee;

    public Promotion() {

    }

    public Promotion(Long idPromotion, String nom, Date annee) {
        this.idPromotion = idPromotion;
        Nom = nom;
        this.annee = annee;
    }

    public Long getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Long idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Date getAnnee() {
        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }
}
