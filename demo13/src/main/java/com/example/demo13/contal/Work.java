package com.example.demo13.contal;

import com.example.demo13.Mapper.workservice;
import com.example.demo13.pop.event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/work")
public class Work {
    @Autowired
    private workservice work1;
    @RequestMapping("/show")
    public List<event> s(){
        List<event> a = work1.read();
        return a;
    }
    @RequestMapping("changetitle")
    public String se(@RequestParam("id") int id,@RequestParam("title") String title){
        String a = work1.change(id,title);
        System.out.println(a);
        return a;
    }
    @RequestMapping("showtitle")
    public String as(@RequestParam("id") int id){
        String a = work1.find(id);
        System.out.println(a);
        return a;
    }
}
