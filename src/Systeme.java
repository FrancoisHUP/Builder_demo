
import serveur.Serveur;
import vue.UserInterface;

public class Systeme {

    public static void main(String[] args) {
        initServeur();
        new UserInterface();

    }

    private static void initServeur() {
        Serveur serveur = Serveur.getInstance();
        serveur.addTable("personnel");
    }

}
