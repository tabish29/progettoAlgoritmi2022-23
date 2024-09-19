import java.util.ArrayList;
import java.util.Random;
/*
Una volta generati i dati all'interno della struttura S,viene salvata la prima coppia prodotta.
Dopo di che viene utilizzato l'algoritmo Mergesort per ordinare la struttura S.
E si usano gli algoritmi di ricerca binaria per poter trovare le coppie
con chiavi maggiori/minori rispetto alla prima coppia prodotta.
Il costo computazionale di questo algoritmo è O(nlogn) causato dall'ordinamento che viene effettuato sulla struttura S.
 */

public class Esercizio2 {
    public static void main(String[] args) {
            // K è il numero di coppie generate
            int K = 16;

            // Creazione e popolamento della struttura dati S con le coppie generate
            ArrayList<Coppia> S = generateData(K);


        Coppia firstPair = S.get(0);

        mergeSort(S);


        System.out.println("Prima coppia memorizzata in S: "+firstPair);

        RicercaBinariaMinKey(S, firstPair.getChiave());

        RicercaBinariaMaxKey(S, firstPair.getChiave());

        System.out.println("Coppia con la chiave più piccola--->" + S.get(0));

        System.out.println("Coppia con la chiave più grande--->" + S.get(S.size()-1));


    }

    private static ArrayList<Coppia> generateData(int K) {
        ArrayList<Coppia> S = new ArrayList<>();

        //Generazione delle coppie dal sensore
        Random random = new Random(154);

        for (int i = 0; i < K; i++) {
            char info = (char) ('A' + random.nextInt(26));
            int key = random.nextInt(2000) + 1;
            S.add(new Coppia(key,info));
        }

        return S;
    }

    private static void printPair(Coppia pair) {
        System.out.println("<" + pair.getChiave() + "," + pair.getInfo() + ">");
    }

    public static void printAllPairs(ArrayList<Coppia> s){
        int counter=1;
        for(  Coppia c: s){
            System.out.println("Coppia n-"+counter+": "+c);
            counter++;
        }
    }

    private static void mergeSort(ArrayList<Coppia> S) {
        if (S.size() > 1) {
            int mid = S.size() / 2;
            ArrayList<Coppia> leftside = new ArrayList<>(S.subList(0, mid));
            ArrayList<Coppia> rightside = new ArrayList<>(S.subList(mid, S.size()));

            mergeSort(leftside);
            mergeSort(rightside);

            // Combina le due metà ordinate
            merge(S, leftside, rightside);
        }

    }

    private static void merge(ArrayList<Coppia> S, ArrayList<Coppia>left,ArrayList<Coppia>right) {
        int indexLeftSide = 0;
        int indexRigthSide = 0;
        int indexOutputList = 0;

        while (indexLeftSide < left.size() && indexRigthSide < right.size()) {
            if (left.get(indexLeftSide).getChiave() <= right.get(indexRigthSide).getChiave()) {
                S.set(indexOutputList, left.get(indexLeftSide));
                indexLeftSide++;
            } else {
                S.set(indexOutputList, right.get(indexRigthSide));
                indexRigthSide++;
            }
            indexOutputList++;
        }

        // Copia gli eventuali elementi rimanenti della lista sinistra
        while (indexLeftSide < left.size()) {
            S.set(indexOutputList, left.get(indexLeftSide));
            indexLeftSide++;
            indexOutputList++;
        }

        // Copia gli eventuali elementi rimanenti della lista destra
        while (indexRigthSide < right.size()) {
            S.set(indexOutputList, right.get(indexRigthSide));
            indexRigthSide++;
            indexOutputList++;
        }

    }

    private static void RicercaBinariaMinKey(ArrayList<Coppia>S, int key) {
        int low = 0;
        int high = S.size() - 1;
        int index = -1;

        while (low <= high) {
            int mid =low + (high- low) /2;

            if (S.get(mid).getChiave() <= key) {
                index = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (index != -1) {
            System.out.println("Coppie con chiavi minori o uguali rispetto alla chiave della prima coppia prodotta:");
            for (int i = 0; i <= index; i++) {
                printPair(S.get(i));
            }
        } else {
            System.out.println("Nessuna coppia con chiave minore di " + key + " trovata.");
        }
    }

    private static void RicercaBinariaMaxKey(ArrayList<Coppia>S, int key) {
        int low = 0;
        int high = S.size() - 1;
        int index = -1;

        while (low <= high) {
            int mid =low + (high- low) /2;

            if (S.get(mid).getChiave() > key) {
                index = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (index != -1) {
            System.out.println("Coppie con chiavi maggiori rispetto alla chiave della prima coppia prodotta:");
            for (int i = index; i < S.size(); i++) {
                printPair(S.get(i));
            }
        } else {
            System.out.println("Nessuna coppia con chiave maggiore di " + key + " trovata.");
        }
    }

}

class Coppia {

    private final int key;

    private final char value;

    public Coppia(int key,char value) {
        this.key = key;
        this.value = value;
    }

    public Integer getChiave() {
        return key;
    }

    public Character getInfo() {
        return value;
    }

    public String toString(){
        return "<"+key+","+value+">";
    }
}