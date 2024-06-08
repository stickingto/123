package com.example.demo13.Mapper;

import com.example.demo13.pop.Tongzhi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.joda.time.DateTime;

import java.util.List;

@Mapper
public interface tongzhi {
    @Select("insert into tongzhi(title,neirong,diqu,time,person,xingzhi)"+"values(#{name},#{neirong},#{desc},#{time},#{person},#{xingzhi})")
   public void insert(String name, String neirong, String desc,String time, String person,String xingzhi);
@Select("select * from tongzhi")
   public List<Tongzhi> show();
}
