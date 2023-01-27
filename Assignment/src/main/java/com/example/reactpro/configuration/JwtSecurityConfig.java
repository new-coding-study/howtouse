package com.example.reactpro.configuration;


import com.example.reactpro.jwt.JwtFilter;
import com.example.reactpro.jwt.TokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    // TokenProvider 를 주입받아서 JwtFilter 를 통해 Security 로직에 필터를 등록
    // JwtFilter 를 등록한다.
    // 클라이언트가 리소스 요청할때 접근권한이 없는경우 기본적으로 로그인폼으로 보내게 되는데 이역할을 하는 필터가
    // UsernamePasswordAuthenticationFilter 이고 이필터 앞에 넣어서 인증권한이 없는 오류처리를 할수있도록 한다.
    private final TokenProvider tokenProvider;
    public JwtSecurityConfig(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }
    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        //UsernamePasswordAuthenticationFilter 필터전에 커스텀 필터를 걸겠다
    }
}
