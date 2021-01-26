package ma.youcode.GestionDabsence.Modeles;

public class Specialite {
    private Long idSpecialite;
    private String nom;


    public Specialite() {

    }

    public Specialite(Long idSpecialite, String nom) {
        this.idSpecialite = idSpecialite;
        this.nom = nom;
    }

    public Long getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(Long idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
