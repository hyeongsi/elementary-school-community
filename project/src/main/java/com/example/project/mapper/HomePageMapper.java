package com.example.project.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.MainPageDto;

@Mapper
@Repository
public interface HomePageMapper {
	
	public List<MainPageDto> mainPageTop5(int boardId);
	
}
