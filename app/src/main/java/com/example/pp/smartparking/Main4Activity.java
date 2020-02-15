package com.example.pp.smartparking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main4Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

public    String T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
getSupportActionBar().setTitle("choose operation");
        TelephonyManager tp = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
         T = tp.getDeviceId();



        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            Intent intent=new Intent(Main4Activity.this,MainActivity.class);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
             Intent K=new Intent(Main4Activity.this,about.class);
             startActivity(K);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
         if (id==R.id.exit){
         KEYING();
             Intent i=new Intent(Main4Activity.this,Main2Activity.class);
             startActivity(i);


         }

      else   if (id == R.id.nav_share) {
             Intent intent=new Intent(Intent.ACTION_SEND);
             intent.setType("text/plain");
             startActivity(intent);


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void reservation(View view) {
        Intent i= new Intent(Main4Activity.this,location.class);
        startActivity(i);
    }

    public void findecar(View view) {
        Intent intent=new Intent(Main4Activity.this,backtocar.class);
        startActivity(intent);

    }

    public void cancle(View view) {
        CustomDialog customDialog=new CustomDialog();
        customDialog.show(getSupportFragmentManager(),"Dialog");
    }

    public void lastreservationinf(View view) {
        IDdialog iDdialog=new IDdialog();
        iDdialog.show(getSupportFragmentManager(),"dialog");

    }
    public void KEYING(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("registcheck");
        reference.equalTo(T).orderByChild("imie").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                 String   Key =snapshot.getKey();
                    DatabaseReference regist = FirebaseDatabase.getInstance().getReference().child("registcheck").child(Key);
                    IMIE imie=new IMIE(T,"0");
                    regist.setValue(imie);
                  //  Intent i=new Intent(Main4Activity.this,Main2Activity.class);
                    //startActivity(i);

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
