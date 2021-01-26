package ma.youcode.GestionDabsence.Modeles;

public class Classe {
    private Long idClasse;
    private String Nom;
    private String salle;
    private boolean isClasse;
    private Long idFormateur;

    public Classe( ) {
     }

    public Classe(Long idClasse, String nom, String salle, Long idFormateur) {
        this.idClasse = idClasse;
        this.Nom = nom;
        this.salle = salle;
        //this.isClasse = isClasse;
        this.idFormateur = idFormateur;
    }



    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public boolean isClasse() {
        return isClasse;
    }

    public void setClasse(boolean classe) {
        isClasse = classe;
    }

    public long getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(long idFormateur) {
        this.idFormateur = idFormateur;
    }
}
