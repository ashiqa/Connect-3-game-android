package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //1 is for yellow and 2 for red
    int player=1 ;
    boolean gameIsActive=true ;
    int[] gameState={0,0,0,0,0,0,0,0,0}; //position unplayed if 0
    int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playAgain(View view){
       LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
       playAgainLayout.setVisibility(View.INVISIBLE);
       player=1 ;
       gameIsActive=true ;
       for(int i=0 ; i<9;++i){
           gameState[i]=0 ;
       }
       androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
       for(int i=0 ; i<9;++i){
           ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
       }
    }public void dropIn(View view){
        ImageView counter = (ImageView) view ;
        int pos=Integer.parseInt(counter.getTag().toString());

        if(gameState[pos]==0&& gameIsActive==true) {
            counter.setTranslationY(-1000f);
            if (player == 1) {
                counter.setImageResource(R.drawable.yellow);
                gameState[pos]=1 ;
                player = 2;

            } else {
                counter.setImageResource(R.drawable.red);
                gameState[pos]=2 ;
                player = 1;
            }
            counter.animate().translationYBy(1000f).setDuration(600);
            for(int i=0; i<8;++i){

                    if(gameState[winningpos[i][0]]==gameState[winningpos[i][1]]&&gameState[winningpos[i][1]]==gameState[winningpos[i][2]]&&gameState[winningpos[i][0]]!=0){
                        //player
                        String winner="red";
                        if(gameState[winningpos[i][0]]==1)winner="yellow";
                        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        EditText winnerMessage = (EditText) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(winner +" has won !");
                        playAgainLayout.setVisibility(View.VISIBLE);
                        gameIsActive=false ;
                }
                    else{
                        boolean gameOver=true ;
                        for(int j=0 ; j<9 ;++j){
                            if(gameState[j]==0)gameOver=false ;
                        }
                        if(gameOver==true){
                            LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                            EditText winnerMessage = (EditText) findViewById(R.id.winnerMessage);
                            winnerMessage.setText("none has won !");
                            playAgainLayout.setVisibility(View.VISIBLE);

                            gameIsActive=false ;
                        }
                    }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}