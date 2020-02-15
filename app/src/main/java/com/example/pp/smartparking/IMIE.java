package com.example.pp.smartparking;

public class IMIE {
  private String IMIE, REGEST;

    public IMIE(String IMIE, String REGEST) {
        this.IMIE = IMIE;
        this.REGEST = REGEST;
    }
    public IMIE(){

    }


    public String getIMIE() {
        return IMIE;
    }

    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }

    public String getREGEST() {
        return REGEST;
    }

    public void setREGEST(String REGEST) {
        this.REGEST = REGEST;
    }
}

