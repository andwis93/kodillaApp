package com.tictactoeapp;

public class Player {
    private final String playerName;
    private boolean playerIsHuman;
    private final char playerSymbol;


    public Player(String playerName, boolean playerIsHuman, char playerSymbol) {
        this.playerName = playerName;
        this.playerIsHuman = playerIsHuman;
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerName() {
        return playerName;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public boolean isPlayerIsHuman() {
        return playerIsHuman;
    }
}
