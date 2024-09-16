package pe.edu.upeu._enraya.modelo;

public class TicTO {
    private String nomP;   // Cambié nomp a nomP
    private String nom1;
    private String nom2;
    private String nomG;   // Cambié nomgan a nomG
    private String punt;
    private String estad;  // Cambié Estad a estad

    // Getters y Setters

    public String getNomP() { return nomP; }
    public void setNomP(String nomP) { this.nomP = nomP; }

    public String getNom1() { return nom1; }
    public void setNom1(String nom1) { this.nom1 = nom1; }

    public String getNom2() { return nom2; }
    public void setNom2(String nom2) { this.nom2 = nom2; }

    public String getNomG() { return nomG; }
    public void setNomG(String nomG) { this.nomG = nomG; }

    public String getPunt() { return punt; }
    public void setPunt(String punt) { this.punt = punt; }

    public String getEstad() { return estad; }
    public void setEstad(String estad) { this.estad = estad; }

    @Override
    public String toString() {
        return "TicTO{" +
                "nomP='" + nomP + '\'' +
                ", nom1='" + nom1 + '\'' +
                ", nom2='" + nom2 + '\'' +
                ", nomG='" + nomG + '\'' +
                ", punt='" + punt + '\'' +
                ", estad='" + estad + '\'' +
                '}';
    }
}
