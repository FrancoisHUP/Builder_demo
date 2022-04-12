package personnel;

import serveur.Data;

public interface BuilderInterface {

    void setDirecteur(Emploie emploie);
    Personnel buildEmploye(Data datafield, Emploie emploie);


}
