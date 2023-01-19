package com.example.SpringTrySample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean //BCryptアルゴリズムを使用してパスワードのハッシュ化を行う
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override //Securityの設定をする
    protected void configure(HttpSecurity http) throws Exception{
        //　認可の設定
        http.authorizeHttpRequests() //ルール、アクセスポリシーの設定
                .antMatchers("/login","/signup").permitAll() //ログイン画面、ユーザー登録画面は認証なしでaccessできる
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") //adminはadminユーザーしか表示できない なぜかhasRoleだとうまくいかなかった
                .antMatchers("/user").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") //roleはadmin,userどちらでも表示できる
                .anyRequest().authenticated();//　↑以外の全てのURLリクエストはloginしないと見れない


        //認可失敗時の設定
        http.exceptionHandling()
                        .accessDeniedPage("/accessDeny"); //アクセス拒否されたときに表示するパス

        //ログイン設定
        http.formLogin() //フォーム認証の有効化
                .loginPage("/login") //　ログインページの指定
                .usernameParameter("username") //ユーザー名のリクエストパラメータ名
                .passwordParameter("password") //パスワードのリクエストパラメータ名
                .defaultSuccessUrl("/loginsuccess", true); //ログインが成功したら/loginsuccessに行く

        //ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); //logoutのURLを/logoutにする
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //　AuthenticationManagerBuilderに、実装したUserDetailsServiceを設定
        auth.userDetailsService(userDetailsService) //userDetailsServiceを使って、認証を行う
                .passwordEncoder(passwordEncoder()); //パスワードのハッシュ化方法を指定（BCryptアルゴリズム）
    }
}
