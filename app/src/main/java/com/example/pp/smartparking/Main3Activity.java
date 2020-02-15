package com.example.pp.smartparking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Main3Activity extends AppCompatActivity {
     public EditText et21;
     public EditText et22;
    public EditText et23 ;
    public EditText et24 ;
    public  database data;
    public ProgressDialog progressDialog;
    public DatabaseReference reference;
    public  users m ;

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Main3Activity.this,Main2Activity.class);
        startActivity(i);

        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

     getSupportActionBar().setTitle("Registration form");
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void submit(View view) {
        progressDialog=new ProgressDialog(Main3Activity.this);
        progressDialog.setTitle("Saving Information");
        progressDialog.setMessage("checking......");
        progressDialog.show();

        et23 = findViewById(R.id.et23);
        et24 = findViewById(R.id.et24);
        et21 = findViewById(R.id.et21);
        et22 = findViewById(R.id.et22);
        String et1 = et23.getText().toString();
        String et2 = et24.getText().toString();
        String et3 = et21.getText().toString();
        String et4 = et22.getText().toString();

        ConnectivityManager checker = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo tetster = checker.getActiveNetworkInfo();


        if (tetster != null && tetster.isConnected()) {
            if (!et3.isEmpty() && !et1.isEmpty() && !et4.isEmpty() && !et2.isEmpty()) {
                int a = Integer.parseInt(et1);
                int b = Integer.parseInt(et2);
                if (a == b) {

                    m = new users(et3, et4, et1);
                    reference = FirebaseDatabase.getInstance().getReference().child("users auth");
                    reference.push().setValue(m);
                    progressDialog.cancel();
                    Toast.makeText(getApplicationContext(), "data stored", Toast.LENGTH_LONG).show();
                    Intent I =new Intent(Main3Activity.this,Main2Activity.class);
                    startActivity(I);
                }

                else if (a != b) {

                    Toast.makeText(getApplicationContext(), "the passwords not identical", Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
            }
            }
         else if (et1.isEmpty() || et2.isEmpty() || et3.isEmpty() || et4.isEmpty())

        {
            Toast.makeText(this, "one field or more left empty please fill all fields", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }}

     else  {
            Toast.makeText(this, "check the network connection", Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
        }
    }}



