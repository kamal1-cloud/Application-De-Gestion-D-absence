package ma.youcode.GestionDabsence.Modeles;

public class Absence {
    private int id;
    private String dateDebu;
    private String dateFin;
    private byte isJustifie;
    private boolean retard;
    private Long idApprenant;
    private String ts;

    public Absence(int id, String dateDebu, String dateFin, byte isJustifie, boolean retard, Long idApprenant, String ts) {
        this.id = id;
        this.dateDebu = dateDebu;
        this.dateFin = dateFin;
        this.isJustifie = isJustifie;
        this.retard = retard;
        this.idApprenant = idApprenant;
        this.ts = ts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte getIsJustifie() {
        return isJustifie;
    }

    public void setIsJustifie(byte isJustifie) {
        this.isJustifie = isJustifie;
    }

    public boolean isRetard() {
        return retard;
    }

    public void setRetard(boolean retard) {
        this.retard = retard;
    }

    public Long getIdApprenant() {
        return idApprenant;
    }

    public void setIdApprenant(Long idApprenant) {
        this.idApprenant = idApprenant;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
