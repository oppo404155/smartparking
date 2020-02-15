package com.example.pp.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle("about us ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
