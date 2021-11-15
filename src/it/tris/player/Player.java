package it.tris.player;
/**
 * Classe del giocatore di tris
 * @author Erik Gurzau
 * @see <a href="www.linkedin.com/in/erikgurzau">Linkedin</a>
 * @see <a mailto="gurzau10@gmail.com">Email</a>
 */
public final class Player {
    /**
     * Simbolo del giocatore
     */
    private final char symbol;

    /**
     * Costruttore del giocatore
     * @param symbol Simbolo char identificativo del giocatore
     */
    public Player(char symbol){
        this.symbol = symbol;
    }

    /**
     * Metodo getter del simbolo
     * @return
     */
    public char getSymbol() {
        return symbol;
    }


    @Override
    public String toString() {
        return "" + symbol;
    }
}
