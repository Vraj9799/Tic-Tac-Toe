package com.lld.strategies;

import com.lld.models.Board;
import com.lld.models.Cell;
import com.lld.models.GameState;
import com.lld.models.Player;

public interface GameWinningStrategy {
    GameState checkForWinner(Board board, Player player, Cell cell);
}
