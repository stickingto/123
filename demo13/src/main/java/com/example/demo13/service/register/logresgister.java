package com.example.demo13.service.register;

import com.example.demo13.Mapper.userservice;
import com.example.demo13.pop.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class logresgister implements logres {
        @Autowired
        private userservice si;
        @Override
        public user finduser(String name) {
            user u = si.finduser(name);
            return u;
        }

        @Override
    public void regis(String name, String number) {
        si.regiwww(name,number);
    }

    @Override
    public user loginuser(String name, String number) {
       user i = si.loginuser(name,number);
       return i;
    }

    @Override
    public void change(String name, String number) {
        si.change(name,number);
    }

}
