package com.example.pp.smartparking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class IDdialog extends AppCompatDialogFragment {

   TextView tx;
   private Double RANDOM ;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.iddialog,null);
        tx=view.findViewById(R.id.TV);
        DatabaseReference Reference= FirebaseDatabase.getInstance().getReference().child("lastID");
        Reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                   long id= (Long) dataSnapshot.getValue();
                    tx.setText("your last ID is:  "+id);
                }
                else {
                    Toast.makeText(getContext(),"error try again later",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setView(view).setTitle("Reservation ID").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }});
                return builder.create();

    }}
