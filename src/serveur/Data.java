package serveur;

import java.util.HashMap;

public class Data<K, V> {

    private HashMap<K, V> data = new HashMap<>();

    public void setKeyValue(K key, V value){
        this.data.put(key, value);
    }

    public V getValueOfkey(K key) {
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