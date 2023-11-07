package com.example.project.config;

import com.example.project.config.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();  // csrf 비활성화
        http.authorizeRequests()
                    //.antMatchers("/admin/**").authenticated()   // admin 하위 경로는 모두 로그인 필요
                    .antMatchers("/admin/**").hasRole("ADMIN")  // ADMIN 역할을 가진 유저만 접근 가능
                    .antMatchers("/mypage/**").hasAnyRole("USER", "ADMIN")  // USER, ADMIN 역할을 가진 유저만 접근 가능
                    .anyRequest().permitAll()   // 위의 경로를 제외한 나머지 경로는 모두 접근 허용
                    .and()
                    .formLogin()
                    .loginPage("/login")            // 로그인 페이지 경로 지정
                    .loginProcessingUrl("/login")   // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                    .defaultSuccessUrl("/loginSuccess")         // 로그인 완료 시 해당 url로 이동
                    .failureUrl("/login") // 로그인 실패 후 이동 페이지
                    .usernameParameter("id")        // 사용자 아이디(기본: username) 값을 id로 변경 -> UserDetails ServiceloadUserByUsername(parameter)의 파라미터 이름 변경 가능
                    .passwordParameter("pwd")
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)                        // 세션 초기화
                    .deleteCookies("JSESSIONID");    // 쿠키 삭제

        http.sessionManagement()    // 중복 로그인
                .maximumSessions(1) // 세션 최대 허용 수
                .maxSessionsPreventsLogin(false);   // false: 중복 로그인 시 이전 로그인 해제
    }

}
