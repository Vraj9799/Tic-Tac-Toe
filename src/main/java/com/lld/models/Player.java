package com.lld.models;

import java.util.Scanner;

public class Player {

    private String name;

    private char symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Cell decideCell(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tell the row to move: ");
        int row = scanner.nextInt();

        System.out.print("Tell the col to move: ");
        int col = scanner.nextInt();

        if (row < 0 || col < 0 || row >= board.getCells().length || col >= board.getCells().length) {
            System.out.println("Invalid row and col. Please choose again.");
            board.display();
            Cell cell = this.decideCell(board);
            return cell;
        }
        Cell cell = board.getCells()[row][col];
        if (cell.getPlayer() != null) {
            System.out.println("Cell is already occupied. Please choose again.");
            board.display();
            cell = this.decideCell(board);
            return cell;
        }
        return cell;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", symbol=" + symbol +
                '}';
    }
}
