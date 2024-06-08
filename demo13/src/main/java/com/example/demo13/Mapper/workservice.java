package com.example.demo13.Mapper;

import com.example.demo13.pop.event;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface workservice {
    @Select("select * from tablework")
    public List<event> read();
@Select("update tablework set title = #{title} where id = #{id}")
   public String change(int id,String title);
@Select("select title from tablework where id = #{id}")
public String find(int id);
}
