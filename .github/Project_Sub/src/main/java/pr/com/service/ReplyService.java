package pr.com.service;

import java.util.List;

import pr.com.dto.ReplyDTO;

public interface ReplyService {

	// 댓글 조회
	public List<ReplyDTO> readReply(int bno) throws Exception;
	
	// 댓글 작성
	public void writeReply(ReplyDTO dto) throws Exception;
}
