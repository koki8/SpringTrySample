package com.example.SpringTrySample.dao;

import com.example.SpringTrySample.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //select文。userテーブルから、usernameが一致しているものを検索
    public MyUser findByUsername(String username);
}
