package pr.com.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import pr.com.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject SqlSession sql;
	
	// 댓글 조회
	@Override
	public List<ReplyDTO> readReply(int bno) throws Exception {
		return sql.selectList("replyMapper.readReply", bno);
	}
	
	// 댓글 작성
	@Override
	public void writdReply(ReplyDTO dto) throws Exception {
		sql.insert("replyMapper.writeReply", dto);
	}
	
	
}
