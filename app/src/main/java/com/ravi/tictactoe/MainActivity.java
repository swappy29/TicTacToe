package com.ravi.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button [][] buttons=new Button[3][3];
    private Boolean player1turn=true;
    private int roundcount;
    private int player1points;
    private int player2points;
    private TextView textViewplayer1;
    private TextView textViewplayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewplayer1=findViewById(R.id.text_view_p1);
        textViewplayer2=findViewById(R.id.text_view_p2);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID="button_"+i+j;
                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.d("h","h");
                        buttonOnClick(v);
                    }
                });
            }
        }
    }


    public void buttonOnClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player1turn){
            ((Button)v).setText("X");

        }else
        {
            ((Button)v).setText("0");
        }
        roundcount++;
        if(checkforwin()){
            if(player1turn){
                player1wins();
            }else
            {
                player2wins();
            }
        }else if(roundcount==9){
            draw();
        }else
        {
            player1turn=!player1turn;
        }
    }
    private boolean checkforwin(){
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++){


            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void player1wins(){
        player1points++;
        Toast.makeText(this,"Player1 wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2wins(){
        player2points++;
        Toast.makeText(this,"Player2 wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();

        resetBoard();
    }
    private void updatePointsText(){
        textViewplayer1.setText("Player1"+player1points);
        textViewplayer2.setText("Player2"+player2points);
    }
    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }
}
