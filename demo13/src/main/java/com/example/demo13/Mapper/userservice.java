package com.example.demo13.Mapper;

import com.example.demo13.pop.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface userservice {
    @Select("select * from bbbb where name = #{name}")
   public user finduser(String name);
@Select("insert into bbbb(name,number)"+"values(#{name},#{number})")
   public void regiwww(String name, String number);
@Select("select * from bbbb where name = #{name} and number = #{number}")
   public user loginuser(String name, String number);
@Select("update bbbb set number = #{number} where name = #{name}")
   public void change(String name, String number);
}
