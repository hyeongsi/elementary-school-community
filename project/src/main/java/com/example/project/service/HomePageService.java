package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.dto.MainPageDto;
import com.example.project.mapper.HomePageMapper;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class HomePageService {

	final HomePageMapper homePageMapper;
	
	public List<MainPageDto> mainPageTop5(int boardId){
		return homePageMapper.mainPageTop5(boardId);
	}
	
	public List<MainPageDto> mainPagePublicTop5(){
		return homePageMapper.mainPagePublicTop5();
	}
}
