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
    private String dateNaissance;
    private Long classe;
    private Long specialite;
    private int role;

    /**public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String password, String CIN, String dateNaissance, Long classe, Long specialite) {
        this.idApprenant = idApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.numTele = numTele;
        this.email = email;
        this.password = password;
        this.CIN = CIN;
        this.dateNaissance = dateNaissance;
        this.classe = classe;
        this.specialite = specialite;
    }
*/
    public Apprenant(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance) {
        this.idApprenant = idApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.numTele = numTele;
        this.email = email;
       // this.password = password;
        this.CIN = CIN;
        this.dateNaissance = dateNaissance;
        //this.role = role;
    }

    public Long getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(Long idApprenant) {
        this.idApprenant = idApprenant;
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

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classe) {
        this.classe = classe;
    }

    public Long getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Long specialite) {
        this.specialite = specialite;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}