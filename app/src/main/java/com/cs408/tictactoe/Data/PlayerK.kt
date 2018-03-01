package com.cs408.tictactoe.Data

class PlayerK(var symbol: Char, var wins: Int = 0) {
    fun reset() {
        wins = 0
    }

    operator fun inc() : PlayerK {
        return PlayerK(symbol, wins + 1)
    }

}