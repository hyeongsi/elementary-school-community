package pr.com.dao;

import java.util.List;

import pr.com.dto.ReplyDTO;

public interface ReplyDAO {
	
	// 댓글 조회
	public List<ReplyDTO> readReply(int bno) throws Exception;
	
	// 댓글 작성
	public void writdReply(ReplyDTO dto) throws Exception;

}
