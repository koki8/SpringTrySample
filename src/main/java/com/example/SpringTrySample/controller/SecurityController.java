package com.example.SpringTrySample.controller;

import com.example.SpringTrySample.form.SignUpForm;
import com.example.SpringTrySample.service.UserDetailsServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    final private UserDetailsServiceImpl userDetailsServiceImpl;

    final private PasswordEncoder passwordEncoder;

    public SecurityController(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder){
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("login") //configのloginPageで指定したurlを準備
    public String login(){
        return "login";
    }

    @GetMapping("loginsuccess") //configのdefaultsuccessUrlで指定したurlを準備
    public String hello(Authentication loginUser, Model model){

        /**
         * AuthenticationでログインUserの情報を扱うことができる
         */
        model.addAttribute("username", loginUser.getName());
        model.addAttribute("authorities", loginUser.getAuthorities());

        return "loginsuccess";
    }

    @GetMapping("signup") //ユーザー登録画面
    public String newsignup(SignUpForm signUpForm, Model model){
        return "signup";
    }

    @PostMapping("signup")
    public String signup(SignUpForm signUpForm, Model model){
        try{
            userDetailsServiceImpl.registerUser(
                    signUpForm.getUsername(),
                    passwordEncoder.encode(signUpForm.getPassword()),
                    "ROLE_ADMIN");
        }catch (DataAccessException e){
            model.addAttribute("signUpError", "ユーザー登録に失敗しました");
            return "signup";
        }
        return "redirect:/";
    }

    @GetMapping("admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminpage(){
        return "admin";
    }

    @GetMapping("user")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public String userpage(){
        return "user";
    }

    @GetMapping("accessDeny")
    public String accessDeny(){
        return "accessDeny";
    }


}
