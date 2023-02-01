package com.lld.models;

public class Cell {

    private Player player;

    private int row;

    private int col;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
