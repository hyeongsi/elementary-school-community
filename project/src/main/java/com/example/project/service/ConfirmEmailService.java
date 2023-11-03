package com.example.project.service;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.project.dto.MailDTO;

@Service
@AllArgsConstructor
public class ConfirmEmailService {
	
	
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "amos5105@naver.com";
    private RedisService redisService;

    //인증번호 이메일 send
    public MailDTO createConfirmMail(String email){
    	
        String tempPassword = getTempPassword(); // 해결중
        System.out.println(tempPassword);
        
        redisService.setWithExpiration("email", tempPassword, 120, TimeUnit.SECONDS);
        String code = redisService.get("email");
        
        System.out.println(code);
        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(email);
        mailDTO.setTitle("초등커뮤니티 인증번호 안내 이메일 입니다.");
        mailDTO.setMessage("안녕하세요. 초등커뮤니티 인증번호 안내 관련 이메일 입니다." + "[" + email + "]" +"님의 임시 비밀번호는 " + code + " 입니다." + "인증번호의 유효시간은 2분 입니다.");
        
        return mailDTO;
    }


    // 인증번호 난수 생성
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
    
    
    // 이메일 전송 메서드ㄴ
    public void mailSend(MailDTO mailDTO) {
    	System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setFrom(ConfirmEmailService.FROM_ADDRESS);
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
    }
    
  //입력받은 값과 인증번호 매칭
  	public boolean CheckConfirm(String InputNumber){
  		
  		String code = redisService.get("email");

          if(InputNumber!=null && InputNumber.equals(code)) {
              return true;
          }
          else {
              return false;
          }
      }
    
    
    
    
    
    
     
    
    
}