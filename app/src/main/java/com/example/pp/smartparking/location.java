package com.example.pp.smartparking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class location extends AppCompatActivity {
 private Button B1,B2,B3,B4,B5,B6;
    int color ;

    @Override
    public void onBackPressed() {

        Intent i=new Intent(location.this,Main4Activity.class);
        startActivity(i);
        super.onBackPressed();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().setTitle("choose location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        B1=findViewById(R.id.slot1);
        B2=findViewById(R.id.slot2);
        B3=findViewById(R.id.slot3);
        B4=findViewById(R.id.slot4);
        B5=findViewById(R.id.slot5);
        B6=findViewById(R.id.slot6);
        //INITIALIZER();
     //B1.setBackgroundColor(Color.GREEN);
      //ColorDrawable d= (ColorDrawable) B1.getBackground();
       //color= d.getColor();


    }

    public void slot1(View view) {

            testconnectivity("1");
            B1.setBackgroundColor(Color.RED);


    }

    public void slot2(View view) {

       testconnectivity("2");


    }

    public void slot3(View view) {
        testconnectivity("3");

    }

    public void slot4(View view) {
        testconnectivity("4");

    }

    public void slot5(View view) {
        testconnectivity("5");

    }

    public void slot6(View view) {
       testconnectivity("6");

    }



 public void testconnectivity(final String SLOTID){

             ConnectivityManager checker = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
             NetworkInfo tester = checker.getActiveNetworkInfo();
             if (tester!=null&&tester.isConnected()){
                 dialogclass dialog = new dialogclass(SLOTID);
                 dialog.show(getSupportFragmentManager(), "dialog class");



             }
             else {
                 Toast.makeText(getApplicationContext(),"check the network connection",Toast.LENGTH_LONG).show();
             }

         }



    public void  INITIALIZER(){

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("slots");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              int b1=0 ,b2,b3,b4,b5,b6 ;

               ButtonVal buttonVal=new ButtonVal();
                 buttonVal.setSLOT1(dataSnapshot.child("slot1").getValue(ButtonVal.class).getSLOT1());
                 buttonVal.setSLOT1(dataSnapshot.child("slot2").getValue(ButtonVal.class).getSLOT2());
                 buttonVal.setSLOT1(dataSnapshot.child("slot3").getValue(ButtonVal.class).getSLOT3());
                 buttonVal.setSLOT1(dataSnapshot.child("slot4").getValue(ButtonVal.class).getSLOT4());
                 buttonVal.setSLOT1(dataSnapshot.child("slot5").getValue(ButtonVal.class).getSLOT5());
                 buttonVal.setSLOT1(dataSnapshot.child("slot6").getValue(ButtonVal.class).getSLOT6());
                b1=buttonVal.getSLOT1();
                b2=buttonVal.getSLOT2();
                b3=buttonVal.getSLOT3();
                b4=buttonVal.getSLOT4();
                b5=buttonVal.getSLOT5();
                b6=buttonVal.getSLOT6();
                if(b1==1){
                    B1.setBackgroundColor(Color.RED);
                }
                else {
                    B1.setBackgroundColor(Color.GREEN);
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

