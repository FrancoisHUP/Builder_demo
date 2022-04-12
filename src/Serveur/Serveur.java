package Serveur;

import personnel.Personnel;

import java.util.HashMap;

public class Serveur {

    HashMap<String,HashMap<UniqId,Data>> collections;

    // singleton
    private static Serveur instance;

    private Serveur() {
        collections = new HashMap<>();
    }

    public static Serveur getInstance() {
        if (instance == null) {
            instance = new Serveur();
        }
        return instance;
    }

    public Data getData(String nomTable,UniqId uniqId) throws TableNotFound, RowNotFound {
        if(collections.containsKey(nomTable)) {
            if(collections.get(nomTable).containsKey(uniqId)) {
                //System.out.println(uniqId);
                //System.out.println("contains : " + collections.get(nomTable).containsKey(uniqId));
                //System.out.println("Table : " + collections.get(nomTable));
                return collections.get(nomTable).get(uniqId);
            }
            throw new RowNotFound();
        }
        throw new TableNotFound();
    }

    public void addTable(String nomTable) {
        collections.put(nomTable,new HashMap<>());
    }

    public void setDoc(String nomTable, UniqId uniqId, Personnel personnel) {
        Data dataPeronnel = personnel.toData();
        collections.get(nomTable).put(uniqId,dataPeronnel);
    }

    public class TableNotFound extends Exception {
        public TableNotFound() { super("Table not found in the database"); }
    }
    public class RowNotFound extends Exception {
        public RowNotFound() { super("Row not found in database"); }
    }

    @Override
    public String toString() {
        return "Serveur{" +
                "collections=" + collections +
                '}';
    }
}
