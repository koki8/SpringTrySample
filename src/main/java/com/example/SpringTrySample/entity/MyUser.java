package com.example.SpringTrySample.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class MyUser implements UserDetails {

    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public MyUser(String username, String password, Collection<GrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorites(){
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
