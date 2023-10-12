package pr.com.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import pr.com.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject SqlSession sql;
	// 회원가입
	
	@Override
	public void register(MemberDTO dto) throws Exception {
		sql.insert("memberMapper.register", dto);
	}
	
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		
		return sql.selectOne("memberMapper.login", dto);
	}
	
	// 2023_10_12 참고 코드 해석 -> 서비스에서 보낸 파라미터들을 memberUpdate(MemberDTO dto)에 담는다.
	@Override
	public void memberUpdate(MemberDTO dto) throws Exception {
		/* 2023_10_12 참고 코드 해석 -> dto에 담긴 파라미터들은 memberMapper.xml에 memberMapper라는 namespace에
		      아이디가 memberUpdate인 쿼리에 파라미터들을 넣어준다. */
		sql.update("memberMapper.memberUpdate", dto);
	}
	
	// 업데이트랑 흐름은 같음
	@Override
	public void memberDelete(MemberDTO dto) throws Exception {
		/* MemberDTO에 담긴 값들을 보내준다.
		     그럼 xml에서 memberMapper.memberDelete에 보면
		   #{userId}, #{userPass}에 파라미터값이 매칭된다.
		*/
		sql.delete("memverMapper.memberDelete", dto);
	}
	
	// 패스워드 체크
	@Override
	public int passChk(MemberDTO dto) throws Exception {
		int result = sql.selectOne("memberMapper.passChk", dto);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberDTO dto) throws Exception {
		int result = sql.selectOne("memberMapper.idChk", dto);
		return result;
	}

}





