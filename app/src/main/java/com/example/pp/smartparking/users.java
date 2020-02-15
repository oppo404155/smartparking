package com.example.pp.smartparking;

public class users {
     private String name , usrername,password ;

    public users(String name, String usrername, String password) {
        this.name = name;
        this.usrername = usrername;
        this.password = password;
    }
    public users(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsrername(String usrername) {
        this.usrername = usrername;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsrername() {
        return usrername;
    }

    public String getPassword() {
        return password;
    }
}


