import java.io.*;
import java.util.*;

public class Esercizio3 {
    public static void main(String[] args) {

        if (args.length<1) {
            System.out.println("Mancano dei dati dalla linea di comando");
            return;
        }

        Locale.setDefault(Locale.US);
        String filename = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int numeroFornitori = Integer.parseInt(reader.readLine());
            ArrayList<Fornitore> elencoFornitori = new ArrayList<Fornitore>(numeroFornitori);

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length == 3) {
                    int idFornitore = Integer.parseInt(tokens[0]);
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    Cliente cliente = new Cliente(idFornitore, x, y);

                    boolean fornExist = false;
                    int counter = 0;

                    while (counter < elencoFornitori.size()) {
                        if (elencoFornitori.get(counter).getId() == idFornitore) {
                            elencoFornitori.get(counter).getListaClienti().add(cliente);
                            fornExist = true;
                            break;
                        }
                        counter++;
                    }
                    if (!fornExist ) {
                        Fornitore f = new Fornitore(cliente);
                        elencoFornitori.add(f);
                    }
                }

            }

            for (int i = 0; i != numeroFornitori; i++) {
                if(i< elencoFornitori.size()){
                    double minimumSpanningTreeWeight = elencoFornitori.get(i).getMinimumSpanningTreeWeightPrim();
                    System.out.println(minimumSpanningTreeWeight);

                }else{
                    System.out.println(0.0);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file " + e.getMessage());
        }
    }

}
 class Fornitore {
    int id;
    ArrayList<Cliente> clienti = new ArrayList<Cliente>();

    public Fornitore(Cliente cliente) {
        this.id = cliente.getIdFornitore();
        clienti.add(cliente);
    }

    public int getId() {
        return id;
    }

     public ArrayList<Cliente> getListaClienti() {
         return clienti;
     }

    private double getManhattanDistance(Cliente c1, Cliente c2) {
        return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
    }

    public double getMinimumSpanningTreeWeightPrim() {
        int numClienti = clienti.size();
        double[][] graph = new double[numClienti][numClienti];
        for (int i = 0; i < numClienti; i++) {
            for (int j = 0; j < numClienti; j++) {
                double distance = getManhattanDistance(clienti.get(i), clienti.get(j));
                graph[i][j] = distance;
            }
        }

        boolean[] visited = new boolean[numClienti];
        double[] weights = new double[numClienti];

        //Imposto tutti i pesi al valore massimo Double(per rappresentare l'infinito)
        Arrays.fill(weights, Double.MAX_VALUE);
        weights[0] = 0;

        for (int i = 0; i < numClienti - 1; i++) {
            int minIndex = -1;
            double minWeight = Double.MAX_VALUE;
            for (int j = 0; j < numClienti; j++) {
                if (!visited[j] && weights[j] < minWeight) {
                    minIndex = j;
                    minWeight = weights[j];
                }
            }
            if (minIndex == -1)
                break;
            visited[minIndex] = true;

            for (int j = 0; j < numClienti; j++) {
                if (!visited[j] && graph[minIndex][j] != 0 && graph[minIndex][j] < weights[j]) {
                    weights[j] = graph[minIndex][j];
                }
            }
        }

        double lunghezzaCavo = 0;
        for (int i = 0; i < numClienti; i++) {
            lunghezzaCavo += weights[i];
        }

        return lunghezzaCavo;
    }
}

 class Cliente {
    int idFornitore;
    double x;
    double y;

    public Cliente(int idFornitore, double x, double y) {
        this.idFornitore = idFornitore;
        this.x = x;
        this.y = y;
    }

     public int getIdFornitore() {
         return idFornitore;
     }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return x + ";" + y;
    }
}
