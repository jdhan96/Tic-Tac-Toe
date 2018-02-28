package com.cs408.tictactoe.Data;


public class Player {
    private char symbol;
    private int wins;

    public Player(char symbol) {
        this.symbol = symbol;
        wins = 0;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void reset() {
        wins = 0;
    }

    public void incrementWin() {
        wins++;
    }
}
