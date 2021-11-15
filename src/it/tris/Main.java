package it.tris;
import it.tris.game.Tris;
import it.tris.grid.Grid;
import it.tris.grid.utils.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tris tris;
        int risp, numPlayers = 0, numRows = 0, numColumns = 0;

        try {
            do {
                System.out.println("\n\n――――――――――――――――――");
                System.out.println("       GIOCO DEL TRIS");
                System.out.println("――――――――――――――――――");
                System.out.println("(1) - Partita classica");
                System.out.println("(2) - Partita personalizzata");
                System.out.println("(0) - Esci");
                System.out.print("\nRisposta: ");
                risp = sc.nextInt();

                switch (risp) {
                    case 1:
                        tris = new Tris();
                        tris.run();
                        break;
                    case 2:
                        System.out.println("\nmin." + Tris.MIN_PLAYERS + " max."+Tris.MAX_PLAYERS);
                        System.out.print("Inserisci il numero di giocatori: ");
                        numPlayers = sc.nextInt();
                        while (numPlayers < Tris.MIN_PLAYERS || numPlayers > Tris.MAX_PLAYERS){
                            System.out.println(GridColors.ANSI_RED + "Il numero deve essere compreso tra " +
                                    Tris.MIN_PLAYERS + " e " + Tris.MAX_PLAYERS + GridColors.ANSI_RESET);
                            System.out.print("Inserisci il numero di giocatori: ");
                            numPlayers = sc.nextInt();
                        }

                        System.out.println("\nmin." + Grid.MIN_ROWS + " max."+Tris.MAX_PLAYERS);
                        System.out.print("Inserisci il numero di righe:  ");
                        numRows = sc.nextInt();
                        while (numRows < Grid.MIN_ROWS || numRows > Tris.MAX_PLAYERS){
                            System.out.println(GridColors.ANSI_RED + "Il numero deve essere compreso tra " +
                                    Grid.MIN_ROWS + " e " + Tris.MAX_PLAYERS + GridColors.ANSI_RESET);
                            System.out.print("Inserisci il numero di giocatori: ");
                            numRows = sc.nextInt();
                        }

                        System.out.println("\nmin." + Grid.MIN_ROWS + " max."+Tris.MAX_PLAYERS);
                        System.out.print("Inserisci il numero di colonne:  ");
                        numColumns = sc.nextInt();
                        while (numColumns < Grid.MIN_ROWS || numColumns > Tris.MAX_PLAYERS){
                            System.out.println(GridColors.ANSI_RED + "Il numero deve essere compreso tra " +
                                    Grid.MIN_COLUMNS + " e " + Tris.MAX_PLAYERS + GridColors.ANSI_RESET);
                            System.out.print("Inserisci il numero di giocatori: ");
                            numColumns = sc.nextInt();
                        }

                        tris = new Tris(numPlayers, numRows, numColumns);
                        tris.run();
                        break;
                }
            } while (risp != 0);
        } catch (InputMismatchException e) {

        }
        System.out.println("\nGrazie per aver giocato!");
        System.out.println("\nRealizato da Erik Gurzau\n© 2021 Erik Gurzau. Tutti i diritti riservati.");



    }
}
