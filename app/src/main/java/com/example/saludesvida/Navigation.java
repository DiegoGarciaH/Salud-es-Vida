package com.example.saludesvida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.saludesvida.fragments.Account;
import com.example.saludesvida.fragments.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Navigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Home homefragment = new Home();
    Account accountfragment = new Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homefragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homemenu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homefragment).commit();
                        return true;
                    case R.id.accountmenu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountfragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}