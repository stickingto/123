package com.example.demo13.contal;

import com.example.demo13.Mapper.tongzhi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/chu")
public class notice {
    @Autowired
    private tongzhi t;
    @RequestMapping("/tong")
    public String so(@RequestParam("name") String title, @RequestParam("neirong") String neirong, @RequestParam("diqu") String desc, @RequestParam("date") String time, @RequestParam("person") String person,@RequestParam("xingzhi")String xingzhi){
        System.out.println(title+neirong+desc+time+person+xingzhi);
        t.insert(title,neirong,desc,time,person,xingzhi);
        return "创建成功";
    }
    @RequestMapping("/show")
    public List<com.example.demo13.pop.Tongzhi>
    s(){
        List<com.example.demo13.pop.Tongzhi> a = t.show();
        return a;
    }
}
