package personnel;

public class InfirmierBuilder extends PersonnelBuilder {
    @Override
    public void setEmploie() {
        personnel.setEmploie(Emploie.INFIRMIER);
    }
}
