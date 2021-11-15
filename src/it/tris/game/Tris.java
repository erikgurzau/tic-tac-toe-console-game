package it.tris.game;
import it.tris.grid.*;
import it.tris.grid.utils.GridColors;
import it.tris.player.Player;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
/**
 * Classe che implementa il gioco del "Tris" sul terminale, conosciuto anche come "Tic-Tac-Toe".
 * Il campo di gioco è espandibile, di conseguenza possono giocare più di due giocatori
 * @author Erik Gurzau
 * @version 2.3.0
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public final class Tris {
    /**
     * Array contenente i giocatori
     */
    private Player[] players;
    /**
     * Campo di gioco, contiene i simboli dei giocatori
     */
    private Grid grid;
    /**
     * Simboli disponibili per i giocatori
     */
    public static final String SYMBOLS = "XOABCDEFGHIJKLMNPQRSTUVWYZ";
    /**
     * Numero massimo di giocatori
     */
    public static final int MAX_PLAYERS = 20;
    /**
     * Numero minimo di giocatori
     */
    public static final int MIN_PLAYERS = 2;
    /**
     * Valore booleano dell'attivazione della AI che prende il controllo del gioco
     */
    private final boolean cpu_engine;


    /**
     * Costruttore di un tris
     */
    public Tris(){
        grid = new Grid(this);
        players = initPlayers(MIN_PLAYERS);
        cpu_engine = false;
    }
    /**
     * Costruttore di un tris
     * @param cpu_engine Valore booleano dell'attivazione della AI come giocatore
     */
    public Tris(boolean cpu_engine){
        grid = new Grid(this);
        players = initPlayers(MIN_PLAYERS);
        this.cpu_engine = cpu_engine;
    }

    /**
     * Costruttore di un tris per espandere il numero di giocatori e la dimensione del campo
     * @param numPlayers Numero di giocatori
     * @param rows Numero di righe
     * @param column Numero di colonne
     */
    public Tris(int numPlayers, int rows, int column){
        grid = new Grid(this, rows, column);
        players = initPlayers(numPlayers);
        cpu_engine = false;
    }

    /**
     * Costruttore di un tris per espandere il numero di giocatori e la dimensione del campo
     * @param numPlayers Numero di giocatori
     * @param rows Numero di righe
     * @param column Numero di colonne
     * @param cpu_engine Valore booleano dell'attivazione della AI come giocatore
     */
    public Tris(int numPlayers, int rows, int column, boolean cpu_engine){
        grid = new Grid(this, rows, column);
        players = initPlayers(numPlayers);
        this.cpu_engine = cpu_engine;
    }

    /**
     * Metodo per inizializzare l'array dei giocatori con i rispettivi simboli
     * @return Array di char inizializzato
     */
    private Player[] initPlayers(int numPlayers){
        if (numPlayers > MAX_PLAYERS) numPlayers = MAX_PLAYERS;
        else if (numPlayers < MIN_PLAYERS) numPlayers = MIN_PLAYERS;
        Player[] array = new Player[numPlayers];
        for (int i = 0; i < array.length; i++){
            array[i] = new Player(SYMBOLS.charAt(i));
        }
        return array;
    }


    /**
     * Metodo getter per l'array dei giocatori
     * @return Array di Player contenenti i giocatori
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Metodo per controllare se il giocatore ha vinto
     * @param player Giocatore di tris
     * @return Valore booleanno per la vittore del giocatore
     */
    private boolean hasWin(Player player){
        return grid.isAligned(player.getSymbol());
    }

    /**
     * Metodo per chidere all'utente le coordinate del simbolo da inserire nel campo
     * @param player Simbolo corrispondente al giocatore
     */
    private void turn(Player player){
        String row_message = "Inserisci la riga: ";
        String column_message = "Inserisci la colonna: ";
        int row, column;
        boolean validate = true;
        if (!cpu_engine) {
            do{
                System.out.println(GridColors.ANSI_BLUE + "\nTurno di " + player + GridColors.ANSI_RESET);
                row = readPosition(row_message, grid.getRows());
                column = readPosition(column_message, grid.getColumns());
                validate = grid.add(player.getSymbol(), row - 1, column - 1);
                if (!validate) System.out.println(GridColors.ANSI_RED + "\nCasella occupata, scegli un'altra posizione " + GridColors.ANSI_RESET);
            } while (!validate);
        }
        else turnCPU(player.getSymbol());
    }

    /**
     * Metodo per chiedere all'utente la riga o la colonna del campo
     * @param message Messaggio personalizzato per la richiesta della riga o colonna
     * @return Posizione intera della riga o colonna
     */
    private int readPosition(String message, int maxValue){
        Scanner in = new Scanner(System.in);
        int x;
        try {
            System.out.print(message);
            x = in.nextInt();
            while (x < 1 || x > maxValue) {
                System.out.println(GridColors.ANSI_RED + "⚠ Attenzione ⚠ Il valore deve essere compreso tra 1 e " + maxValue + GridColors.ANSI_RESET);
                System.out.print(message);
                x = in.nextInt();
            }
            return x;
        }catch (InputMismatchException e){
            System.out.println(GridColors.ANSI_RED + "⚠ Attenzione ⚠ Devi inserire solo numeri" + GridColors.ANSI_RESET);
            x = readPosition(message, maxValue);
            return x;
        }
    }
    /**
     * Metodo per inserire nel campo di gioco, in posizione randomica, il simbolo dell'AI
     * @param cpuSymbols Simbolo corrispondente all'AI
     */
    private void turnCPU(char cpuSymbols){
        Random rand = new Random();
        int x,y;
        boolean valido;
        do {
            x = rand.nextInt(grid.getRows());
            y = rand.nextInt(grid.getColumns());
            valido = grid.add(cpuSymbols, x, y);
        } while (!valido);
    }

    /**
     * Metodo per stampare sul terminare il campo di gioco
     */
    private void printGrid(){
        System.out.println(grid.toString());
    }

    /**
     * Metodo per iniziare a giocare
     */
    public void run(){
        int pos = -1, turns = (int) Math.pow(grid.getRows(), 2);
        printGrid();

        boolean active = true;
        while (active){
            for (int i = 0; i < players.length && active && turns > 0; i++){
                turn(players[i]);
                turns--;
                if (turns == 0) active = false;
                if (hasWin(players[i])){
                    active = false;
                    pos = i;
                }
                printGrid();
            }
        }
        if (pos > -1) System.out.println(GridColors.ANSI_BLUE + "\n✔ " + players[pos] + " ha vinto!" + GridColors.ANSI_RESET);
        else System.out.println(GridColors.ANSI_BLUE + "\n" + SYMBOLS.substring(0, players.length) + " pareggio!" + GridColors.ANSI_RESET);

    }
}
