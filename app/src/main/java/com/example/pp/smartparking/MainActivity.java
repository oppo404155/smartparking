package com.example.pp.smartparking;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    public String IMIE;
    public IMIE im ;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Smart parking");


    }

    public void lisner(View view) {

        final ProgressDialog dialog=new ProgressDialog(MainActivity.this);
        dialog.setTitle("checking");
        dialog.setMessage("Loading......");
        dialog.show();
        ConnectivityManager manger = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo tester = manger.getActiveNetworkInfo();
        if (tester.isConnected() && tester!=null) {
            TelephonyManager tp = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
               //  public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            IMIE = tp.getDeviceId();
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("registcheck");
            Query query=reference.equalTo(IMIE).orderByChild("imie");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                           im=snapshot.getValue(IMIE.class);
                            String R=im.getREGEST();
                            if (R.equals("1")){

                                Intent i=new Intent(MainActivity.this,Main4Activity.class);
                               dialog.cancel();
                                startActivity(i);
                            }
                            else if(R.equals("0")){

                                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                                dialog.cancel();
                                startActivity(i);
                            }


                        }




                    }
                    else if(!dataSnapshot.exists()){
                        Intent i=new Intent(MainActivity.this,Main2Activity.class);
                       dialog.cancel();
                        startActivity(i);



                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

       else  {
            Toast.makeText(getApplicationContext(),"check the network connection",Toast.LENGTH_LONG).show();
            dialog.cancel();
        }



    }

}
