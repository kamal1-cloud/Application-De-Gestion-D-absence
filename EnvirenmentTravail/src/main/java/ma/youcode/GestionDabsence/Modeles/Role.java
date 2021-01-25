package ma.youcode.GestionDabsence.Modeles;

public class Role {
    private long idRole;
    private String nom;




    public Role() {

    }
    public Role(long idRole, String nom) {
        this.idRole = idRole;
        this.nom = nom;
    }


    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
