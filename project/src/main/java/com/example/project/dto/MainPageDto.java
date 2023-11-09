package com.example.project.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Alias("MainPageDto")
public class MainPageDto {
	final private Long postId;
	final private String title;
	final private String content;
	final private Long viewCnt;
	final private String memberName;
	final private Long likeCount;
	
	public MainPageDto(Long postId, String title, String content, Long viewCnt, String memberName, Long likeCount) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.memberName = memberName;
		this.likeCount = likeCount;
	}
	
	
}
