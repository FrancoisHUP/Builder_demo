package personnel;

import java.util.Date;

public class Directeur {

    private PersonnelBuilder personnelBuilder;

    public void setPersonnelBuilder(PersonnelBuilder personnelBuilder) { this.personnelBuilder = personnelBuilder; }
    public Personnel getPersonnel() { return personnelBuilder.getPersonnel(); }

    public void buildPersonnel(String nom, Date dateCreation) {
        personnelBuilder.nouveauPersonnel();
        personnelBuilder.setNom(nom);
        personnelBuilder.setNumEmploye();
        personnelBuilder.setDateCreation(dateCreation);
        personnelBuilder.setEmploie();
    }

}
