package com.example.SpringTrySample.dao;

import com.example.SpringTrySample.entity.MyUser;
import com.example.SpringTrySample.form.UserReceiveForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //select文。loginuserテーブルから、usernameが一致しているものを検索
    public UserReceiveForm findByUsername(String username);

    //insert文。loginuserテーブルに、ユーザー追加
    public Boolean registerUser(String username, String password, String authority);
}
