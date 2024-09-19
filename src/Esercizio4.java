import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
/*
Il costo asintotico dell'algoritmo implementato è O(N^2)
 */

public class Esercizio4 {
    static int N;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Inserire il nome del file dalla linea di comando");
            return;
        }

        String inputFilePath = args[0];

        try {
            String[][] M = readMatrixFromFile(inputFilePath);
            MinimumPath(M);
        } catch(FileNotFoundException f){
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("C'è stato un errore durante la lettura del file: " + e.getMessage());
        }

    }

    public static String[][] readMatrixFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        N = Integer.parseInt(reader.readLine().trim());
        String[][] M = new String[N][N];
        String line;
        int row = 0;

        while ((line = reader.readLine()) != null && row < N) {
            line = line.trim();
            if (line.length() != N) {
                throw new IllegalArgumentException("Il file di input è invalido. Ogni riga dovrebbe avere " + N + " caratteri");
            }

            for (int col = 0; col < N; col++) {
                M[row][col] = String.valueOf(line.charAt(col));
            }

            row++;
        }

        reader.close();
        return M;
    }

   public static boolean IsSafe(int i, int j, String[][] M) {
        if ((i < 0 || i >= N) || (j < 0 || j >= N) || M[i][j].equals("*"))
            return false;
        return true;
    }

   public static void MinimumPath(String[][] M) {
        int sourcePosition = 0;
        int destinationPosition = 0;
        int numberVertex = N * N +1 ;
        Graph g = new Graph(numberVertex);

        int currentVertex = 1; // Number of current vertex
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!M[i][j].equals("*")) {
                    if (IsSafe(i, j + 1, M))
                        g.AddEdge(currentVertex, currentVertex + 1);
                    if (IsSafe(i, j - 1, M))
                        g.AddEdge(currentVertex, currentVertex - 1);
                    if ( IsSafe(i + 1, j, M))
                        g.AddEdge(currentVertex, currentVertex + N);
                    if ( IsSafe(i - 1, j, M))
                        g.AddEdge(currentVertex, currentVertex - N);
                }

                if (M[i][j].equals("s"))
                    sourcePosition = currentVertex;

                if (M[i][j].equals("d"))
                    destinationPosition = currentVertex;
                currentVertex++;
            }
        }

        int shortestPathLength = g.BFS(sourcePosition, destinationPosition);

        if (shortestPathLength > 0) {
            System.out.println("Percorso minimo:");
            g.segnaPercorso(sourcePosition, destinationPosition, N, M);
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M[i].length; j++) {
                    System.out.print(M[i][j]);
                }
                System.out.println();
            }
            System.out.println((shortestPathLength+1));
        } else {
            System.out.println("NON RAGGIUNGIBILE");
        }


    }

}

class Graph {
    private int vertex;
    private ArrayList<ArrayList<Integer>> adjVertex;
    private int[] level;
    private int[] pred; // Array dei predecessori dei vertici

    Graph(int v) {
        vertex = v;
        adjVertex = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjVertex.add(new ArrayList<>());
        }

        pred = new int[v];
    }

    public void AddEdge(int vertex1, int vertex2) {
        adjVertex.get(vertex1).add(vertex2);
        adjVertex.get(vertex2).add(vertex1);
    }

    public int BFS(int s, int d) {
        if (s == d)
            return 0;

        level = new int[vertex];
        for (int i = 0; i < vertex; i++)
            level[i] = -1;

        Queue<Integer> queue = new ArrayDeque<>();
        level[s] = 0;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.remove();

            for (int i : adjVertex.get(s)) {
                if (level[i] < 0 || level[i] > level[s] + 1) {
                    level[i] = level[s] + 1;
                    pred[i] = s;
                    queue.add(i);
                }
            }
        }

        return level[d];
    }

    public void segnaPercorso(int s, int v, int N, String[][] M) {
        if (v != s) {
            segnaPercorso(s, pred[v], N, M);
        }

        //Calcolo delle righe e colonne,per poter mettere la stringa X
        int row = (v - 1) / N;
        int col = (v - 1) % N;
        M[row][col] = "X";
    }
}
