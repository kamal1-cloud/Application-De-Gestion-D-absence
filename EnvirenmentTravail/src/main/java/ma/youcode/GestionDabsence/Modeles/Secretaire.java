package ma.youcode.GestionDabsence.Modeles;

public class Secretaire {

    private BigInteger idSecretaire;
    private String nom;
    private String prenom;
    private String numTele;
    private String email;
    private String password;
    private String CIN;
    private Date dateNaissance;

    public Secretaire(BigInteger idSecretaire, String nom, String prenom, String numTele, String email, String password, String CIN, Date dateNaissance) {
        this.idSecretaire = idSecretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.numTele = numTele;
        this.email = email;
        this.password = password;
        this.CIN = CIN;
        this.dateNaissance = dateNaissance;
    }

    public BigInteger getIdSecretaire() {
        return idSecretaire;
    }

    public void setIdSecretaire(BigInteger idSecretaire) {
        this.idSecretaire = idSecretaire;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
