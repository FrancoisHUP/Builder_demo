package personnel;

import Serveur.Data;

import java.util.Date;

public class Builder implements BuilderInterface {

    Directeur directeur = new Directeur();
    PersonnelBuilder builderMedecin = new MedecinBuilder();
    PersonnelBuilder builderInfirmier = new InfirmierBuilder();
    PersonnelBuilder builderRecepetionniste = new RecepetionnisteBuilder();

    public Personnel buildEmploye(Data datafield, Emploie emploie) {
        setDirecteur(emploie);

        // valeur par default
        String nom = "";
        Date dateCreation = new Date(java.lang.System.currentTimeMillis());

        if(datafield.keyIsDefine("nom")){
            nom = (String)datafield.getValueOfkey("nom");
        }

        directeur.buildPersonnel(nom,dateCreation);
        Personnel personnel = directeur.getPersonnel();

        return personnel;
    }

    @Override
    public void setDirecteur(Emploie emploie) {
        if(emploie == Emploie.MEDECIN){
            directeur.setPersonnelBuilder(builderMedecin);
        } else if (emploie == Emploie.INFIRMIER){
            directeur.setPersonnelBuilder(builderInfirmier);
        } else if (emploie == Emploie.RECEPTIONNISTE){
            directeur.setPersonnelBuilder(builderRecepetionniste);
        }
    }
}
