package com.cs408.tictactoe

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.cs408.tictactoe.Data.GameK
import com.cs408.tictactoe.Data.PlayerK

class MainActivity : AppCompatActivity(), View.OnClickListener
{

    private lateinit var firstPlayer: TextView
    private lateinit var secondPlayer: TextView
    private lateinit var reset: Button
    private lateinit var game: GameK
    private var buttons = Array(3) { arrayOfNulls<Button> (3) }

    //function extension shortening toast message call
    fun String.toast(context: Context)
    {
        Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstPlayer = findViewById(R.id.firstPlayer)
        secondPlayer = findViewById(R.id.secondPlayer)
        reset = findViewById(R.id.resetButton)

        reset.setOnClickListener{
            game.reset()
            firstPlayer.setText("Player 1 Score: ${game?.first?.wins}")
            secondPlayer.setText("Player 2 Score: ${game?.second?.wins}")
            "Reset".toast(this)
        }


        for (i in 0..2)
        {
            for (j in 0..2)
            {
                val buttonID: String = "button_" + i + j
                val resID: Int = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }

        game = GameK( PlayerK('X'), PlayerK('O'), buttons)
    }

    override fun onClick(view: View)
    {
        if (!(view as Button).text.toString().equals(""))
        {
            "Invalid Move".toast(this)
        }
        else
        {
            //set button to players symbol
            (view as Button).setText(game.getMark().toString())

            game.incrementTurn()

            //check if game is done
            if (game.done() == true)
            {
                //first player wins
                if (game.isFirstPlayerTurn() == true)
                {
                    Toast.makeText(this, "Player 1 Wins!!!", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Player 2 Wins!!!", Toast.LENGTH_SHORT).show()
                }
                game.incrementWin()
                firstPlayer.setText("Player 1 Score: ${game.first.wins}")
                secondPlayer.setText("Player 2 Score: ${game.second.wins}")
                game.newGame()
            }
            else if (game.fullBoard() == true)
            {
                "Draw Game!".toast(this)
                game.newGame()
            }
            else
            {
                game.changeFirstPlayerTurn()
            }
        }
    }
}
