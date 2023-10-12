package pr.com.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pr.com.dao.ReplyDAO;
import pr.com.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyDTO> readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}
	
	@Override
	public void writeReply(ReplyDTO dto) throws Exception {
		dao.writdReply(dto);
	}
	
}
