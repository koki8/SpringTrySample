package com.example.SpringTrySample.service;

import com.example.SpringTrySample.dao.UserMapper;
import com.example.SpringTrySample.entity.MyUser;
import com.example.SpringTrySample.form.UserReceiveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetails loadUserByUsername(String searchUsername) throws UsernameNotFoundException{

        //findByUsernameで見つけてきたユーザ情報をmyUserに入れる
        UserReceiveForm userReceiveForm = userMapper.findByUsername(searchUsername);

        String username = userReceiveForm.getUsername();
        String password = userReceiveForm.getPassword();
        Collection<GrantedAuthority> authorities = Arrays.stream(userReceiveForm.getAuthority().split(","))
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        MyUser myUser = new MyUser(username, password, authorities);

        //UserDetailsにreturn
        return myUser;
    }

    //ユーザー情報の登録
    public void registerUser(String username, String password, String authority){
        userMapper.registerUser( username, password, authority);
    }
}
