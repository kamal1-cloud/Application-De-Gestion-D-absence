package ma.youcode.GestionDabsence.Modeles;

public class AbcenceAppre extends Apprenant{
    String dateDebu;
    String dateFin;
    Byte isJustifie;
    boolean retard;
    String ts;
    public AbcenceAppre(Long idApprenant, String nom, String prenom, String numTele, String email, String CIN, String dateNaissance, String dateDebu, String dateFin,  Byte isJustifie) {
        super(idApprenant, nom, prenom, numTele, email, CIN, dateNaissance);
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

    public Byte getIsJustifie() {
        return isJustifie;
    }

    public void setIsJustifie(Byte isJustifie) {
        this.isJustifie = isJustifie;
    }

    public boolean isRetard() {
        return retard;
    }

    public void setRetard(boolean retard) {
        this.retard = retard;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
