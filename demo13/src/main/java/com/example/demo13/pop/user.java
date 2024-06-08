package com.example.demo13.pop;

public class user {
    private String number;
    private String name;
   public user(String name,String number)
   {
       this.name = name;
       this.number = number;
   }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
