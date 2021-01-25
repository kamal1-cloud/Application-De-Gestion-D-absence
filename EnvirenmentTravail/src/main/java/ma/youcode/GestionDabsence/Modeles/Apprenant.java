package ma.youcode.GestionDabsence.Modeles;


import java.util.Date;

public class Apprenant {
    private Long idApprenant;
    private String nom;
    private String prenom;
    private String numTele;
    private String email;
    private String password;
    private String CIN;
    private Date dateNaissance ;
    private Long classe;

    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance, Long classe, Long specialite) {
        this.idApprenant = idApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.numTele = numTele;
        this.email = email;
        this.password = password;
        this.CIN = CIN;
        this.dateNaissance = dateNaissance;
        this.classe = classe;
    }
}
