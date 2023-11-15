package com.yagmurdurukan.catchtheeren;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    public void startGame(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);


        startActivity(intent);

    }
}
