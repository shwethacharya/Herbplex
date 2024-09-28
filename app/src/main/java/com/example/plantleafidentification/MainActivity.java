package com.example.plantleafidentification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static int splash_screen_time_out =2000; // Next activity will start after 2000ms.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // This method is used to cover the full screen

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, HomePage.class); //Intent is used to switch from one activity to another.
                startActivity(i); //invoke the SecondActivity
                finish(); //current activity will be finished.
            }
        }, splash_screen_time_out );
    }
}
