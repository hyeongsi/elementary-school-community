package pr.com.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pr.com.dao.BoardDAO;
import pr.com.dto.BoardDTO;
import pr.com.dto.Criteria;
import pr.com.dto.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	// 게시글 작성
	@Override
	public void write(BoardDTO boardDTO) throws Exception {
		dao.write(boardDTO);
	}
	
	// 게시물 목록 조회
	@Override
	public List<BoardDTO> list(SearchCriteria scri) throws Exception {

		return dao.list(scri);
	}
	
	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception{
		return dao.listCount(scri);
	}
	
	// 게시물 조회
	@Override
	public BoardDTO read(int bno) throws Exception {
			
		return dao.read(bno);
	}
	
	// 게시물 수정
	@Override
	public void update(BoardDTO boardDTO) throws Exception {

		dao.update(boardDTO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		
		dao.delete(bno);
	}

}

