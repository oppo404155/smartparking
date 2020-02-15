package com.example.pp.smartparking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class CustomDialog extends AppCompatDialogFragment {

   EditText ID ;
    public  CustomDialog(){}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
LayoutInflater inflater=getActivity().getLayoutInflater();
final View view= (View) inflater.inflate(R.layout.customdialog,null);
     AlertDialog.Builder builder= new  AlertDialog.Builder(getActivity()).setView(view).setTitle("Cancel reservation").
            setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            }).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ID= view.findViewById(R.id.txt1);
            final String s= ID.getText().toString();
            if (s.isEmpty()){
                Toast.makeText(getContext(),"Please enter the ID",Toast.LENGTH_SHORT).show();
            }
            else {
                Double id=Double.parseDouble(s);
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("parkinginfo");
                Query query=reference.equalTo(id).orderByChild("id");
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               String key=null ;
                                if (dataSnapshot.exists()){
                                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                        key=snapshot.getKey();

                                    }
                                    DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child("parkinginfo").child(key);
                                    reference1.removeValue();
                                    Toast.makeText(view.getContext()," reservation deleted ",Toast.LENGTH_SHORT).show();
                                }
                                else if (!dataSnapshot.exists()){
                                    Toast.makeText(view.getContext(),"there is no reservation with such ID",Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }




        }
    });



    return builder.create();
    }}
