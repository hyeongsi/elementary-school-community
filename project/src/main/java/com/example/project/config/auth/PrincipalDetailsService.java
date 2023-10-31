package com.example.project.config.auth;

import com.example.project.dao.UserDAO;
import com.example.project.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// "/login" 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    PrincipalDetailsService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    // form의 username부분의 name값과 loadUserByUsername()의 매개변수 이름이 같아야 username을 받아와 동작이 가능하다.
    // form의 name="username" -> loadUserByUsername(String username) 매개변수 이름이 같아야 매칭되어 사용이 가능
    // 이 함수 리턴하면 시큐리티 session(내부 Authentication(내부 PrincipalDetails(내부 userDTO))) 형태로 들어가게 됨
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserDTO userDTO = userDAO.getUserById(id);
        if(userDTO != null){
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            return new User(userDTO.getId(), userDTO.getPwd(), authorities);
        }
        return null;
    }

}
