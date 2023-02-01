package com.lld.models;

public class Board {

    private int dimensions;
    private Cell[][] cells;

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Board(int dimensions) {
        this.dimensions = dimensions;
        this.cells = new Cell[dimensions][dimensions];
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void display() {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                Cell currCell = this.getCells()[i][j];
                if (currCell.getPlayer() == null)
                    System.out.print("| - |");
                else
                    System.out.print("| " + currCell.getPlayer().getSymbol() + " |");
            }
            System.out.println();
        }
    }
}
