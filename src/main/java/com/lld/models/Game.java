package com.lld.models;

import com.lld.exceptions.InvalidGameArgumentsException;
import com.lld.strategies.GameWinningStrategy;
import com.lld.strategies.OrderNGameWinningStrategy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private Board board;
    private Deque<Player> players;
    private GameState gameState;

    private Player winner;

    private GameWinningStrategy gameWinningStrategy;

    public Board getBoard() {
        return board;
    }

    public Deque<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public Game(int dimensions, List<Player> players) {
        this.board = new Board(dimensions);
        this.players = new ArrayDeque<>(players);
        this.gameState = GameState.END_IN_PROGRESS;
        this.winner = null;
        this.gameWinningStrategy = new OrderNGameWinningStrategy();
    }

    public void display() {
        this.board.display();
    }

    public void makeNextMove() {
        Player player = players.pollFirst();
        System.out.println(player.getName() + " please take your turn.");
        Cell cell = player.decideCell(board);
        cell.setPlayer(player);

        GameState newGameState = this.gameWinningStrategy.checkForWinner(board, player, cell);
        if (newGameState.equals(GameState.END_IN_RESULT)) {
            this.gameState = newGameState;
            this.winner = player;
        } else if (newGameState.equals(GameState.END_IN_DRAW)) {
            this.gameState = newGameState;
            this.winner = null;
        } else {
            this.gameState = newGameState;
            this.winner = null;
            this.players.offerLast(player);
        }
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private int dimensions;
        private List<Player> players;

        public GameBuilder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public void validate() throws InvalidGameArgumentsException {
            if (this.dimensions < 3) {
                throw new InvalidGameArgumentsException("Dimensions should be more than 3");
            }

            Set<Character> symbols = this.players.stream().map(p -> p.getSymbol()).collect(Collectors.toSet());
            if (players.size() != symbols.size()) {
                throw new InvalidGameArgumentsException("Players should have unique symbols.");
            }
            if (players.size() != dimensions - 1) {
                throw new InvalidGameArgumentsException("Number of players should be equal to " + (this.dimensions - 1));
            }
        }

        public Game build() throws InvalidGameArgumentsException {
            try {
                validate();
                return new Game(dimensions, players);
            } catch (InvalidGameArgumentsException exception) {
                throw exception;
            }
        }
    }
}
