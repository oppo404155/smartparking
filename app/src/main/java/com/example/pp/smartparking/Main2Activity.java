package com.example.pp.smartparking;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
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

public class Main2Activity extends AppCompatActivity {
   TextView tt ;
   public IMIE imie ;
   public  String  im;

    @Override
    public void onBackPressed() {

       Intent i=new Intent(Main2Activity.this,MainActivity.class);
       startActivity(i);

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
getSupportActionBar().setTitle("Sign in form");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tt=findViewById(R.id.tt);
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(i);

            }
        });


    }

    public void sign(View view) {
        ConnectivityManager checker = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo tetster = checker.getActiveNetworkInfo();
       final ProgressDialog progressDialog=new ProgressDialog(Main2Activity.this);
        progressDialog.setTitle("Authentication");
        progressDialog.setMessage("please wait......");
        progressDialog.show();

        EditText  et1 = findViewById(R.id.et11);
       String un =et1.getText().toString();
    final    EditText et2 =findViewById(R.id.et12);

       final     String PW=et2.getText().toString();
        if (tetster != null && tetster.isConnected()){

          if (!un.isEmpty()&&!PW.isEmpty()){
              Intent i =getIntent();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users auth");
        Query query= reference.equalTo(un).orderByChild("usrername");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name="x";
                String password="01122" ;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        users s = snapshot.getValue(users.class);
                        name = s.getName();
                        password = s.getPassword();
                    }
                    if (password .equals(PW)) {
                       checking();
                        Intent intent=new Intent(Main2Activity.this,Main4Activity.class);
                        startActivity(intent);
                        progressDialog.cancel();
                        LayoutInflater inflater=getLayoutInflater();
                        View view1=inflater.inflate(R.layout.welcoming,null);
                        TextView tx=view1.findViewById(R.id.cust);
                        tx.setText("Welcom "+name);
                        Toast toast=new Toast(getApplicationContext());
                        toast.setView(view1);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();



                    } else if (!password.equals(PW)) {
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "incorrect password", Toast.LENGTH_LONG).show();
                        et2.clearComposingText();

                    }


                 }
                else if (!dataSnapshot.exists()){
                    progressDialog.cancel();
                    Toast.makeText(getApplicationContext(),"there is no user with such email",Toast.LENGTH_LONG).show();
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               progressDialog.cancel();
                Toast.makeText(getApplicationContext(),"cannot reach the database",Toast.LENGTH_LONG).show();

            }
        });

          }


    else  {
             progressDialog.cancel();
              Toast.makeText(getApplicationContext(),"error one field or more is left  empty",Toast.LENGTH_LONG).show();
          }}
          else {
            progressDialog.cancel();
              Toast.makeText(getApplicationContext(),"check the network connection ",Toast.LENGTH_LONG).show();
        }


    }

public void checking(){
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
    im = tp.getDeviceId();
    DatabaseReference regist = FirebaseDatabase.getInstance().getReference().child("registcheck") ;
    imie=new IMIE(im,"1");
   String key =null ;
        Query query=regist.equalTo(im).orderByChild("imie");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            String key =null ;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        key=snapshot.getKey();

                    }
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("registcheck").child(key) ;
                    reference.setValue(imie);

                }
                else if(!dataSnapshot.exists()){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("registcheck") ;
                    reference.push().setValue(imie);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }}


