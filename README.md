# Esercizi per l'Esame di Algoritmi e Strutture Dati

Questa repository contiene gli esercizi richiesti per l'esame di Algoritmi e Strutture Dati. Ogni esercizio è implementato come un file Java separato e risolve un problema specifico legato agli algoritmi e alle strutture dati. Di seguito sono forniti riassunti e dettagli per ciascun esercizio.

## Esercizio 1

**Descrizione:**
Progettare e realizzare un algoritmo per risolvere il problema seguente: 
- Sono dati due interi `n` e `m` tali che `1 ≤ m ≤ n`. 
- Il parametro `n` rappresenta il numero di coppie costituite da una chiave numerica intera e da una stringa di caratteri contenente esclusivamente lettere minuscole. 
- L'algoritmo deve stampare le `m` coppie con le chiavi minime, in ordine crescente di chiave. Se ci sono coppie con la stessa chiave, devono essere ordinate per stringa.

**Istruzioni per l'Esecuzione:**
- Il programma accetta due parametri sulla riga di comando:
  - Il valore di `m`
  - Il nome del file contenente le coppie (chiave, stringa)

**Comando di Esecuzione:**
java Esercizio1 <m> <file_input> 

# Esercizio 2: Gestione Dati da Sensore Ambientale

## Descrizione

Questo progetto implementa un algoritmo per gestire dati provenienti da un sensore ambientale. I dati sono coppie `<info, chiave>`, dove `info` è un carattere e `chiave` è un intero tra 1 e 2000. L'algoritmo gestisce le seguenti operazioni su una struttura dati `S` contenente `K` coppie:

1. Stampa la prima coppia.
2. Stampa tutte le coppie con chiavi minori o uguali alla chiave della prima coppia.
3. Stampa tutte le coppie con chiavi maggiori della chiave della prima coppia.
4. Stampa le coppie con la chiave minima e massima tra le `K` chiavi.

## Istruzioni per l'Esecuzione
Per cambiare il numero di coppie generate cambiare il valore della variabile k presente nel file Esercizio2.java

Per far partire il programma, utilizza il comando:

java Esercizio2 


# Esercizio 3: Ottimizzazione Reti Fibra Ottica

## Descrizione

Questo progetto affronta il problema di ottimizzazione della rete di connettività in fibra ottica per una città con M fornitori di Internet. Ogni abitazione della città è abbonata a uno dei fornitori e deve essere collegata alle altre abitazioni dello stesso fornitore minimizzando la lunghezza totale delle connessioni.

### Struttura del File di Input

Per visualizzare un esempio della struttura del file di input richiesto, si può consultare il file `Esercizio3.txt` incluso nella repository. Questo file mostra come devono essere organizzati i dati della scacchiera e le coordinate della sorgente e della destinazione.

### Requisiti

1. **Collegamento tra Abitazioni dello Stesso Fornitore**: Ogni coppia di abitazioni appartenenti allo stesso fornitore deve essere collegata in modo che esista almeno un cammino tra di esse.
2. **Nessun Collegamento tra Fornitori Diversi**: Non devono esistere collegamenti tra abitazioni di fornitori differenti.
3. **Minimizzazione della Lunghezza Totale**: La somma delle lunghezze dei collegamenti in fibra ottica deve essere minimizzata, utilizzando la distanza di Manhattan anziché la distanza Euclidea. La distanza di Manhattan tra due punti \((xi, yi)\) e \((xj, yj)\) è calcolata come:
   \[
   d(xi, yi, xj, yj) = |xi - xj| + |yi - yj|
   \]

### Output

Il programma stamperà a video la lunghezza complessiva dei tratti in fibra ottica per conto di ciascun fornitore. Se un fornitore non ha abbonati, il risultato sarà zero.

## Istruzioni per l'Esecuzione

Per eseguire il programma, utilizzare il comando seguente:

java  Esercizio3 Esercizio3.txt

# Esercizio 4

## Descrizione

