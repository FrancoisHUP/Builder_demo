package serveur;

import java.util.Objects;
import java.util.Random;
import static java.lang.Math.abs;

public class UniqId implements Comparable{

    private String uniqId;
    private String nom;
    private long salt;
    private long time;

    private static Random random = new Random();

    public UniqId(String nom){
        this.nom = nom;
        this.uniqId = generateUniqId(nom);
    }

    private String generateUniqId(String nom) {
        salt = abs(random.nextInt());
        time = java.lang.System.currentTimeMillis();
        String uniqId = nom + (time) +""+ (salt);
        return uniqId;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "UniqId{" +
                "uniqId='" + uniqId + '\'' +
                ", nom='" + nom + '\'' +
                ", salt=" + salt +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqId uniqId1 = (UniqId) o;
        return uniqId.equals(uniqId1.uniqId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqId);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return 0;
        UniqId uniqId1 = (UniqId) o;
        return uniqId.compareTo(uniqId1.uniqId);
    }
}
