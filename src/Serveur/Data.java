package Serveur;

import java.util.HashMap;

public class Data<k,v> {

    private HashMap<k,v> data = new HashMap<>();

    public void setKeyValue(k key,v value){
        this.data.put(key, value);
    }

    public v getValueOfkey(k key) {
        return data.get(key);
    }

    @Override
    public String toString() {
        return "Serveur.Data{" +
                "data=" + data +
                '}';
    }

    public boolean keyIsDefine(String key) {
        return data.containsKey(key);
    }
}