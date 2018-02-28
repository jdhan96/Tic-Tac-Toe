package com.cs408.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs408.tictactoe.Data.Game;
import com.cs408.tictactoe.Data.Player;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Game game;

    private Button reset;
    private Button[][] buttons = new Button[3][3];
    private TextView firstPlayer, secondPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstPlayer = findViewById(R.id.firstPlayer);
        secondPlayer = findViewById(R.id.secondPlayer);
        reset = findViewById(R.id.resetButton);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.reset();
                firstPlayer.setText("Player 1 Score: " + String.valueOf(game.getFirstPlayer().getWins()));
                secondPlayer.setText("Player 2 Score: " + String.valueOf(game.getSecondPlayer().getWins()));
                Toast.makeText(Main2Activity.this, "Reset", Toast.LENGTH_SHORT).show();
            }
        });

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Player player1 = new Player('X');
        Player player2 = new Player('O');
        game = new Game(player1, player2, buttons);


    }



    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")) {
            Toast.makeText(Main2Activity.this, "Invalid Move", Toast.LENGTH_SHORT).show();
        }
        else {
            //set button to players symbol
            ((Button)view).setText(String.valueOf(game.getMark()));

            game.incrementTurn();

            //check if game is done
            if(game.done()) {
                //first player wins
                if(game.isFirstPlayerTurn()) {
                    Toast.makeText(Main2Activity.this, "Player 1 Wins!!!", Toast.LENGTH_SHORT).show();
                }
                //second player wins
                else {
                    Toast.makeText(Main2Activity.this, "Player 2 Wins!!!", Toast.LENGTH_SHORT).show();
                }
                //increment the win total
                game.incrementWin();
                firstPlayer.setText("Player 1 Score: " + String.valueOf(game.getFirstPlayer().getWins()));
                secondPlayer.setText("Player 2 Score: " + String.valueOf(game.getSecondPlayer().getWins()));
                game.newGame();
            }
            //check if game is draw
            else if(game.fullBoard()) {
                Toast.makeText(Main2Activity.this, "Draw Game!", Toast.LENGTH_SHORT).show();
                game.newGame();
            }
            //game progress
            else {
                game.changeFirstPlayerTurn();
            }
        }
    }

}
