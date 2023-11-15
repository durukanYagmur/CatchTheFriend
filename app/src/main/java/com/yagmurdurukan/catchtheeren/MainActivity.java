package com.yagmurdurukan.catchtheeren;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score;
    TextView scoreText;
    TextView time;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        time = findViewById(R.id.time);
        scoreText = findViewById(R.id.score);

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time: " + millisUntilFinished/1000);

                imageView = findViewById(R.id.imageView);
                imageView2 = findViewById(R.id.imageView2);
                imageView3 = findViewById(R.id.imageView3);
                imageView4 = findViewById(R.id.imageView4);
                imageView5 = findViewById(R.id.imageView5);
                imageView6 = findViewById(R.id.imageView6);
                imageView7 = findViewById(R.id.imageView7);
                imageView8 = findViewById(R.id.imageView8);
                imageView9 = findViewById(R.id.imageView9);
                imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

                hideImages();
            }

            @Override
            public void onFinish() {

                time.setText("Time is off");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Game over");
                alert.setMessage("Your score is "+score);
                alert.setMessage("Do you want to play again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),StartPage.class);
                        startActivity(intent);
                    }
                });
                alert.show();
            }
        }.start();


    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView imageView: imageArray) {
                    imageView.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }

    public void increaseScore(View view){

        score++;
        scoreText.setText("Score: "+score);
    }
}