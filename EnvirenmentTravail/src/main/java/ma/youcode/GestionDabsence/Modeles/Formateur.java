package ma.youcode.GestionDabsence.Modeles;

import java.math.BigInteger;
import java.util.Date;

public class Formateur {
    private Long id;
    private String nom;
    private String prenom;
    private String numTele;
    private String email;
    private String password;
    private String CIH;
    private Date dateNaissance ;
    private Long classe;

    public Formateur(Long id, String nom, String prenom, String numTele, String email, String password, String CIH, Date dateNaissance, Long classe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numTele = numTele;
        this.email = email;
        this.password = password;
        this.CIH = CIH;
        this.dateNaissance = dateNaissance;
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNumTele() {
        return numTele;
    }

    public void setNumTele(String numTele) {
        this.numTele = numTele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCIH() {
        return CIH;
    }

    public void setCIH(String CIH) {
        this.CIH = CIH;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classe) {
        this.classe = classe;
    }
}