Questo progetto risolve il problema di trovare il percorso minimo su una scacchiera rappresentata da una matrice quadrata. La scacchiera è composta da celle libere, occupate, e due celle speciali: la sorgente ('s') e la destinazione ('d'). L'obiettivo è spostare una pedina dalla sorgente alla destinazione nel minor numero possibile di mosse.

### Scacchiera

La scacchiera è rappresentata come una matrice di caratteri `M[0..n-1, 0..n-1]`, dove:
- **'.'** rappresenta una cella libera.
- **'*'** rappresenta una cella occupata.
- **'s'** rappresenta la cella sorgente.
- **'d'** rappresenta la cella destinazione.

La pedina può muoversi solo in orizzontale o verticale, cioè dalle coordinate (i, j) a (i+1, j), (i-1, j), (i, j+1), o (i, j-1), purché la destinazione sia una cella libera e all'interno dei bordi della matrice.

### Output

1. **Percorso Trovato**: Se esiste un percorso dal punto 's' al punto 'd', il programma stamperà la scacchiera con il percorso evidenziato:
   - Le celle del percorso saranno marcate con 'x'.
   - La scacchiera mostrata includerà la sorgente e la destinazione marcate come 'x'.

   Sotto la scacchiera, il programma stamperà il numero totale di celle nel percorso (compresa la sorgente e la destinazione).

2. **Percorso Non Raggiungibile**: Se non esiste un percorso dalla sorgente alla destinazione, il programma stamperà il messaggio: non raggiungibile

### Struttura del File di Input

Per visualizzare un esempio della struttura del file di input richiesto, si può consultare il file `Esercizio4.txt` incluso nella repository. Questo file mostra come devono essere organizzati i dati della scacchiera e le coordinate della sorgente e della destinazione.

### Istruzioni per l'Esecuzione

Per eseguire il programma, utilizzare il comando seguente, sostituendo `nome-del-file.txt` con il nome del file di input:

java Esercizio4 nome-del-file.txt

# Esercizio 5

## Descrizione

Questo progetto affronta il problema di inserire il maggior numero possibile di contenitori a forma di parallelepipedo uno dentro l'altro, simile a una "matrioska". Ogni contenitore ha una larghezza, un'altezza e una profondità, e deve essere inserito in un altro contenitore se e solo se soddisfa determinati vincoli.

### Vincoli

Per poter inserire un contenitore `j` dentro un contenitore `k`, devono essere soddisfatti i seguenti requisiti:

1. **Etichetta**: Il contenitore `j` deve avere un'etichetta strettamente minore di quella del contenitore `k` (j < k).
2. **Dimensioni**: Le dimensioni del contenitore `j` devono essere strettamente minori rispetto a quelle del contenitore `k`. In altre parole:
   - Larghezza: `x[j] < x[k]`
   - Altezza: `y[j] < y[k]`
   - Profondità: `z[j] < z[k]`

### Obiettivo

Il compito è determinare il massimo numero di contenitori che possono essere inseriti l'uno dentro l'altro rispettando i vincoli sopra indicati.

### Soluzione

L'algoritmo implementato utilizza la **programmazione dinamica** per risolvere il problema in modo efficiente. La soluzione prevede i seguenti passaggi:

1. **Ordinamento**: I contenitori vengono ordinati in base alle loro dimensioni e etichette.
2. **Programmazione Dinamica**: Utilizzando una tabella, viene calcolato il numero massimo di contenitori che possono essere impilati uno dentro l'altro per ogni contenitore.

### Struttura del File di Input

Per visualizzare un esempio della struttura del file di input richiesto, si può consultare il file `Esercizio5.txt` incluso nella repository. Questo file mostra come devono essere organizzati i dati della scacchiera e le coordinate della sorgente e della destinazione.

### Output

Il programma stamperà il massimo numero di contenitori che possono essere inseriti l'uno dentro l'altro.

### Istruzioni per l'Esecuzione

Per eseguire il programma, utilizzare il comando seguente, sostituendo `nome-del-file.txt` con il nome del file contenente le dimensioni dei contenitori:

```bash
java Esercizio5 nome-del-file.txt


