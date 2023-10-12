package pr.com.dao;

import pr.com.dto.MemberDTO;

public interface MemberDAO {
	
	// 회원가입
	public void register(MemberDTO dto) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 회원정보 수정
	public void memberUpdate(MemberDTO dto) throws Exception;
	
	// 회원 탈퇴
	public void memberDelete(MemberDTO dto) throws Exception;
	
	// 패스워드 체크
	public int passChk(MemberDTO dto) throws Exception;
	
	// 아이디 중복체크
	public int idChk(MemberDTO dto) throws Exception;
		
}
