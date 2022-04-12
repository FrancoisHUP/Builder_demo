package utils;

public class Paire<F,S> {
    public F first;
    public S second;

    public Paire(F first,S second){
        this.first=first;
        this.second=second;
    }

    @Override
    public String toString() {
        return "Paire{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
