package com.cs408.tictactoe.Data;


import android.widget.Button;

public class Game {
    private Player first, second;
    private Button[][] buttons;
    private int turn;
    private final int maxTurn = 9;
    private boolean firstPlayerTurn;

    public Game(Player first, Player second, Button[][] buttons) {
        this.first = first;
        this.second = second;
        this.buttons = buttons;
        turn = 0;
        firstPlayerTurn = true;
    }

    public boolean isFirstPlayerTurn() {
        return firstPlayerTurn;
    }

    public void changeFirstPlayerTurn() {
        firstPlayerTurn = !firstPlayerTurn;
    }
    public boolean fullBoard() {
        return turn == maxTurn;
    }

    public void reset() {
        first.reset();
        second.reset();
        turn = 0;
        resetBoard();
    }

    public void incrementTurn() {
        turn++;
    }

    public int getTurn() {
        return turn;
    }

    public void newGame() {
        turn = 0;
        resetBoard();
    }

    public void resetBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public Player getFirstPlayer() {
        return first;
    }

    public Player getSecondPlayer() {
        return second;
    }

    public void incrementWin() {
        if(isFirstPlayerTurn()) {
            first.incrementWin();
        } else {
            second.incrementWin();
        }
    }

    //if the turn is even get the symbol of the first player else symbol of second player
    public char getMark() {
        return isFirstPlayerTurn() == true ? first.getSymbol() : second.getSymbol();
    }

    //check if game is done
    public boolean done() {
        //check row
        for(int n = 0; n < 3; n++) {
            //check row
            if(buttons[n][0].getText().toString().equals(buttons[n][1].getText().toString()) &&
                    buttons[n][1].getText().toString().equals(buttons[n][2].getText().toString()) &&
                    !buttons[n][1].getText().toString().equals("")) {
                return true;
            }
            //check column
            if(buttons[0][n].getText().toString().equals(buttons[1][n].getText().toString()) &&
                    buttons[1][n].getText().toString().equals(buttons[2][n].getText().toString()) &&
                    !buttons[1][n].getText().toString().equals("")) {
                return true;
            }


        }
        //check  major diagonal
        if(buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[1][1].getText().toString().equals(buttons[2][2].getText().toString()) &&
                !buttons[1][1].getText().toString().equals("")) {
            return true;
        }

        //check other diagonal
        if(buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[1][1].getText().toString().equals(buttons[2][0].getText().toString()) &&
                !buttons[1][1].getText().toString().equals("")) {
            return true;
        }


        return false;
    }
}
