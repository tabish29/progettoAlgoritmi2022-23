import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;


public class Esercizio5 {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Mancano dei dati dalla linea di comando");
            return;
        }

        String inputFile = args[0];
        int numeroContenitori;
        Contenitore[] contenitori;

        try {
            Scanner scanner = new Scanner(new File(inputFile));
            scanner.useLocale(Locale.US);

            numeroContenitori = scanner.nextInt();
            contenitori = new Contenitore[numeroContenitori];

            for (int i = 0; i < numeroContenitori; i++) {
                double larghezza = scanner.nextDouble();
                double altezza = scanner.nextDouble();
                double profondita = scanner.nextDouble();
                contenitori[i] = new Contenitore(larghezza, altezza, profondita);
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("C'è stato un errore durante la lettura del file: " + e.getMessage());
            return;
        }

        int[] numMaxContenitoriImpilabili = new int[numeroContenitori];
        int[] indiciContenitoriPrecedenti = new int[numeroContenitori];

        for (int i = 0; i < numeroContenitori; i++) {
            numMaxContenitoriImpilabili[i] = 1;
            indiciContenitoriPrecedenti[i] = -1;
        }

        int conteggioMassimoGlobale = 1;
        int indiceContenitoreMassimo = 0;

        for (int i = 1; i < numeroContenitori; i++) {
            for (int j = 0; j < i; j++) {
                if (contenitori[j].getLarghezza() < contenitori[i].getLarghezza()
                        && contenitori[j].getAltezza()  < contenitori[i].getAltezza()
                        && contenitori[j].getProfondita() < contenitori[i].getProfondita()) {
                    if (numMaxContenitoriImpilabili[j] + 1 > numMaxContenitoriImpilabili[i]) {
                        numMaxContenitoriImpilabili[i] = numMaxContenitoriImpilabili[j] + 1;
                        indiciContenitoriPrecedenti[i] = j;
                    }
                }
            }

            if (numMaxContenitoriImpilabili[i] > conteggioMassimoGlobale) {
                conteggioMassimoGlobale = numMaxContenitoriImpilabili[i];
                indiceContenitoreMassimo = i;
            }
        }


        int indice = indiceContenitoreMassimo;
        while (indice != -1) {
            Contenitore contenitore = contenitori[indice];

            System.out.println("Contenitore " + indice + ": " + contenitore.getLarghezza() + " "
                                        + contenitore.getAltezza() + " " + contenitore.getProfondita());

            indice = indiciContenitoriPrecedenti[indice];
        }
    }
}
class Contenitore {
    double larghezza;
    double altezza;
    double profondita;

    public Contenitore(double larghezza, double altezza, double profondita) {
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.profondita = profondita;
    }
    public double getLarghezza() {
        return larghezza;
    }

    public double getAltezza() {
        return altezza;
    }

    public double getProfondita() {
        return profondita;
    }
}
