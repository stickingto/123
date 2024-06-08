package com.example.demo13.service.register;

import com.example.demo13.pop.user;


public interface logres {

  public user finduser(String name);
  public void regis(String name,String number);

  public  user loginuser(String name, String number);

   public void change(String name,String number);
}
