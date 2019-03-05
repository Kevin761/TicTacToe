package com.example.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int gameCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewP1;
    private TextView textViewP2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textViewP1 = findViewById(R.id.player1Text);
        textViewP2 = findViewById(R.id.player2Text);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        final Button bReset = findViewById(R.id.bReset);
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("X ");
        } else {
            ((Button) v).setText("O");
        }
        gameCount++;

        if(checkWin()){
            if(player1Turn){
                player1Win();
            }
            else {
                player2Win();
            }

        }
        else if(gameCount == 9){
            draw();
        }
        else{
            player1Turn = !player1Turn;
        }
    }

    private boolean checkWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }

        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1Win(){
        player1Points++;
        Toast.makeText(this,"Player 1 win!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void player2Win(){
        player2Points++;
        Toast.makeText(this,"Player 2 win!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints(){
        textViewP1.setText("Player 1: " + player1Points);
        textViewP2.setText("Player 2: " + player2Points);
    }
    private void resetBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        gameCount = 0;
        player1Turn = true;
    }
    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePoints();
        resetBoard();
    }
}




