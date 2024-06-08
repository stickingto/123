package com.example.demo13.Mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface todoservice {
    @Select("select title from tongzhi where xingzhi = '会议' and current_date>date(time)")
    public List<String> yesterday();
    @Select("select title from tongzhi where xingzhi = '会议' and current_date=date(time)")
    public List<String> today();
    @Select("select title from tongzhi where xingzhi = '会议' and current_date<date(time)")
    public List<String> tomm();
}
