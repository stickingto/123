package com.example.demo13.contal;

import com.example.demo13.Mapper.todoservice;
import com.example.demo13.pop.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/table")
public class menu {
    @Autowired
    private todoservice to;
    @RequestMapping("/get")
    public Meeting re(){
        Meeting a = new Meeting();
        a.yesterday = to.yesterday();
        a.today = to.today();
        a.tomm = to.tomm();
        return a;
    }
}
