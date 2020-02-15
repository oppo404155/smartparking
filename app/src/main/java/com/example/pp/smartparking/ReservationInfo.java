package com.example.pp.smartparking;

import android.app.AlertDialog;

public class ReservationInfo  {
    private String slotnum,dateofreservation,entrancehour,exithour,carnum;
    private double id ;

    public ReservationInfo(String slotnum, String dateofreservation, String entrancehour, String exithour, String carnum,double id) {
        this.slotnum = slotnum;
        this.dateofreservation = dateofreservation;
        this.entrancehour = entrancehour;
        this.exithour = exithour;
        this.carnum = carnum;
        this.id=id;

    }

    public ReservationInfo() {}

    public String getSlotnum() {
        return slotnum;
    }

    public void setSlotnum(String slotnum) {
        this.slotnum = slotnum;
    }

    public String getDateofreservation() {
        return dateofreservation;
    }

    public void setDateofreservation(String dateofreservation) {
        this.dateofreservation = dateofreservation;
    }

    public String getEntrancehour() {
        return entrancehour;
    }

    public void setEntrancehour(String entrancehour) {
        this.entrancehour = entrancehour;
    }

    public String getExithour() {
        return exithour;
    }

    public void setExithour(String exithour) {
        this.exithour = exithour;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    public double getId(){
        return id;
    }
    public void setId(){
        this.id=id;
    }
}
