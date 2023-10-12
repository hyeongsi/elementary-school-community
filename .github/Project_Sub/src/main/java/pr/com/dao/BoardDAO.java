package pr.com.dao;

import java.util.List;

import pr.com.dto.BoardDTO;
import pr.com.dto.Criteria;
import pr.com.dto.SearchCriteria;

public interface BoardDAO {

	// 게시글 작성
	public void write(BoardDTO boardDTO) throws Exception;
	
	// 게시물 목록 조회
	public List<BoardDTO> list(SearchCriteria scri) throws Exception;
	
	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;
	
	// 게시물 조회
	public BoardDTO read(int bno) throws Exception;
	
	// 게시물 수정
	public void update(BoardDTO boardDTO) throws Exception;
		
	// 게시물 삭제
	public void delete(int bno) throws Exception;
}
