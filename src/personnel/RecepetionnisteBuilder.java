package personnel;

public class RecepetionnisteBuilder extends PersonnelBuilder {
    @Override
    public void setEmploie() {
        personnel.setEmploie(Emploie.RECEPTIONNISTE);
    }

}
