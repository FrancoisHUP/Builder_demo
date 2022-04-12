package personnel;

import java.util.Date;

public abstract class PersonnelBuilder {

    protected Personnel personnel;

    public void setNom(String nom) { personnel.setNom(nom); }
    public void setNumEmploye() { personnel.setNumEmploye(); }
    public void setDateCreation(Date dateCreation) { personnel.setDateCreation(dateCreation);}

    public abstract void setEmploie();

    public void nouveauPersonnel(){
        personnel = new Personnel();
    }

    public Personnel getPersonnel() {
        return personnel;
    }

}
