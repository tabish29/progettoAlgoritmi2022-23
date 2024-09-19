import java.io.*;
import java.util.*;
/*
Il costo computazionale di questo algoritmo è O(nlogn)
 */
public class Esercizio1 {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Mancano dei dati dalla linea di comando per far partire il programma ");
            return;
        }

        //Numero di coppie che il programma deve stampare
        int m= Integer.parseInt(args[0]);

        String fileName=args[1];
        ArrayList<Pair<Integer, String>> pairs = readPairsFromFile(fileName);

        if (pairs.isEmpty()) {

            System.out.println("Non ci sono coppie nel file passato");

        }else {

            Collections.sort(pairs);
            int count = 0;

            for (Pair<Integer, String> pair : pairs) {
                System.out.println(pair.getKey() + " " + pair.getValue());
                count++;
                if (count >= m) {
                    break;
                }
            }
        }
    }
    private static ArrayList<Pair<Integer, String>> readPairsFromFile(String filename) {
        ArrayList<Pair<Integer, String>> pairs = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length == 2) {
                    int key = Integer.parseInt(tokens[0]);
                    String value = tokens[1];
                    pairs.add(new Pair<>(key, value));
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file " + e.getMessage());
            System.exit(1);
        }

        return pairs;
    }
}
class Pair<Integer extends Comparable<Integer>, String extends Comparable<String>> implements Comparable<Pair<Integer, String>> {
    private final Integer key;
    private final String value;

    public Pair(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair<Integer, String> other) {
        int keyComparison = key.compareTo(other.getKey());
        if (keyComparison == 0) {
            return value.compareTo(other.getValue());
        }
        return keyComparison;
    }
}