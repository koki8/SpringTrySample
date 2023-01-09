package com.example.SpringTrySample.service;

import com.example.SpringTrySample.dao.UserMapper;
import com.example.SpringTrySample.entity.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        //findByUsernameで見つけてきたユーザ情報をmyUserに入れる
        MyUser myUser = userMapper.findByUsername(username);

        //UserDetailsにreturn
        return myUser;
    }
}
