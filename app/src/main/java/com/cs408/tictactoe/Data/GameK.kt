package com.cs408.tictactoe.Data

import android.widget.Button

class GameK(var first: PlayerK,
           var second: PlayerK,
          private var buttons: Array<Array<Button?>>)
{
    private var turn = 0
    private val maxTurn = 9
    private var firstPlayerTurn = true

    fun isFirstPlayerTurn(): Boolean = this.firstPlayerTurn

    fun changeFirstPlayerTurn()
    {
        this.firstPlayerTurn = !this.firstPlayerTurn
    }

    fun fullBoard(): Boolean
    {
        return this.turn == this.maxTurn
    }

    fun reset()
    {
        this.first.reset()
        this.second.reset()
        this.turn = 0
        resetBoard()
    }

    fun incrementTurn()
    {
        this.turn++
    }

    fun newGame()
    {
        this.turn = 0
        resetBoard()
    }

    fun resetBoard()
    {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                buttons[i][j]?.setText("")
            }
        }
    }


    fun incrementWin()
    {
        if (isFirstPlayerTurn())
        {
            first++
        }
        else
        {
            second++
        }
    }

    fun getMark(): Char?
    {
        if (isFirstPlayerTurn())
        {
            return first.symbol
        }
        else
        {
            return second.symbol
        }
    }

    fun done(): Boolean
    {
        //check row
        for (n in 0..2) {
            //check row
            if (buttons[n][0]?.text.toString() == buttons[n][1]?.text.toString() &&
                    buttons[n][1]?.text.toString() == buttons[n][2]?.text.toString() &&
                    buttons[n][1]?.text.toString() != "") {
                return true
            }
            //check column
            if (buttons[0][n]?.text.toString() == buttons[1][n]?.text.toString() &&
                    buttons[1][n]?.text.toString() == buttons[2][n]?.text.toString() &&
                    buttons[1][n]?.text.toString() != "") {
                return true
            }


        }
        //check  major diagonal
        if (buttons[0][0]?.text.toString() == buttons[1][1]?.text.toString() &&
                buttons[1][1]?.text.toString() == buttons[2][2]?.text.toString() &&
                buttons[1][1]?.text.toString() != "") {
            return true
        }

        //check other diagonal
        return if (buttons[0][2]?.text.toString() == buttons[1][1]?.text.toString() &&
                buttons[1][1]?.text.toString() == buttons[2][0]?.text.toString() &&
                buttons[1][1]?.text.toString() != "") {
            true
        } else false
    }
}