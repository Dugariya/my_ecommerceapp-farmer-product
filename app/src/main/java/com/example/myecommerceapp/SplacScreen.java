package com.example.myecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myecommerceapp.activitys.LoginActivity;
import com.example.myecommerceapp.activitys.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplacScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splac_screen);
//        getSupportActionBar().hide();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent =new Intent(SplacScreen.this, LoginActivity.class);
                startActivity(intent);

                finish();

            }
        },1000);

    }
}