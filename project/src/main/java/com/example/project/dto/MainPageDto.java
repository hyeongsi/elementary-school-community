package com.example.project.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Alias("MainPageDto")
public class MainPageDto {
	private Long postId;
	private String title;
	private String content;
	private Long viewCnt;
	private String memberName;
	private Long likeCount;
}
