package com.lld.strategies;

import com.lld.models.Board;
import com.lld.models.Cell;
import com.lld.models.GameState;
import com.lld.models.Player;

public class OrderNGameWinningStrategy implements GameWinningStrategy {
    @Override
    public GameState checkForWinner(Board board, Player player, Cell cell) {
        Cell[][] cells = board.getCells();
        // Check For Row
        boolean flag = true;
        int curr_row = cell.getRow();
        for (int j = 0; j < board.getDimensions(); j++) {
            if (cells[curr_row][j].getPlayer() != player) {
                flag = false;
                break;
            }
        }
        if (flag)
            return GameState.END_IN_RESULT;

        // Check For Col
        flag = true;
        int curr_col = cell.getCol();
        for (int i = 0; i < board.getDimensions(); i++) {
            if (cells[i][curr_col].getPlayer() != player) {
                flag = false;
                break;
            }
        }
        if (flag)
            return GameState.END_IN_RESULT;

        // Check for diagonals
        boolean right_flag = true;
        boolean left_flag = true;
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                Cell currCell = cells[i][j];
                if (i == j && currCell.getPlayer() != player) {
                    right_flag = false;
                }
                if ((i + j) == board.getDimensions() - 1 && currCell.getPlayer() != player) {
                    left_flag = false;
                }
            }
        }

        if (left_flag || right_flag)
            return GameState.END_IN_RESULT;

        // check for draw
        flag = true;
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                if (cells[i][j].getPlayer() == null) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            return GameState.END_IN_DRAW;
        }

        return GameState.END_IN_PROGRESS;
    }
}
