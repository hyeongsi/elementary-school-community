package com.example.project.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.project.dao.UserDAO;
import com.example.project.dto.UserDTO;

@Service
@AllArgsConstructor
public class FindUserService {
	
	UserDAO userDAO;
	
	//Email을 통해 해당 Email로 가입된 정보가 있는지 확인하고, 가입된 정보가 있다면 입력받은 Id와 등록된 Id가 일차한지 여부를 리턴하는 메소드
	public boolean userEmailCheck(String email, String id){

        UserDTO userDTO = userDAO.findUserByUserId(email);
        if(userDTO!=null && userDTO.getId().equals(id)) {
            return true;
        }
        else {
            return false;
        }
    }
	
}