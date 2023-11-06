package com.example.project.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.dao.UserDAO;
import com.example.project.dto.MailDTO;

@Service
@AllArgsConstructor
public class SendEmailService {
	
	UserDAO userDAO;
	PasswordEncoder passwordEncoder;
	
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "amos5105@naver.com";

    public MailDTO createMailAndChangePassword(String email, String id){
        String str = getTempPassword();
        System.out.println(str);
        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(email);
        mailDTO.setTitle(id+"님 초등커뮤니티 임시비밀번호 안내 이메일 입니다.");
        mailDTO.setMessage("안녕하세요. 초등커뮤니티 임시비밀번호 안내 관련 이메일 입니다." + "[" + id + "]" +"님의 임시 비밀번호는 " + str + " 입니다.");
        
        updatePassword(str,email);
        return mailDTO;
    }

    public void updatePassword(String str,String email){
    	// 비밀번호 암호화
    	
        String pwd =passwordEncoder.encode(str);
        String id = userDAO.findUserByUserId(email).getId();
        userDAO.updateUserPassword(id,pwd);
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    
    
    public void mailSend(MailDTO mailDTO) {
    	System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
    }
    
    
    
    
    
    
    
     
    
    
}