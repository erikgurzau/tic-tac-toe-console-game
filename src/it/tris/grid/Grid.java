package it.tris.grid;

import it.tris.game.Tris;
import it.tris.grid.utils.GridColors;

import java.util.ArrayList;

/**
 * Classe griglia del gioco del tris
 * @author Erik Gurzau
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public final class Grid {
    /**
     * Gioco del tris a cui la griglia fa riferimento
     */
    private Tris tris;
    /**
     * Numero delle righe
     */
    private int rows;
    /**
     * Numero delle colonne
     */
    private int columns;
    /**
     * Campo di gioco
     */
    private char[][] grid;
    /**
     * Numero minimo di righe per giocare
     */
    public static final int MIN_ROWS = 3;
    /**
     * Numero minimo di colonne per giocare
     */
    public static final int MIN_COLUMNS = MIN_ROWS;
    /**
     * Numero di simboli allineati per vincere
     */
    private final int NUM_ALIGN_SYMBOLS = 3;
    /**
     * Carattere nullo
     */
    public static final char DOT = '‧';
    /**
     * Lista contente le posizioni allineate dei simboli
     */
    public ArrayList<Position> positionAligned;

    /**
     * Costruttore della griglia (campo di gioco)
     * @param tris Gioco del tris a cui la griglia fa riferimento
     */
    public Grid(Tris tris){
        this.tris = tris;
        this.rows = MIN_ROWS;
        this.columns = MIN_COLUMNS;
        grid = initGrid(rows, columns);
        positionAligned = new ArrayList<>();
    }

    /**
     * Construttore della griglia con n righe ed n colonne
     * @param tris ioco del tris a cui la griglia fa riferimento
     * @param rows Numero di righe
     * @param columns Numero di colonne
     */
    public Grid(Tris tris, int rows, int columns){
        this.tris = tris;
        grid = initGrid(rows, columns);
        positionAligned = new ArrayList<>();
    }

    /**
     * Metodo per inizializzare il campo di gioco
     * @return Matrice di char contenente il carattere vuoto DOT
     */
    private char[][] initGrid(int rows, int columns){
        //if (rows != columns) rows = columns = Math.max(rows, columns);
        if (rows < MIN_ROWS) rows = MIN_ROWS;
        if (columns < MIN_COLUMNS) columns = MIN_ROWS;
        if (rows > Tris.MAX_PLAYERS) rows = Tris.MAX_PLAYERS;
        if (columns > Tris.MAX_PLAYERS) columns = Tris.MAX_PLAYERS;

        this.rows = rows;
        this.columns = columns;
        char[][] matrix = new char[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                matrix[i][j] = DOT;
            }
        }
        return matrix;
    }


    /**
     * Metodo getter delle righe
     * @return Numero di righe
     */
    public int getRows() {
        return rows;
    }

    /**
     * Metodo getter delle colonne
     * @return Numero delle colonne
     */
    public int getColumns() {
        return columns;
    }


    /**
     * Metodo per inserire il simbolo nel campo
     * @param symbol Simbolo corrispondente al giocatore
     * @param x Numero della riga
     * @param y Numero della colonna
     * @return Valore booleano della riuscita dell'inserimento
     */
    public boolean add(char symbol, int x, int y){
        if (grid[x][y] == DOT) {
            grid[x][y] = symbol;
            return true;
        }
        else return false;
    }

    /**
     * Metodo per controllare se il simbolo è consecutivo su una riga, colonna o sulle diagonali
     * @param symbol Simbolo corrispondente al giocatore
     * @return Valore booleano della consecutività del simbolo
     */
    public boolean isAligned(char symbol){
        if (checkRow(symbol)) return true;
        else if (checkColumn(symbol)) return true;
        else if (checkDiagonal(symbol)) return true;
        else return false;
    }

    /**
     * Metodo per controllare se il simbolo è consecutivo per k volte sulla stessa riga
     * @param symbol Simbolo corrispondente al giocatore
     * @return Valore booleano della consecutività del simbolo
     */
    private boolean checkRow(char symbol){
        int times = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j <= columns - NUM_ALIGN_SYMBOLS; j++){
                for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                    if (grid[i][j+k] == symbol){
                        times++;
                    }
                }
                if (times == NUM_ALIGN_SYMBOLS) {
                    for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                        positionAligned.add(new Position(i,j+k));
                    }
                    return true;
                }
                else times = 0;

            }
        }
        return false;
    }
    /**
     * Metodo per controllare se il simbolo è consecutivo per k volte sulla stessa colonna
     * @param symbol Simbolo corrispondente al giocatore
     * @return Valore booleano della consecutività del simbolo
     */
    private boolean checkColumn(char symbol){
        int times = 0;
        for (int i = 0; i < columns; i++){
            for (int j = 0; j <= rows - NUM_ALIGN_SYMBOLS; j++){
                for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                    if (grid[j+k][i] == symbol){
                        times++;
                    }
                }
                if (times == NUM_ALIGN_SYMBOLS) {
                    for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                        positionAligned.add(new Position(j+k,i));
                    }
                    return true;
                }
                else times = 0;
            }

        }
        return false;
    }
    /**
     * Metodo per controllare le due diagonali della matrice
     * @param symbol Simbolo corrispondente al giocatore
     * @return Valore booleano della consecutività del simbolo
     */
    private boolean checkDiagonal(char symbol){
        return primaryDiagonal(symbol) || secondaryDiagonal(symbol);
    }

    /**
     * Metodo per controllare se il simbolo è consecutivo per k volte sulla diagonale che inizia in alto e termina in basso
     * @param symbol Simbolo corrispondente al giocatore
     * @return Valore booleano della consecutività del simbolo
     */
    private boolean primaryDiagonal(char symbol){
        int times = 0;
        for (int i = 0; i <= rows - NUM_ALIGN_SYMBOLS; i++){
            for (int j = 0; j <= columns - NUM_ALIGN_SYMBOLS; j++){
                for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                    if (grid[i+k][j+k] == symbol){
                        times++;
                    }
                }
                if (times == NUM_ALIGN_SYMBOLS) {
                    for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                        positionAligned.add(new Position(i+k,j+k));
                    }
                    return true;
                }
                else times = 0;


            }
        }
        return false;
    }
    /**
     * Metodo per controllare se il simbolo è consecutivo per k volte sulla diagonale che inizia in basso e termina in alto
     * @param symbol Simbolo corrispondente al giocatore
     * @return Valore booleano della consecutività del simbolo
     */
    private boolean secondaryDiagonal(char symbol){
        int times = 0, row = rows - 1;
        for (int i = 0; i <= rows - NUM_ALIGN_SYMBOLS; i++){
            for (int j = 0; j <= columns - NUM_ALIGN_SYMBOLS; j++){
                for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                    if (grid[row-k][j+k] == symbol){
                        times++;
                    }
                }
                if (times == NUM_ALIGN_SYMBOLS) {
                    for (int k = 0; k < NUM_ALIGN_SYMBOLS; k++){
                        positionAligned.add(new Position(row-k,j+k));
                    }
                    return true;
                }
                else times = 0;
            }
            row--;
        }
        return false;
    }


    /**
     * Metodo per che organizza l'interafaccia del campo di gioco
     * @return La stringa contenente le informazioni del campo di gioco
     */
    public String toString(){
        String s = "\n  ";
        String numeri  = "⒈⒉⒊⒋⒌⒍⒎⒏⒐⒑⒒⒓⒔⒕⒖⒗⒘⒙⒚⒛";
        int k = 0;

        for (int i = 0; i < columns; i++) s += numeri.charAt(i) + "   ";

        for (int i = 0; i < rows; i++){
            s += "\n" + numeri.charAt(k);
            for (int j = 0; j < columns; j++){
                if (grid[i][j] == Grid.DOT) s += "  " + grid[i][j] + "   ";
                else {
                    if (positionAligned.contains(new Position(i,j))) s += " " + GridColors.ANSI_YELLOW + grid[i][j] + GridColors.ANSI_RESET + " ";
                    else s += " " + grid[i][j] + " ";
                }
            }
            k++;
        }
        return s;
    }
}
