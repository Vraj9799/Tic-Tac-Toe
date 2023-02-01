package com.lld;

import com.lld.models.Game;
import com.lld.models.GameState;
import com.lld.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Tic Tac Toe !!!");
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is the size of the board? ");
        int dimensions = scanner.nextInt();

        List<Player> players = new ArrayList<>();

        for (int i = 0; i < dimensions - 1; i++) {
            String playerName = "";
            String playerSymbol;
            do {
                System.out.print("What is the name of the player " + (i + 1) + "? ");
                playerName = scanner.next();
            }
            while (playerName.isBlank());
            do {
                System.out.print("What is the symbol for " + playerName + "? ");
                playerSymbol = scanner.next();
            } while (playerSymbol.isBlank());

            Player player = new Player(playerName, playerSymbol.charAt(0));
            System.out.println(player.toString());
            players.add(player);
        }

        Game game = Game.getBuilder()
                .setDimensions(dimensions)
                .setPlayers(players)
                .build();

        while (game.getGameState().equals(GameState.END_IN_PROGRESS)) {

            System.out.println("The board looks like");

            game.display();

            game.makeNextMove();

        }
        game.display();
        if (game.getGameState().equals(GameState.END_IN_DRAW)) {
            System.out.println("Game ended in draw...");
        } else if (game.getGameState().equals(GameState.END_IN_RESULT)) {
            System.out.println("Hurray!!! Winner is " + game.getWinner().getName());
        }
    }
}
