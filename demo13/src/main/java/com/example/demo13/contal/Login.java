package com.example.demo13.contal;


import com.example.demo13.pop.user;
import com.example.demo13.service.register.logres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin//很重要，非常非常重要(跨域)
@RequestMapping("/ol")
public class Login {

    @Autowired
    private logres re;
@RequestMapping("/change")
public int change(@RequestParam("name") String name, @RequestParam("pass") String number){
    user u = re.finduser(name);
    if(u==null)
        return 0;
    else{
        re.change(name,number);
        return 1;
    }
}
    @RequestMapping("/register")
    public int  sea(@RequestParam("name") String name, @RequestParam("pass") String number) {
        //查询
        user u = re.finduser(name);
        if(u!=null) {
            return 2;
        }
        else{
            re.regis(name, number);
            return 1;
    }
    }
  @RequestMapping("/login")
    public int un(@RequestParam("name") String name,@RequestParam("pass") String number){//使用@来对应vue中传参的名字（很重要）
        user i = re.loginuser(name,number);
        System.out.println(name+number);
        if(i!=null)
            return 1;
      else{
          return 0;
      }
  }


}
