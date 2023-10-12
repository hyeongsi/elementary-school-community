package pr.com.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pr.com.dao.MemberDAO;
import pr.com.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	@Override
	public void register(MemberDTO dto) throws Exception {
		
		dao.register(dto);
		
	}
	
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return dao.login(dto);
	}
	
	// 2023_10_12 참고 코드 해석 -> Controller에서 보내는 파라미터들을 memberUpdate(MemberDTO dto)로 받고
	@Override
	public void memberUpdate(MemberDTO dto) throws Exception {
		
		// 받은 dto를 DAO로 보내준다.
		dao.memberUpdate(dto);
	}
	
	// 업데이트에서 처리한 내용과 동일하다.
	@Override
	public void memberDelete(MemberDTO dto) throws Exception {
		dao.memberDelete(dto);
	}
	
	// 패스워드 체크
	@Override
	public int passChk(MemberDTO dto) throws Exception {
		int result = dao.passChk(dto);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberDTO dto) throws Exception {
		int result = dao.idChk(dto);
		return result;
	}

}
