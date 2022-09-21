package com.tictactoeapp;

import java.util.*;

public class PlayerComputer {

    GameMechanics gameMechanics = new GameMechanics();
    public List<Integer> emptySquares = new ArrayList<>();

    public void creatingComputerMoves(int boardListSize) {
        for (int i = 0; i < boardListSize; i++) {
            emptySquares.add(i);
        }
    }
    public boolean computerMove(List<Character> board, Player player, List<Integer> emptySquares ){
        Random rand = new Random();
        boolean squareEmpty = false;

        if (emptySquares.size()>0) {
            int square = rand.nextInt(emptySquares.size());
            board.set(emptySquares.get(square), player.getPlayerSymbol());
            emptySquares.remove(square);
            gameMechanics.setUsedSquares();
            squareEmpty = true;
        }
        return  squareEmpty;
    }

    public List<Integer> getEmptySquares() {
        return emptySquares;
    }
}