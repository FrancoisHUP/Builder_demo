package App;

import Serveur.Serveur;
import personnel.*;
import vue.UserInterface;

public class App {


    public App() {
        initServeur();
        new UserInterface();
    }

    private void initServeur() {
        Serveur serveur = Serveur.getInstance();
        serveur.addTable("patient");
    }


}
