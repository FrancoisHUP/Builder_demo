package personnel;

import serveur.Data;

import java.util.Date;

public class Personnel implements dataAdapter {

    private String nom;
    private Date dateCreation;
    private Emploie emploie;
    private int numEmploye;

    private static int compteurEmploye = 0;

    public void setEmploie(Emploie emploie) {
        this.emploie = emploie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumEmploye() {
        this.numEmploye = compteurEmploye++;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNom() {
        return nom;
    }

    // Adapter
    public Data toData() {
        Data data = new Data();
        data.setKeyValue("nom", nom);
        data.setKeyValue("dateCreation", dateCreation);
        data.setKeyValue("numEmploye", numEmploye);
        data.setKeyValue("emploie", emploie);
        return data;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "nom='" + nom + '\'' +
                ", dateCreation=" + dateCreation +
                ", emploie=" + emploie +
                '}';
    }
}
