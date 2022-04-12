package personnel;

public class MedecinBuilder extends PersonnelBuilder {
    @Override
    public void setEmploie() {
        personnel.setEmploie(Emploie.MEDECIN);
    }
}
