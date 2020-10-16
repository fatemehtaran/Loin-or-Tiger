package com.taran.loinortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, NO
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Button btnReset;
    private GridLayout gridL;
    private boolean gameOver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridL  = findViewById(R.id.gridLayout);

        playerChoices[0] = Player.NO;
        playerChoices[1] = Player.NO;
        playerChoices[2] = Player.NO;
        playerChoices[3] = Player.NO;
        playerChoices[4] = Player.NO;
        playerChoices[5] = Player.NO;
        playerChoices[6] = Player.NO;
        playerChoices[7] = Player.NO;
        playerChoices[8] = Player.NO;

        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetTheGame();
            }
        });
    }

    //Animate&ShowImage,winner
    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView = (ImageView) imageView;

        tappedImageView.setTranslationX(-2000);

        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tiTag] == Player.NO && gameOver == false ) {

              tappedImageView.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;
            //show tiger or lion
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO){
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(2000);

            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();



            //winner
            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.NO) {
                    gameOver = true;
                    btnReset.setVisibility(View.VISIBLE);
                    String winnerOfGame = "";
                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "lion Player";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "tiger Player";
                    }
                    Toast.makeText(this, winnerOfGame + "is the winner", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    public void ResetTheGame() {

        for (int index = 0; index < gridL.getChildCount(); index++) {
            ImageView imageView = (ImageView) gridL.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        currentPlayer = Player.ONE;
//        playerChoices[0] = Player.NO;
//        playerChoices[1] = Player.NO;
//        playerChoices[2] = Player.NO;
//        playerChoices[3] = Player.NO;
//        playerChoices[4] = Player.NO;
//        playerChoices[5] = Player.NO;
//        playerChoices[6] = Player.NO;
//        playerChoices[7] = Player.NO;
//        playerChoices[8] = Player.NO;

        for (int index = 0; index <playerChoices.length;index++){

            playerChoices[index] = Player.NO;
        }

        gameOver = false;

        btnReset.setVisibility(View.INVISIBLE);


    }

}