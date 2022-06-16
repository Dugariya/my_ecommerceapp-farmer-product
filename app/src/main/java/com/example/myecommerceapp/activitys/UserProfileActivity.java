package com.example.myecommerceapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myecommerceapp.R;

public class UserProfileActivity extends AppCompatActivity {

      TextView name;
      TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name = findViewById(R.id.tvName);
        email = findViewById(R.id.tvEmail);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        name.setText(sh.getString("name",""));
        email.setText(sh.getString("email", ""));


    }
}