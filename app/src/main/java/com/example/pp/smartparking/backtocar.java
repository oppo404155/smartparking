package com.example.pp.smartparking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class backtocar extends AppCompatActivity {
private EditText idfield ;
private TextView car_number,date_of_reservation,enterance_hour,exit_hour,slot_number ;

    @Override
    public void onBackPressed() {
        Intent i=new Intent(backtocar.this,Main4Activity.class);
        startActivity(i);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backtocar);
        getSupportActionBar().setTitle("find my car");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        car_number=findViewById(R.id.carnumber);
        date_of_reservation=findViewById(R.id.dateofreservation);
        enterance_hour=findViewById(R.id.enterancehour);
       exit_hour=findViewById(R.id.exithour);
       slot_number=findViewById(R.id.slotnumber);
        idfield=findViewById(R.id.ID);


    }

    public void show(View view) {
     final String s=idfield.getText().toString();
        final ProgressDialog progressDialog=new ProgressDialog(backtocar.this);
        progressDialog.setTitle("Getting data");
        progressDialog.setMessage("Loading......");
        progressDialog.show();
        ConnectivityManager Tester=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo tester=Tester.getActiveNetworkInfo();
        if(tester!=null&&tester.isConnected()){
            if (!s.isEmpty()) {
               double id = Double.parseDouble(s) ;
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("parkinginfo");
                Query query = reference.equalTo(id).orderByChild("id");

                query.addListenerForSingleValueEvent(new ValueEventListener () {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReservationInfo reservation=null ;

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                reservation = snapshot.getValue(ReservationInfo.class);

                            }

                            date_of_reservation.setText("date is : " + reservation.getDateofreservation());
                            enterance_hour.setText("entrance hour is : " + reservation.getEntrancehour());
                            exit_hour.setText("exit hour is : " + reservation.getExithour());
                            slot_number.setText(" slot number is : " + reservation.getSlotnum());
                            progressDialog.cancel();
                        } else if (!dataSnapshot.exists()) {
                            progressDialog.cancel();
                            Toast.makeText(getApplicationContext(), "there is no reservation with such ID", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "ERROR:" + databaseError.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
            else if(s.isEmpty()){

                Toast.makeText(getApplicationContext(),"fill the empty field please !",Toast.LENGTH_LONG).show();
                progressDialog.cancel();

            }

            }



        else {
            Toast.makeText(getApplicationContext(),"check the network connection!",Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }
    }
}
