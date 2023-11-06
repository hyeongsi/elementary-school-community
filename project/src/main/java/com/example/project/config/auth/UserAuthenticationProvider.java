package com.example.project.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final PrincipalDetailsService principalDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    // 입력정보와 DB 사용자정보 비교
    // Authentication authentication는 화면에서 사용자가 입력한 로그인 정보를 담고있음
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // form의 username, password를 담는다
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        // 입력 username으로 사용자 정보를 가져와 user에 담는다
        PrincipalUser user = (PrincipalUser)principalDetailsService.loadUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // 인증되면 입력정보와 권한 담아 리턴
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    // 위의 authenticate 메소드에서 반환한 객체가 유효한 타입이 맞는지 검사
    // null 값이거나 잘못된 타입을 반환했을 경우 인증 실패로 간주
    public boolean supports(Class<?> authentication) {
        // 스프링 Security가 요구하는 UserPasswordAuthenticationToken 타입이 맞는지 확인
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
