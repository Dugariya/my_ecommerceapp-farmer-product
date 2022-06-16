package com.example.myecommerceapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.myecommerceapp.R;
import com.example.myecommerceapp.fragment.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Fragment homefragment;
    Toolbar toolbar;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

         auth = FirebaseAuth.getInstance();


            toolbar = findViewById(R.id.home_toolbar);
            setSupportActionBar(toolbar);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
          /*      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this,UserProfileActivity.class));

            }
        });*/

        homefragment = new HomeFragment();
        loadFragment(homefragment);

    }

    private void loadFragment(Fragment homefragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homefragment);
        transaction.commit();
    }

    //@Override
        public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){

            int id = item.getItemId();

            if (id == R.id.menu_logout) {
                auth.signOut();
               startActivity(new Intent(MainActivity.this, LoginActivity.class));
                  Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();

                finish();
            } else if (id == R.id.menu_my_cart) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));

            }
            return true;

        }
    }