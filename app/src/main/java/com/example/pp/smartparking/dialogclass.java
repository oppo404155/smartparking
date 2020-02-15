package com.example.pp.smartparking;

import android.Manifest;
import android.Manifest.permission;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static android.content.Context.TELEPHONY_SERVICE;

public class dialogclass extends AppCompatDialogFragment {
    private EditText enter,exit,carnum,slotnum;
    private  String currentdate,carnumber, ex,slotnumber ;



    public dialogclass(String slotnumber) {
        this.slotnumber = slotnumber;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        final AlertDialog.Builder buldir = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        final View view =  inflater.inflate(R.layout.dialoglayout,null);
        buldir.setView(view).setTitle("reservation information").setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("reserve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exit=view.findViewById(R.id.exit);
                carnum=view.findViewById(R.id.carnumber);
                carnumber=carnum.getText().toString();
                ex=exit.getText().toString();
                Calendar calendar= Calendar.getInstance();
                currentdate= DateFormat.getInstance().format(calendar.getTime());
                SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");

                Random random =new Random();
                double rand =random.nextInt(5000)+1;
                ReservationInfo reservationinfo=new ReservationInfo(slotnumber,currentdate,format.format(calendar.getTime()),ex,carnumber,rand);
                if (!ex.isEmpty()&&!carnumber.isEmpty()) {
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("parkinginfo");
                    database.push().setValue(reservationinfo);
                    Toast.makeText(getContext(), "your ID is :" + rand, Toast.LENGTH_LONG).show();

                    DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("lastID");
                    reference.setValue(rand);



                }
                else {
                    Toast.makeText(view.getContext()," error cannot make reservation: one or more field left empty",Toast.LENGTH_LONG).show();
                }


            }
        });

        return  buldir.create() ;
    }


}

